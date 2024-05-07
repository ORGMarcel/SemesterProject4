package dk.sdu.mmmi.cbse.enemiessystem;

//import dk.sdu.mmmi.cbse.common.bullet.BulletSPI;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.commonenemy.Enemy;
import dk.sdu.mmmi.cbse.commonbullet.BulletSPI;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.commonpath.CommonPath;
import dk.sdu.mmmi.cbse.commonplayer.Player;

import java.util.Collection;
import java.util.Random;
import java.util.ServiceLoader;

import static java.util.stream.Collectors.toList;


public class EnemyControlSystem implements IEntityProcessingService {


    @Override
    public void process(GameData gameData, World world) {

        Random random = new Random();
        int randomNumber;
        int randomNumber2;
        int randomInt = random.nextInt(500);

        for (Entity enemyEntity : world.getEntities(Enemy.class)) {
            Enemy enemy = (Enemy) enemyEntity;


            LifePart lifePart = enemyEntity.getPart(LifePart.class);

            if(lifePart.getLife()<=0){
                world.removeEntity(enemyEntity);
            }

//            if(enemy.getPath() != null){
//                CommonPath path = enemy.getPath();
//                int[][] pathArray = path.getPath();
//                int currentPathIndex = enemy.getCurrentPathIndex();
//
//                if (pathArray != null && currentPathIndex < pathArray.length) {
//                    int[] nextPosition = pathArray[currentPathIndex];
//                    int tileSize = 40;
//                    double speed = enemy.getSpeed();
//                    double targetX = nextPosition[0]*tileSize;
//                    double targetY = nextPosition[1]*tileSize;
//
//                    // Calculate the direction vector
//                    double dx = targetX - enemy.getX();
//                    double dy = targetY - enemy.getY();
//
//                    // Calculate the distance to the target
//                    double distance = Math.sqrt(dx*dx + dy*dy);
//
//                    // Normalize the direction vector to get a unit vector
//                    double ux = dx / distance;
//                    double uy = dy / distance;
//
//                    // Multiply the unit vector by the speed to get the velocity
//                    double vx = ux * speed;
//                    double vy = uy * speed;
//
//                    // Add the velocity to the current position to get the new position
//                    enemy.setX(enemy.getX() + vx);
//                    enemy.setY(enemy.getY() + vy);
//
//                    // Check if the enemy has reached the target position
//                    if (Math.abs(enemy.getX() - targetX) < 1 && Math.abs(enemy.getY() - targetY) < 1) {
//                        enemy.setCurrentPathIndex(currentPathIndex + 1);
//                    }
//                }
//            }

            // TODO: NEW
//            if(enemy.getPath() != null){
//                CommonPath path = enemy.getPath();
//                int[][] pathArray = path.getPath();
//                int currentPathIndex = enemy.getCurrentPathIndex();
//
//                if (pathArray != null && currentPathIndex < pathArray.length) {
//                    int[] nextPosition = pathArray[currentPathIndex];
//                    int tileSize = 40;
//                    double speed = enemy.getSpeed();
//                    double targetX = nextPosition[0]*tileSize;
//                    double targetY = nextPosition[1]*tileSize;
////                    System.out.println("Current Position: (" + enemy.getX() + ", " + enemy.getY() + ")");
////                    System.out.println("Target Position: (" + targetX + ", " + targetY + ")");
////                    System.out.println("Calculated New Position: (" + lerp(enemy.getX(), targetX, speed) + ", " + lerp(enemy.getY(), targetY, speed) + ")");
//
//
//                    enemy.setX(lerp(enemy.getX(), targetX, speed));
//                    enemy.setY(lerp(enemy.getY(), targetY, speed));
//
//
////                    enemy.setX(enemy.getX() + (targetX - enemy.getX()) * speed);
////                    enemy.setY(enemy.getY() + (targetY - enemy.getY()) * speed);
////                    enemy.setX(nextPosition[0]*tileSize);
////                    enemy.setY(nextPosition[1]*tileSize);
//
//                    if (Math.abs(enemy.getX() - targetX) < 1 && Math.abs(enemy.getY() - targetY) < 1) {
////                        System.out.println("Next position reached");
//                        enemy.setCurrentPathIndex(currentPathIndex + 1);
//                    }
//
////                    enemy.setCurrentPathIndex(currentPathIndex + 1);
//                } else if (currentPathIndex >= pathArray.length) {
//                    enemy.setCurrentPathIndex(0);
//                } else {
//                    // If the next position is not walkable, skip to the next position in the path
//                    enemy.setCurrentPathIndex(currentPathIndex + 1);
//                }
//            }







//            randomNumber = random.nextInt(50);
//            randomNumber2 = random.nextInt(20);
//
//            if (randomNumber ==0) {
//                enemy.setRotation(enemy.getRotation() - 45);
//            }
//            if (randomNumber ==1) {
//                enemy.setRotation(enemy.getRotation() + 45);
//            }
//            if (true) {
//                double changeX = Math.cos(Math.toRadians(enemy.getRotation()));
//                double changeY = Math.sin(Math.toRadians(enemy.getRotation()));
//                enemy.setX(enemy.getX() + changeX);
//                enemy.setY(enemy.getY() + changeY);
//            }
//            if (randomNumber2 ==5) {
//                getBulletSPIs().stream().findFirst().ifPresent(
//                        spi -> {world.addEntity(spi.createBullet(enemy, gameData));}
//                );
//            }
//
//            if (enemy.getX() < 0) {
//                enemy.setX(1);
//                enemy.setRotation(enemy.getRotation() + 180);
//            }
//
//            if (enemy.getX() > gameData.getDisplayWidth()) {
//                enemy.setX(gameData.getDisplayWidth()-1);
//                enemy.setRotation(enemy.getRotation() + 180);
//            }
//
//            if (enemy.getY() < 0) {
//                enemy.setY(1);
//                enemy.setRotation(enemy.getRotation() + 180);
//            }
//
//            if (enemy.getY() > gameData.getDisplayHeight()) {
//                enemy.setY(gameData.getDisplayHeight()-1);
//                enemy.setRotation(enemy.getRotation() + 180);
//            }

            lifePart.process(gameData, enemyEntity);

        }

//        // Spawning enemies that is round * 2
//        if (!world.isRoundRunning()){
//            for (int i = 0; i < world.getRound()*2; i++) {
//                Enemy enemies;
//                enemies = createEnemyShip(gameData);
//                world.addEntity(enemies);
//            }
//            world.setRoundRunning(true);
//        }



    }
    private double lerp(double start, double end, double t) {
        return start + t * (end - start);
    }

