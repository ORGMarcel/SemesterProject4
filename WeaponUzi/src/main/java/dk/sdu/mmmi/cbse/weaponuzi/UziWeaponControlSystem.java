package dk.sdu.mmmi.cbse.weaponuzi;

import dk.sdu.mmmi.cbse.commonplayer.Player;
import dk.sdu.mmmi.cbse.commonbullet.BulletSPI;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.GameKeys;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.commonweapon.Weapon;

import java.util.Collection;
import java.util.ServiceLoader;

import static java.util.stream.Collectors.toList;

public class UziWeaponControlSystem implements IEntityProcessingService {

    @Override
    public void process(GameData gameData, World world) {

        for (Entity weaponUzi : world.getEntities(Uzi.class)) {
            Uzi weapon1 = (Uzi) weaponUzi;
            if (weapon1.isEquipped()) {
                for (Entity player : world.getEntities(Player.class)) {
                    Player player1 = (Player) player;

                    if (weapon1.getDurability() <= 0) {
                        player1.removeWeaponFromInventory(weapon1);
                        player1.setEquippedWeapon(null);
                        world.removeEntity(weapon1);
                    }

                    weaponUzi.setY(player.getY());
                    weaponUzi.setX(player.getX());
                    weaponUzi.setRotation(player.getRotation());
                    double changeX = Math.cos(Math.toRadians(weaponUzi.getRotation()));
                    double changeY = Math.sin(Math.toRadians(weaponUzi.getRotation()));
                    weaponUzi.setX(weaponUzi.getX() + changeX * 15);
                    weaponUzi.setY(weaponUzi.getY() + changeY * 15);
                }
            }


            // Controlling
            if (gameData.getKeys().isPressed(GameKeys.SPACE)) {
                if (weapon1.isEquipped()) {
                    weapon1.setDurability(weapon1.getDurability() - 1);


                    Thread thread1 = new Thread(() -> {
                        weapon1.setShooting(true);

                        try {
                            for (int i = 0; i < 10; i++) {
                                // Execute the action
                                getBulletSPIs().stream().findFirst().ifPresent(
                                        spi -> world.addEntity(spi.createBullet(weaponUzi, gameData))
                                );

                                // Wait for 0.05 seconds between bullets
                                Thread.sleep(50);
                            }
                            // Wait for 2 seconds after completing the loop
                            Thread.sleep(1000); // 1000 milliseconds
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
            }


            if (weaponUzi.getX() < 0) {
                weaponUzi.setX(1);
            }

            if (weaponUzi.getX() > gameData.getDisplayWidth()) {
                weaponUzi.setX(gameData.getDisplayWidth() - 1);
            }

            if (weaponUzi.getY() < 0) {
                weaponUzi.setY(1);
            }

            if (weaponUzi.getY() > gameData.getDisplayHeight()) {
                weaponUzi.setY(gameData.getDisplayHeight() - 1);
            }


        }
    }

    private Collection<? extends BulletSPI> getBulletSPIs() {
        return ServiceLoader.load(BulletSPI.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }

}
