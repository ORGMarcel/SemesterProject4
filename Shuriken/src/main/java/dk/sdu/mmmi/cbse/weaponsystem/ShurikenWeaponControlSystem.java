package dk.sdu.mmmi.cbse.weaponsystem;

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


public class ShurikenWeaponControlSystem implements IEntityProcessingService {

    @Override
    public void process(GameData gameData, World world) {

        for (Entity weapon : world.getEntities(Shuriken.class)) {
            Shuriken weapon1 = (Shuriken) weapon;
            for (Entity player : world.getEntities(Player.class)) {
                Player player1 = (Player) player;

                if (weapon1.getDurability() <= 0) {
                    player1.removeWeaponFromInventory(weapon1);
                    player1.setEquippedWeapon(null);
                    world.removeEntity(weapon1);
                }
                if (weapon1.isEquipped()) {
                    weapon.setY(player.getY());
                    weapon.setX(player.getX());
                    weapon.setRotation(player.getRotation());
                    double changeX = Math.cos(Math.toRadians(weapon.getRotation()));
                    double changeY = Math.sin(Math.toRadians(weapon.getRotation()));
                    weapon.setX(weapon.getX() + changeX * 30);
                    weapon.setY(weapon.getY() + changeY * 30);
                }


            }


            // Controlling
            if (gameData.getKeys().isPressed(GameKeys.SPACE)) {
                Shuriken shuriken = (Shuriken) weapon;
                if (shuriken.isEquipped()) {
                    shuriken.setDurability(shuriken.getDurability() - 1);
                    getBulletSPIs().stream().findFirst().ifPresent(
                            spi -> {
                                world.addEntity(spi.createBullet(weapon, gameData));
                            }
                    );
                }


            }

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