    private Enemy createEnemyShip(GameData gameData) {

        Random random = new Random();
        int randomWidthX = random.nextInt(gameData.getDisplayWidth());
        int randomHeightY = random.nextInt(gameData.getDisplayHeight());

        Enemy enemyShip = new Enemy();
//        enemyShip.add(new LifePart(3));
        enemyShip.setPolygonCoordinates(18.0, -1.5, 12.0, -1.5, 12.0, -4.5, 9.0, -4.5, 9.0, -7.5, -3.0, -7.5, -3.0, -10.5, 0.0, -10.5, 0.0, -13.5, -15.0, -13.5, -15.0, -7.5, -12.0, -7.5, -12.0, -4.5, -9.0, -4.5, -9.0, -1.5, -15.0, -1.5, -15.0, 1.5, -9.0, 1.5, -9.0, 4.5, -12.0, 4.5, -12.0, 7.5, -15.0, 7.5, -15.0, 13.5, 0.0, 13.5, 0.0, 10.5, -6.0, 10.5, -6.0, 7.5, 6.0, 7.5, 6.0, 1.5, 12.0, 1.5, 12.0, -1.5, 6.0, -1.5, 6.0, -4.5, 12.0, -4.5, 12.0, -1.5, 18.0, -1.5);
        enemyShip.setX(randomWidthX);
        enemyShip.setY(randomHeightY);
        return enemyShip;
    }

    private Collection<? extends BulletSPI> getBulletSPIs() {
        return ServiceLoader.load(BulletSPI.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }
}
