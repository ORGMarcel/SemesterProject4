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
import dk.sdu.mmmi.cbse.commonweapon.Weapon;

import java.util.Collection;
import java.util.List;
import java.util.ServiceLoader;

import static java.util.stream.Collectors.toList;


public class PlayerControlSystem implements IEntityProcessingService {

    @Override
    public void process(GameData gameData, World world) {


        for (Entity x : world.getEntities(Player.class)) {
            Player ninja = (Player) x;
            LifePart lifePart = x.getPart(LifePart.class);
            MovingPart movingPart = x.getPart(MovingPart.class);


            if (lifePart.getLife() <= 0) {
                world.removeEntity(ninja);
            }

            if (movingPart.getAcceleration() > 2) {
                movingPart.setJumping(false);
            }

            ninja.setY(ninja.getY() + movingPart.getAcceleration());


            // Controlling
            if (gameData.getKeys().isDown(GameKeys.LEFT)) {


                if (!movingPart.isAtObstacle()) {
                    ninja.setX(ninja.getX() - 3);
                    ninja.setRotation(180);
                    double[] targetArray = {-9.4, -11.84, 6.36, 6.0, -12.4, -10.16, -13.84, -13.36, -9.32, -13.24, 3.2, 0.16, 16.0, -12.0, 20.72, -11.96, 19.16, -9.16, 16.08, -10.56, 4.64, 0.44, 8.24, 5.44, 12.88, 3.44, 15.8, -1.0, 13.52, -2.6, 16.2, -2.0, 19.32, -3.76, 18.76, -1.72, 16.8, -0.32, 13.92, 4.28, 9.8, 6.36, 13.2, 8.28, 14.76, 10.64, 14.88, 13.92, 13.08, 16.48, 10.2, 17.84, 7.12, 17.48, 5.48, 16.36, 2.68, 18.52, -1.08, 18.12, -3.0, 18.56, -1.16, 17.08, 2.56, 17.68, 3.96, 16.56, 4.4, 15.0, 1.48, 15.48, -1.56, 16.12, -6.12, 14.8, -4.64, 14.08, -1.6, 15.12, 3.84, 13.92, 3.76, 11.0, 5.28, 8.56, 7.4, 7.16, 1.28, 9.64, -1.16, 9.84, -4.0, 8.0, -7.36, 7.28, -8.48, 5.52, -7.36, 4.88, -5.76, 6.8, -5.72, 4.72, -4.08, 6.36, -2.6, 5.56, -1.16, 5.84, -2.76, 7.88, -0.76, 9.0};

                    if (!areEqual(targetArray, ninja.getPolygonCoordinates())) {
                        Player playerNinja = new Player();
                        LifePart tempLifePart = ninja.getPart(LifePart.class);
                        playerNinja.add(new LifePart(tempLifePart.getLife()));
                        playerNinja.setPolygonCoordinates(-9.4, -11.84, 6.36, 6.0, -12.4, -10.16, -13.84, -13.36, -9.32, -13.24, 3.2, 0.16, 16.0, -12.0, 20.72, -11.96, 19.16, -9.16, 16.08, -10.56, 4.64, 0.44, 8.24, 5.44, 12.88, 3.44, 15.8, -1.0, 13.52, -2.6, 16.2, -2.0, 19.32, -3.76, 18.76, -1.72, 16.8, -0.32, 13.92, 4.28, 9.8, 6.36, 13.2, 8.28, 14.76, 10.64, 14.88, 13.92, 13.08, 16.48, 10.2, 17.84, 7.12, 17.48, 5.48, 16.36, 2.68, 18.52, -1.08, 18.12, -3.0, 18.56, -1.16, 17.08, 2.56, 17.68, 3.96, 16.56, 4.4, 15.0, 1.48, 15.48, -1.56, 16.12, -6.12, 14.8, -4.64, 14.08, -1.6, 15.12, 3.84, 13.92, 3.76, 11.0, 5.28, 8.56, 7.4, 7.16, 1.28, 9.64, -1.16, 9.84, -4.0, 8.0, -7.36, 7.28, -8.48, 5.52, -7.36, 4.88, -5.76, 6.8, -5.72, 4.72, -4.08, 6.36, -2.6, 5.56, -1.16, 5.84, -2.76, 7.88, -0.76, 9.0);
                        playerNinja.setX(ninja.getX());
                        playerNinja.setY(ninja.getY());
                        playerNinja.setRotation(ninja.getRotation());
                        movingPart.setJumping(movingPart.isJumping());
                        playerNinja.setEquippedWeapon(ninja.getEquippedWeapon());
                        playerNinja.setCurrentWeapon(ninja.getCurrentWeapon());
                        playerNinja.setInventory(ninja.getInventory());

                        for (Entity entityPlayer : world.getEntities(Player.class)) {
                            world.removeEntity(entityPlayer);
                        }

                        world.addEntity(playerNinja);
                    }
                } else {
                    ninja.setX(ninja.getX() + 6);
                }


            }
            if (gameData.getKeys().isDown(GameKeys.RIGHT)) {

                if (!movingPart.isAtObstacle()) {
                    ninja.setX(ninja.getX() + 3);
                    ninja.setRotation(360);
                    double[] targetArray = {-9.4, 11.84, 6.36, -6.0, -12.4, 10.16, -13.84, 13.36, -9.32, 13.24, 3.2, -0.16, 16.0, 12.0, 20.72, 11.96, 19.16, 9.16, 16.08, 10.56, 4.64, -0.44, 8.24, -5.44, 12.88, -3.44, 15.8, 1.0, 13.52, 2.6, 16.2, 2.0, 19.32, 3.76, 18.76, 1.72, 16.8, 0.32, 13.92, -4.28, 9.8, -6.36, 13.2, -8.28, 14.76, -10.64, 14.88, -13.92, 13.08, -16.48, 10.2, -17.84, 7.12, -17.48, 5.48, -16.36, 2.68, -18.52, -1.08, -18.12, -3.0, -18.56, -1.16, -17.08, 2.56, -17.68, 3.96, -16.56, 4.4, -15.0, 1.48, -15.48, -1.56, -16.12, -6.12, -14.8, -4.64, -14.08, -1.6, -15.12, 3.84, -13.92, 3.76, -11.0, 5.28, -8.56, 7.4, -7.16, 1.28, -9.64, -1.16, -9.84, -4.0, -8.0, -7.36, -7.28, -8.48, -5.52, -7.36, -4.88, -5.76, -6.8, -5.72, -4.72, -4.08, -6.36, -2.6, -5.56, -1.16, -5.84, -2.76, -7.88, -0.76, -9.0};

                    if (!areEqual(targetArray, ninja.getPolygonCoordinates())) {
                        Player playerninja = new Player();
                        LifePart tempLifePart = ninja.getPart(LifePart.class);
                        playerninja.add(new LifePart(tempLifePart.getLife()));
                        playerninja.setPolygonCoordinates(-9.4, 11.84, 6.36, -6.0, -12.4, 10.16, -13.84, 13.36, -9.32, 13.24, 3.2, -0.16, 16.0, 12.0, 20.72, 11.96, 19.16, 9.16, 16.08, 10.56, 4.64, -0.44, 8.24, -5.44, 12.88, -3.44, 15.8, 1.0, 13.52, 2.6, 16.2, 2.0, 19.32, 3.76, 18.76, 1.72, 16.8, 0.32, 13.92, -4.28, 9.8, -6.36, 13.2, -8.28, 14.76, -10.64, 14.88, -13.92, 13.08, -16.48, 10.2, -17.84, 7.12, -17.48, 5.48, -16.36, 2.68, -18.52, -1.08, -18.12, -3.0, -18.56, -1.16, -17.08, 2.56, -17.68, 3.96, -16.56, 4.4, -15.0, 1.48, -15.48, -1.56, -16.12, -6.12, -14.8, -4.64, -14.08, -1.6, -15.12, 3.84, -13.92, 3.76, -11.0, 5.28, -8.56, 7.4, -7.16, 1.28, -9.64, -1.16, -9.84, -4.0, -8.0, -7.36, -7.28, -8.48, -5.52, -7.36, -4.88, -5.76, -6.8, -5.72, -4.72, -4.08, -6.36, -2.6, -5.56, -1.16, -5.84, -2.76, -7.88, -0.76, -9.0);
                        playerninja.setX(ninja.getX());
                        playerninja.setY(ninja.getY());
                        playerninja.setRotation(ninja.getRotation());
                        movingPart.setJumping(movingPart.isJumping());
                        playerninja.setEquippedWeapon(ninja.getEquippedWeapon());
                        playerninja.setCurrentWeapon(ninja.getCurrentWeapon());
                        playerninja.setInventory(ninja.getInventory());


                        for (Entity entityPlayer : world.getEntities(Player.class)) {
                            world.removeEntity(entityPlayer);
                        }

                        world.addEntity(playerninja);
                    }
                } else {
                    ninja.setX(ninja.getX() - 6);
                }


            }

            if (gameData.getKeys().isPressed(GameKeys.UP)) {
                if (!movingPart.isJumping() && !movingPart.isAtObstacle()) {
                    movingPart.setAcceleration(-7);
                    movingPart.setJumping(true);
                }

            }

            if (ninja.getX() < 0) {
                ninja.setX(1);
            }

            if (ninja.getX() > gameData.getDisplayWidth()) {
                ninja.setX(gameData.getDisplayWidth() - 1);
            }

            if (ninja.getY() < 0) {
                ninja.setY(1);
            }

            if (ninja.getY() > gameData.getDisplayHeight()) {
                ninja.setY(gameData.getDisplayHeight() - 1);
            }


            lifePart.process(gameData, ninja);
            movingPart.process(gameData, ninja);

            // Using Sorting algorithm to sort the inventory
            List<Weapon> playerInventory = ninja.getInventory();
            playerInventory = sortInventory(playerInventory);


            if (gameData.getKeys().isPressed(GameKeys.NUM1)) {
                if (ninja.getInventory().size() > 0) {
                    System.out.println("Switched weapon to 1");
                    System.out.println(ninja.getCurrentWeapon());
                    playerInventory = ninja.getInventory();
                    for (int i = 0; i < playerInventory.size(); i++) {
                        System.out.println(playerInventory.get(i).getClass().getName());
                    }


                    world.removeEntity(playerInventory.get(ninja.getCurrentWeapon()));
                    if (ninja.getEquippedWeapon() != null) {
                        ninja.getEquippedWeapon().setEquipped(false);
                        world.removeEntity(ninja.getEquippedWeapon());
                    }

                    ninja.setCurrentWeapon(0);
                    Weapon weapon = ninja.getInventory().get(ninja.getCurrentWeapon());
                    world.addEntity(weapon);
                    ninja.setEquippedWeapon(weapon);
                    weapon.setEquipped(true);
                }


            }
            if (gameData.getKeys().isPressed(GameKeys.NUM2)) {
                if (ninja.getInventory().size() > 1) {

                    System.out.println("Switched weapon to 2");
                    playerInventory = ninja.getInventory();

                    world.removeEntity(playerInventory.get(ninja.getCurrentWeapon()));
                    if (ninja.getEquippedWeapon() != null) {
                        ninja.getEquippedWeapon().setEquipped(false);
                        world.removeEntity(ninja.getEquippedWeapon());
                    }
                    ninja.setCurrentWeapon(1);
                    Weapon weapon = ninja.getInventory().get(ninja.getCurrentWeapon());

                    world.addEntity(weapon);
                    ninja.setEquippedWeapon(weapon);
                    weapon.setEquipped(true);
                }


            }
            if (gameData.getKeys().isPressed(GameKeys.NUM3)) {
                if (ninja.getInventory().size() > 2) {
                    playerInventory = ninja.getInventory();


                    System.out.println("Switched weapon to 3");

                    world.removeEntity(playerInventory.get(ninja.getCurrentWeapon()));
                    if (ninja.getEquippedWeapon() != null) {
                        ninja.getEquippedWeapon().setEquipped(false);
                        world.removeEntity(ninja.getEquippedWeapon());
                    }
                    ninja.setCurrentWeapon(2);
                    Weapon weapon = ninja.getInventory().get(ninja.getCurrentWeapon());

                    world.addEntity(weapon);
                    ninja.setEquippedWeapon(weapon);
                    weapon.setEquipped(true);
                }


            }
            if (gameData.getKeys().isPressed(GameKeys.NUM4)) {
                if (ninja.getInventory().size() > 3) {
                    playerInventory = ninja.getInventory();

                    System.out.println("Switched weapon to 4");

                    world.removeEntity(playerInventory.get(ninja.getCurrentWeapon()));
                    if (ninja.getEquippedWeapon() != null) {
                        ninja.getEquippedWeapon().setEquipped(false);
                        world.removeEntity(ninja.getEquippedWeapon());
                    }
                    ninja.setCurrentWeapon(3);
                    Weapon weapon = ninja.getInventory().get(ninja.getCurrentWeapon());

                    world.addEntity(weapon);
                    ninja.setEquippedWeapon(weapon);
                    weapon.setEquipped(true);
                }


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

    public List<Weapon> sortInventory(List<Weapon> inventory) {
        for (int i = 0; i < inventory.size(); i++) {
            for (int j = i + 1; j < inventory.size(); j++) {
                if (inventory.get(i).getDurability() < inventory.get(j).getDurability()) {
                    Weapon temp = inventory.get(i);
                    inventory.set(i, inventory.get(j));
                    inventory.set(j, temp);
                }
            }
        }
        return inventory;

    }


    // TODO: We dont use this because weapon shoots now
    private Collection<? extends BulletSPI> getBulletSPIs() {
        return ServiceLoader.load(BulletSPI.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }
}
