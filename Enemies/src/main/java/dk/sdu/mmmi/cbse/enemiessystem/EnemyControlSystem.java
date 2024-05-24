package dk.sdu.mmmi.cbse.enemiessystem;

//import dk.sdu.mmmi.cbse.common.bullet.BulletSPI;

import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.commonenemy.Enemy;
import dk.sdu.mmmi.cbse.commonbullet.BulletSPI;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

import java.util.Arrays;
import java.util.Collection;
import java.util.Random;
import java.util.ServiceLoader;

import static java.util.stream.Collectors.toList;


public class EnemyControlSystem implements IEntityProcessingService {


    @Override
    public void process(GameData gameData, World world) {

        if (world.getEntities(Enemy.class).toArray().length == 0) {
            gameData.setRounds(gameData.getRounds() + 1);
            for (int i = 0; i < gameData.getRounds(); i++) {
                world.addEntity(createEnemyShip(gameData));
            }
        }

        for (Entity enemyEntity : world.getEntities(Enemy.class)) {
            Enemy enemy = (Enemy) enemyEntity;

            LifePart lifePart = enemyEntity.getPart(LifePart.class);

            if (lifePart.getLife() <= 0) {
                world.removeEntity(enemyEntity);
                gameData.setKillCounter(gameData.getKillCounter() + 1);
                break;
            }

//            CommonPath commonPath = enemy.getPath();
//            if (commonPath == null) {
//                continue;
//            }

            int[][] path = enemy.getPathArray();
            if (path == null || path.length == 0) {
                continue;
            }


            // Get the first tile in the path
            int[] currentDestination = path[0];
            double destinationX = currentDestination[0] * (gameData.getDisplayWidth() / 20) + 20;
            double destinationY = currentDestination[1] * (gameData.getDisplayHeight() / 20) + 20;

            double dx = destinationX - enemy.getX();
            double dy = destinationY - enemy.getY();

            double distanceToDestination = Math.sqrt(dx * dx + dy * dy);
            if (distanceToDestination < enemy.getSpeed()) { // Check if the enemy is about to overshoot the destination
                // Set the enemy's position to the destination
                enemy.setX(destinationX);
                enemy.setY(destinationY);
            } else {
                // Move the enemy towards the current destination
                double angle = Math.atan2(dy, dx);
                enemy.setX(enemy.getX() + Math.cos(angle) * enemy.getSpeed());
                enemy.setY(enemy.getY() + Math.sin(angle) * enemy.getSpeed());
            }



            // Enemy has to be facing the player.
            int[] currentDestinationRotation = path[path.length - 1];
            double destinationX2 = currentDestinationRotation[0] * (gameData.getDisplayWidth() / 20);
            double destinationY2 = currentDestinationRotation[1] * (gameData.getDisplayHeight() / 20);

            double dx2 = destinationX2 - enemy.getX();
            double dy2 = destinationY2 - enemy.getY();

            // Calculate the angle in radians
            double angleRad = Math.atan2(dy2, dx2);

            // Convert the angle to degrees
            double angleDeg = Math.toDegrees(angleRad);

            // Set the enemy's rotation
            enemy.setRotation(angleDeg);



            if (enemy.getPathArray().length < 8 && !enemy.isShooting()) {

                Thread thread1 = new Thread(() -> {
                    enemy.setShooting(true);
                    try {
                        // Execute the action
                        getBulletSPIs().stream().findFirst().ifPresent(
                                spi -> world.addEntity(spi.createBullet(enemy, gameData))
                        );
                        // Wait for 2 seconds after completing the loop
                        Thread.sleep(1500); // 1500 milliseconds
                        enemy.setShooting(false);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt(); // Properly handle thread interruption
                        e.printStackTrace();
                    }
                });
                thread1.start();
            }

            lifePart.process(gameData, enemyEntity);

        }

    }


    public Enemy createEnemyShip(GameData gameData) {

        Random random = new Random();
        int randomWidthX = random.nextInt(gameData.getDisplayWidth());
        int randomHeightY = random.nextInt(gameData.getDisplayHeight());

        Enemy enemyShip = new Enemy();
        enemyShip.setPolygonCoordinates(18.0, -1.5, 12.0, -1.5, 12.0, -4.5, 9.0, -4.5, 9.0, -7.5, -3.0, -7.5, -3.0, -10.5, 0.0, -10.5, 0.0, -13.5, -15.0, -13.5, -15.0, -7.5, -12.0, -7.5, -12.0, -4.5, -9.0, -4.5, -9.0, -1.5, -15.0, -1.5, -15.0, 1.5, -9.0, 1.5, -9.0, 4.5, -12.0, 4.5, -12.0, 7.5, -15.0, 7.5, -15.0, 13.5, 0.0, 13.5, 0.0, 10.5, -6.0, 10.5, -6.0, 7.5, 6.0, 7.5, 6.0, 1.5, 12.0, 1.5, 12.0, -1.5, 6.0, -1.5, 6.0, -4.5, 12.0, -4.5, 12.0, -1.5, 18.0, -1.5);
        enemyShip.setX(randomWidthX);
        enemyShip.setY(randomHeightY);
        enemyShip.add(new LifePart(5));
        return enemyShip;
    }

    Collection<? extends BulletSPI> getBulletSPIs() {
        return ServiceLoader.load(BulletSPI.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }
}
