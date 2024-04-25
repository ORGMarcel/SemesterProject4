package dk.sdu.mmmi.cbse.weaponshotgun;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.GameKeys;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.commonbullet.BulletSPI;
import dk.sdu.mmmi.cbse.commonplayer.Player;
import dk.sdu.mmmi.cbse.commonweapon.Weapon;

import java.util.Collection;
import java.util.ServiceLoader;

import static java.util.stream.Collectors.toList;

public class ShotgunWeaponControlSystem implements IEntityProcessingService {

    @Override
    public void process(GameData gameData, World world) {

        // Find the player entity
        // Assuming there's only one player entity
        Entity player = world.getEntities(Player.class).stream().findFirst().orElse(null);

        // If no player entity is found, exit the method
        if (player == null) {
            return;
        }

        for (Entity weapon : world.getEntities(Weapon.class)) {
            // Set weapon position and rotation based on player
            weapon.setY(player.getY());
            weapon.setX(player.getX());
            weapon.setRotation(player.getRotation() + 15);
            double changeX = Math.cos(Math.toRadians(weapon.getRotation()));
            double changeY = Math.sin(Math.toRadians(weapon.getRotation()));
            weapon.setX(weapon.getX() + changeX * 15);
            weapon.setY(weapon.getY() + changeY * 15);

            // Controlling
            if (gameData.getKeys().isPressed(GameKeys.SPACE)) {
                Weapon weapon1 = (Weapon) weapon;

                Thread thread1 = new Thread(() -> {
                    weapon1.setShooting(true);

                    try {
                        for (int i = 0; i < 5; i++) {
                            // Execute the action
                            double bulletRotation = player.getRotation() + (-2*10)+i*10;
                            getBulletSPIs().stream().findFirst().ifPresent(
                                    spi -> {
                                        Entity bullet = spi.createBullet(weapon, gameData);
                                        bullet.setRotation(bulletRotation);
                                        world.addEntity(bullet);
                                    }
                            );
                        }
                        // Wait for 0.2 seconds after completing the loop
                        Thread.sleep(200);
                        weapon1.setShooting(false);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt(); // Properly handle thread interruption
                        e.printStackTrace();
                    }

                });
                if (!weapon1.isShooting()) {
                    thread1.start();
                }

            }

            // Check if weapon is out of bounds
            if (weapon.getX() < 0) {
                weapon.setX(1);
            }
            if (weapon.getX() > gameData.getDisplayWidth()) {
                weapon.setX(gameData.getDisplayWidth() - 1);
            }
            if (weapon.getY() < 0) {
                weapon.setY(1);
            }
            if (weapon.getY() > gameData.getDisplayHeight()) {
                weapon.setY(gameData.getDisplayHeight() - 1);
            }
        }
    }

    private Collection<? extends BulletSPI> getBulletSPIs() {
        return ServiceLoader.load(BulletSPI.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }


}
