package dk.sdu.mmmi.cbse.enemiessystem;

//import dk.sdu.mmmi.cbse.common.bullet.BulletSPI;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.commonenemy.Enemy;
import dk.sdu.mmmi.cbse.commonbullet.BulletSPI;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

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

        for (Entity enemy : world.getEntities(Enemy.class)) {

            LifePart lifePart = enemy.getPart(LifePart.class);

            if(lifePart.getLife()<=0){
                world.removeEntity(enemy);
            }


            randomNumber = random.nextInt(50);
            randomNumber2 = random.nextInt(20);

            if (randomNumber ==0) {
                enemy.setRotation(enemy.getRotation() - 45);
            }
            if (randomNumber ==1) {
                enemy.setRotation(enemy.getRotation() + 45);
            }
            if (true) {
                double changeX = Math.cos(Math.toRadians(enemy.getRotation()));
                double changeY = Math.sin(Math.toRadians(enemy.getRotation()));
                enemy.setX(enemy.getX() + changeX);
                enemy.setY(enemy.getY() + changeY);
            }
            if (randomNumber2 ==5) {
                getBulletSPIs().stream().findFirst().ifPresent(
                        spi -> {world.addEntity(spi.createBullet(enemy, gameData));}
                );
            }

            if (enemy.getX() < 0) {
                enemy.setX(1);
                enemy.setRotation(enemy.getRotation() + 180);
            }

            if (enemy.getX() > gameData.getDisplayWidth()) {
                enemy.setX(gameData.getDisplayWidth()-1);
                enemy.setRotation(enemy.getRotation() + 180);
            }

            if (enemy.getY() < 0) {
                enemy.setY(1);
                enemy.setRotation(enemy.getRotation() + 180);
            }

            if (enemy.getY() > gameData.getDisplayHeight()) {
                enemy.setY(gameData.getDisplayHeight()-1);
                enemy.setRotation(enemy.getRotation() + 180);
            }

            lifePart.process(gameData, enemy);

        }

        // Spawning enemies that is round * 2
        if (!world.isRoundRunning()){
            for (int i = 0; i < world.getRound()*2; i++) {
                Enemy enemies;
                enemies = createEnemyShip(gameData);
                world.addEntity(enemies);
            }
            world.setRoundRunning(true);
        }

//        if (randomInt ==1) {
//
//            Entity enemies;
//            enemies = createEnemyShip(gameData);
//            world.addEntity(enemies);
//        }

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
