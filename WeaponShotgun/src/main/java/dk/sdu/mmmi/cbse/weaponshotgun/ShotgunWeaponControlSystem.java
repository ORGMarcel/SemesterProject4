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

        for (Entity weaponShotgun : world.getEntities(Shotgun.class)) {
            // Set weapon position and rotation based on player
            for (Entity player : world.getEntities(Player.class)) {
                weaponShotgun.setY(player.getY());
                weaponShotgun.setX(player.getX());
                weaponShotgun.setRotation(player.getRotation());
                double changeX = Math.cos(Math.toRadians(weaponShotgun.getRotation()));
                double changeY = Math.sin(Math.toRadians(weaponShotgun.getRotation()));
                weaponShotgun.setX(weaponShotgun.getX() + changeX * 15);
                weaponShotgun.setY(weaponShotgun.getY() + changeY * 15);


                // Controlling
                if (gameData.getKeys().isPressed(GameKeys.SPACE)) {
                    Shotgun weapon1 = (Shotgun) weaponShotgun;

                    Thread thread1 = new Thread(() -> {
                        weapon1.setShooting(true);

                        try {
                            for (int i = 0; i < 5; i++) {
                                // Execute the action
                                double bulletRotation = player.getRotation() + (-2 * 10) + i * 10;
                                getBulletSPIs().stream().findFirst().ifPresent(
                                        spi -> {
                                            Entity bullet = spi.createBullet(weaponShotgun, gameData);
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
                if (weaponShotgun.getX() < 0) {
                    weaponShotgun.setX(1);
                }
                if (weaponShotgun.getX() > gameData.getDisplayWidth()) {
                    weaponShotgun.setX(gameData.getDisplayWidth() - 1);
                }
                if (weaponShotgun.getY() < 0) {
                    weaponShotgun.setY(1);
                }
                if (weaponShotgun.getY() > gameData.getDisplayHeight()) {
                    weaponShotgun.setY(gameData.getDisplayHeight() - 1);
                }
            }
        }
    }

    private Collection<? extends BulletSPI> getBulletSPIs() {
        return ServiceLoader.load(BulletSPI.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }


}
