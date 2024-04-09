package dk.sdu.mmmi.playersystem;

//import dk.sdu.mmmi.cbse.common.bullet.BulletSPI;
import dk.sdu.cbse.commonbullet.BulletSPI;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.GameKeys;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

import java.util.Collection;
import java.util.ServiceLoader;

import static java.util.stream.Collectors.toList;


public class PlayerControlSystem implements IEntityProcessingService {

    @Override
    public void process(GameData gameData, World world) {
            
        for (Entity player : world.getEntities(Player.class)) {


            // Gravity
            player.setY(player.getY()+1);



            // Controlling
            if (gameData.getKeys().isDown(GameKeys.LEFT)) {
                player.setX(player.getX()-5);
//                player.setRotation(player.getRotation() - 5);
                player.setRotation(180);

                Entity playerShip = new Player();
                playerShip.setPolygonCoordinates(-9.4, -11.84, 6.36, 6.0, -12.4, -10.16, -13.84, -13.36, -9.32, -13.24, 3.2, 0.16, 16.0, -12.0, 20.72, -11.96, 19.16, -9.16, 16.08, -10.56, 4.64, 0.44, 8.24, 5.44, 12.88, 3.44, 15.8, -1.0, 13.52, -2.6, 16.2, -2.0, 19.32, -3.76, 18.76, -1.72, 16.8, -0.32, 13.92, 4.28, 9.8, 6.36, 13.2, 8.28, 14.76, 10.64, 14.88, 13.92, 13.08, 16.48, 10.2, 17.84, 7.12, 17.48, 5.48, 16.36, 2.68, 18.52, -1.08, 18.12, -3.0, 18.56, -1.16, 17.08, 2.56, 17.68, 3.96, 16.56, 4.4, 15.0, 1.48, 15.48, -1.56, 16.12, -6.12, 14.8, -4.64, 14.08, -1.6, 15.12, 3.84, 13.92, 3.76, 11.0, 5.28, 8.56, 7.4, 7.16, 1.28, 9.64, -1.16, 9.84, -4.0, 8.0, -7.36, 7.28, -8.48, 5.52, -7.36, 4.88, -5.76, 6.8, -5.72, 4.72, -4.08, 6.36, -2.6, 5.56, -1.16, 5.84, -2.76, 7.88, -0.76, 9.0);
                playerShip.setX(player.getX());
                playerShip.setY(player.getY());
                playerShip.setRotation(player.getRotation());
                playerShip.setHealthPoints(player.getHealthPoints());
                playerShip.setDmg(player.getDmg());

                for (Entity entityPlayer : world.getEntities(Player.class)) {
                    world.removeEntity(entityPlayer);
                }

                world.addEntity(playerShip);
//                world.removeEntity(player);
            }
            if (gameData.getKeys().isDown(GameKeys.RIGHT)) {
                player.setX(player.getX()+5);
//                player.setRotation(player.getRotation() + 5);
                player.setRotation(360);

                Entity playerShip = new Player();
                playerShip.setPolygonCoordinates(-9.4, 11.84, 6.36, -6.0, -12.4, 10.16, -13.84, 13.36, -9.32, 13.24, 3.2, -0.16, 16.0, 12.0, 20.72, 11.96, 19.16, 9.16, 16.08, 10.56, 4.64, -0.44, 8.24, -5.44, 12.88, -3.44, 15.8, 1.0, 13.52, 2.6, 16.2, 2.0, 19.32, 3.76, 18.76, 1.72, 16.8, 0.32, 13.92, -4.28, 9.8, -6.36, 13.2, -8.28, 14.76, -10.64, 14.88, -13.92, 13.08, -16.48, 10.2, -17.84, 7.12, -17.48, 5.48, -16.36, 2.68, -18.52, -1.08, -18.12, -3.0, -18.56, -1.16, -17.08, 2.56, -17.68, 3.96, -16.56, 4.4, -15.0, 1.48, -15.48, -1.56, -16.12, -6.12, -14.8, -4.64, -14.08, -1.6, -15.12, 3.84, -13.92, 3.76, -11.0, 5.28, -8.56, 7.4, -7.16, 1.28, -9.64, -1.16, -9.84, -4.0, -8.0, -7.36, -7.28, -8.48, -5.52, -7.36, -4.88, -5.76, -6.8, -5.72, -4.72, -4.08, -6.36, -2.6, -5.56, -1.16, -5.84, -2.76, -7.88, -0.76, -9.0);
                playerShip.setX(player.getX());
                playerShip.setY(player.getY());
                playerShip.setRotation(player.getRotation());
                playerShip.setHealthPoints(player.getHealthPoints());
                playerShip.setDmg(player.getDmg());

                for (Entity entityPlayer : world.getEntities(Player.class)) {
                    world.removeEntity(entityPlayer);
                }

                world.addEntity(playerShip);
//                world.removeEntity(player);
            }
            if (gameData.getKeys().isPressed(GameKeys.UP)) {
//                player.setY(player.getY()-50);
                Thread jumpThread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < 100; i++) {
                            player.setY(player.getY()-1);

                            try {
                                Thread.sleep(5);
//                                System.out.println("Sleep");
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }
                });

                jumpThread.start(); // Start the thread


//                double changeX = Math.cos(Math.toRadians(player.getRotation()));
//                double changeY = Math.sin(Math.toRadians(player.getRotation()));
//                player.setX(player.getX() + changeX);
//                player.setY(player.getY() + changeY);
            }
//            if(gameData.getKeys().isPressed(GameKeys.SPACE)) {
//                getBulletSPIs().stream().findFirst().ifPresent(
//                        spi -> {world.addEntity(spi.createBullet(player, gameData));}
//                );
//            }
            
        if (player.getX() < 0) {
            player.setX(1);
        }

        if (player.getX() > gameData.getDisplayWidth()) {
            player.setX(gameData.getDisplayWidth()-1);
        }

        if (player.getY() < 0) {
            player.setY(1);
        }

        if (player.getY() > gameData.getDisplayHeight()) {
            player.setY(gameData.getDisplayHeight()-1);
        }

                                        
        }
    }



    private Collection<? extends BulletSPI> getBulletSPIs() {
        return ServiceLoader.load(BulletSPI.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }
}
