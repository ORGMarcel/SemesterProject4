package dk.sdu.mmmi.cbse.playersystem;

//import dk.sdu.mmmi.cbse.common.bullet.BulletSPI;

import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.commonplayer.Player;
import dk.sdu.mmmi.cbse.commonbullet.BulletSPI;
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


        for (Entity x : world.getEntities(Player.class)) {
//            System.out.println(player.isJumping());
            Player player = (Player) x;
            LifePart lifePart = x.getPart(LifePart.class);
            MovingPart movingPart = x.getPart(MovingPart.class);


            if (lifePart.getLife() <= 0) {
                world.removeEntity(player);
            }


//            // Gravity
//            if(player.getGravity()<2){
//                player.setGravity(player.getGravity()+0.1);
//            }
            if (movingPart.getAcceleration() > 2) {
                movingPart.setJumping(false);
            }
//
//            player.setY(player.getY()+player.getGravity());
            player.setY(player.getY() + movingPart.getAcceleration());


            // Controlling
            if (gameData.getKeys().isDown(GameKeys.LEFT)) {


                if (!movingPart.isAtObstacle()) {
                    player.setX(player.getX() - 5);
//                player.setRotation(player.getRotation() - 5);
                    player.setRotation(180);
                    double[] targetArray = {-9.4, -11.84, 6.36, 6.0, -12.4, -10.16, -13.84, -13.36, -9.32, -13.24, 3.2, 0.16, 16.0, -12.0, 20.72, -11.96, 19.16, -9.16, 16.08, -10.56, 4.64, 0.44, 8.24, 5.44, 12.88, 3.44, 15.8, -1.0, 13.52, -2.6, 16.2, -2.0, 19.32, -3.76, 18.76, -1.72, 16.8, -0.32, 13.92, 4.28, 9.8, 6.36, 13.2, 8.28, 14.76, 10.64, 14.88, 13.92, 13.08, 16.48, 10.2, 17.84, 7.12, 17.48, 5.48, 16.36, 2.68, 18.52, -1.08, 18.12, -3.0, 18.56, -1.16, 17.08, 2.56, 17.68, 3.96, 16.56, 4.4, 15.0, 1.48, 15.48, -1.56, 16.12, -6.12, 14.8, -4.64, 14.08, -1.6, 15.12, 3.84, 13.92, 3.76, 11.0, 5.28, 8.56, 7.4, 7.16, 1.28, 9.64, -1.16, 9.84, -4.0, 8.0, -7.36, 7.28, -8.48, 5.52, -7.36, 4.88, -5.76, 6.8, -5.72, 4.72, -4.08, 6.36, -2.6, 5.56, -1.16, 5.84, -2.76, 7.88, -0.76, 9.0};

                    if (!areEqual(targetArray, player.getPolygonCoordinates())) {
                        Player playerShip = new Player();
                        LifePart tempLifePart = player.getPart(LifePart.class);
                        playerShip.add(new LifePart(tempLifePart.getLife()));
//                    playerShip.add(new AccelerationPart());
                        playerShip.setPolygonCoordinates(-9.4, -11.84, 6.36, 6.0, -12.4, -10.16, -13.84, -13.36, -9.32, -13.24, 3.2, 0.16, 16.0, -12.0, 20.72, -11.96, 19.16, -9.16, 16.08, -10.56, 4.64, 0.44, 8.24, 5.44, 12.88, 3.44, 15.8, -1.0, 13.52, -2.6, 16.2, -2.0, 19.32, -3.76, 18.76, -1.72, 16.8, -0.32, 13.92, 4.28, 9.8, 6.36, 13.2, 8.28, 14.76, 10.64, 14.88, 13.92, 13.08, 16.48, 10.2, 17.84, 7.12, 17.48, 5.48, 16.36, 2.68, 18.52, -1.08, 18.12, -3.0, 18.56, -1.16, 17.08, 2.56, 17.68, 3.96, 16.56, 4.4, 15.0, 1.48, 15.48, -1.56, 16.12, -6.12, 14.8, -4.64, 14.08, -1.6, 15.12, 3.84, 13.92, 3.76, 11.0, 5.28, 8.56, 7.4, 7.16, 1.28, 9.64, -1.16, 9.84, -4.0, 8.0, -7.36, 7.28, -8.48, 5.52, -7.36, 4.88, -5.76, 6.8, -5.72, 4.72, -4.08, 6.36, -2.6, 5.56, -1.16, 5.84, -2.76, 7.88, -0.76, 9.0);
                        playerShip.setX(player.getX());
                        playerShip.setY(player.getY());
                        playerShip.setRotation(player.getRotation());
                        movingPart.setJumping(movingPart.isJumping());


                        for (Entity entityPlayer : world.getEntities(Player.class)) {
                            world.removeEntity(entityPlayer);
                        }

                        world.addEntity(playerShip);
//                world.removeEntity(player);
                    }
                } else {
                    player.setX(player.getX() + 6);
                }


            }
            if (gameData.getKeys().isDown(GameKeys.RIGHT)) {

                if (!movingPart.isAtObstacle()) {
                    player.setX(player.getX() + 5);
//                player.setRotation(player.getRotation() + 5);
                    player.setRotation(360);
                    double[] targetArray = {-9.4, 11.84, 6.36, -6.0, -12.4, 10.16, -13.84, 13.36, -9.32, 13.24, 3.2, -0.16, 16.0, 12.0, 20.72, 11.96, 19.16, 9.16, 16.08, 10.56, 4.64, -0.44, 8.24, -5.44, 12.88, -3.44, 15.8, 1.0, 13.52, 2.6, 16.2, 2.0, 19.32, 3.76, 18.76, 1.72, 16.8, 0.32, 13.92, -4.28, 9.8, -6.36, 13.2, -8.28, 14.76, -10.64, 14.88, -13.92, 13.08, -16.48, 10.2, -17.84, 7.12, -17.48, 5.48, -16.36, 2.68, -18.52, -1.08, -18.12, -3.0, -18.56, -1.16, -17.08, 2.56, -17.68, 3.96, -16.56, 4.4, -15.0, 1.48, -15.48, -1.56, -16.12, -6.12, -14.8, -4.64, -14.08, -1.6, -15.12, 3.84, -13.92, 3.76, -11.0, 5.28, -8.56, 7.4, -7.16, 1.28, -9.64, -1.16, -9.84, -4.0, -8.0, -7.36, -7.28, -8.48, -5.52, -7.36, -4.88, -5.76, -6.8, -5.72, -4.72, -4.08, -6.36, -2.6, -5.56, -1.16, -5.84, -2.76, -7.88, -0.76, -9.0};

                    if (!areEqual(targetArray, player.getPolygonCoordinates())) {
                        Player playerShip = new Player();
                        LifePart tempLifePart = player.getPart(LifePart.class);
                        playerShip.add(new LifePart(tempLifePart.getLife()));
//                    playerShip.add(new AccelerationPart());
                        playerShip.setPolygonCoordinates(-9.4, 11.84, 6.36, -6.0, -12.4, 10.16, -13.84, 13.36, -9.32, 13.24, 3.2, -0.16, 16.0, 12.0, 20.72, 11.96, 19.16, 9.16, 16.08, 10.56, 4.64, -0.44, 8.24, -5.44, 12.88, -3.44, 15.8, 1.0, 13.52, 2.6, 16.2, 2.0, 19.32, 3.76, 18.76, 1.72, 16.8, 0.32, 13.92, -4.28, 9.8, -6.36, 13.2, -8.28, 14.76, -10.64, 14.88, -13.92, 13.08, -16.48, 10.2, -17.84, 7.12, -17.48, 5.48, -16.36, 2.68, -18.52, -1.08, -18.12, -3.0, -18.56, -1.16, -17.08, 2.56, -17.68, 3.96, -16.56, 4.4, -15.0, 1.48, -15.48, -1.56, -16.12, -6.12, -14.8, -4.64, -14.08, -1.6, -15.12, 3.84, -13.92, 3.76, -11.0, 5.28, -8.56, 7.4, -7.16, 1.28, -9.64, -1.16, -9.84, -4.0, -8.0, -7.36, -7.28, -8.48, -5.52, -7.36, -4.88, -5.76, -6.8, -5.72, -4.72, -4.08, -6.36, -2.6, -5.56, -1.16, -5.84, -2.76, -7.88, -0.76, -9.0);
                        playerShip.setX(player.getX());
                        playerShip.setY(player.getY());
                        playerShip.setRotation(player.getRotation());
                        movingPart.setJumping(movingPart.isJumping());

                        for (Entity entityPlayer : world.getEntities(Player.class)) {
                            world.removeEntity(entityPlayer);
                        }

                        world.addEntity(playerShip);
//                world.removeEntity(player);
                    }
                } else {
                    player.setX(player.getX() - 6);
                }


            }

            if (gameData.getKeys().isPressed(GameKeys.UP)) {
                if (!movingPart.isJumping() && !movingPart.isAtObstacle()) {
//                    player.setGravity(-5);
                    movingPart.setAcceleration(-5);
                    movingPart.setJumping(true);
                }

            }

            if (player.getX() < 0) {
                player.setX(1);
            }

            if (player.getX() > gameData.getDisplayWidth()) {
                player.setX(gameData.getDisplayWidth() - 1);
            }

            if (player.getY() < 0) {
                player.setY(1);
            }

            if (player.getY() > gameData.getDisplayHeight()) {
                player.setY(gameData.getDisplayHeight() - 1);
            }


            lifePart.process(gameData, player);
            movingPart.process(gameData, player);


            if (gameData.getKeys().isPressed(GameKeys.NUM1)) {
                world.removeEntity(player.getInventory()[player.getCurrentWeapon()]);
                player.setCurrentWeapon(1);
                world.addEntity(player.getInventory()[player.getCurrentWeapon()]);
            }
            if (gameData.getKeys().isPressed(GameKeys.NUM2)) {
                world.removeEntity(player.getInventory()[player.getCurrentWeapon()]);
                player.setCurrentWeapon(2);
                world.addEntity(player.getInventory()[player.getCurrentWeapon()]);
            }
            if (gameData.getKeys().isPressed(GameKeys.NUM3)) {
                world.removeEntity(player.getInventory()[player.getCurrentWeapon()]);
                player.setCurrentWeapon(3);
                world.addEntity(player.getInventory()[player.getCurrentWeapon()]);
            }
            if (gameData.getKeys().isPressed(GameKeys.NUM4)) {
                world.removeEntity(player.getInventory()[player.getCurrentWeapon()]);
                player.setCurrentWeapon(4);
                world.addEntity(player.getInventory()[player.getCurrentWeapon()]);
            }


        }


    }

    public static boolean areEqual(double[] array1, double[] array2) {
        // Check if the arrays have the same length
        if (array1.length != array2.length) {
            return false;
        }

        // Compare each element
        for (int i = 0; i < array1.length; i++) {
            if (array1[i] != array2[i]) {
                return false;
            }
        }

        // If we've made it here, the arrays are the same
        return true;
    }


    // TODO: We dont use this because weapon shoots now
    private Collection<? extends BulletSPI> getBulletSPIs() {
        return ServiceLoader.load(BulletSPI.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }
}
