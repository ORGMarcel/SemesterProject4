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
            }
            if (gameData.getKeys().isDown(GameKeys.RIGHT)) {
                player.setX(player.getX()+5);
//                player.setRotation(player.getRotation() + 5);
                player.setRotation(360);
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
                                System.out.println("Sleep");
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
