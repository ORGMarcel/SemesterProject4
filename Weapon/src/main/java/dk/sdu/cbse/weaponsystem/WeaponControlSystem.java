package dk.sdu.cbse.weaponsystem;

//import dk.sdu.mmmi.cbse.common.bullet.BulletSPI;

import dk.sdu.cbse.common.player.Player;
import dk.sdu.cbse.commonbullet.BulletSPI;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.GameKeys;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

import java.util.Collection;
import java.util.ServiceLoader;

import static java.util.stream.Collectors.toList;


public class WeaponControlSystem implements IEntityProcessingService {

    @Override
    public void process(GameData gameData, World world) {
            
        for (Entity weapon : world.getEntities(Weapon.class)) {
            for(Entity player : world.getEntities(Player.class)){
                weapon.setY(player.getY());
                weapon.setX(player.getX());
                weapon.setRotation(player.getRotation());
                double changeX = Math.cos(Math.toRadians(weapon.getRotation()));
                double changeY = Math.sin(Math.toRadians(weapon.getRotation()));
                weapon.setX(weapon.getX() + changeX * 15);
                weapon.setY(weapon.getY() + changeY * 15);

            }


            // Gravity
//            weapon.setY(weapon.getY()+1);



            // Controlling
            if (gameData.getKeys().isDown(GameKeys.LEFT)) {
//                weapon.setX(weapon.getX()-5);
//                player.setRotation(player.getRotation() - 5);
            }
            if (gameData.getKeys().isDown(GameKeys.RIGHT)) {
//                weapon.setX(weapon.getX()+5);
//                player.setRotation(player.getRotation() + 5);
            }
            if (gameData.getKeys().isPressed(GameKeys.UP)) {
//                player.setY(player.getY()-50);
//                Thread jumpThread = new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        for (int i = 0; i < 100; i++) {
//                            weapon.setY(weapon.getY()-1);
//
//                            try {
//                                Thread.sleep(5);
//                                System.out.println("Sleep");
//                            } catch (InterruptedException e) {
//                                throw new RuntimeException(e);
//                            }
//                        }
//                    }
//                });
//
//                jumpThread.start(); // Start the thread


//                double changeX = Math.cos(Math.toRadians(player.getRotation()));
//                double changeY = Math.sin(Math.toRadians(player.getRotation()));
//                player.setX(player.getX() + changeX);
//                player.setY(player.getY() + changeY);
            }
            if(gameData.getKeys().isPressed(GameKeys.SPACE)) {
                getBulletSPIs().stream().findFirst().ifPresent(
                        spi -> {world.addEntity(spi.createBullet(weapon, gameData));}
                );
            }
            
        if (weapon.getX() < 0) {
            weapon.setX(1);
        }

        if (weapon.getX() > gameData.getDisplayWidth()) {
            weapon.setX(gameData.getDisplayWidth()-1);
        }

        if (weapon.getY() < 0) {
            weapon.setY(1);
        }

        if (weapon.getY() > gameData.getDisplayHeight()) {
            weapon.setY(gameData.getDisplayHeight()-1);
        }

                                        
        }
    }

    private Collection<? extends BulletSPI> getBulletSPIs() {
        return ServiceLoader.load(BulletSPI.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }
}
