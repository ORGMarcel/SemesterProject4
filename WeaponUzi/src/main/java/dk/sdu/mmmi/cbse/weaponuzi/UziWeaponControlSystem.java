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

        for (Entity weaponUzi : world.getEntities(Weapon.class)) {
            for (Entity player : world.getEntities(Player.class)) {
                weaponUzi.setY(player.getY());
                weaponUzi.setX(player.getX());
                weaponUzi.setRotation(player.getRotation());
                double changeX = Math.cos(Math.toRadians(weaponUzi.getRotation()));
                double changeY = Math.sin(Math.toRadians(weaponUzi.getRotation()));
                weaponUzi.setX(weaponUzi.getX() + changeX * 15);
                weaponUzi.setY(weaponUzi.getY() + changeY * 15);

            }


            // Controlling
            if (gameData.getKeys().isDown(GameKeys.LEFT)) {
            }
            if (gameData.getKeys().isDown(GameKeys.RIGHT)) {
            }
            if (gameData.getKeys().isPressed(GameKeys.UP)) {

            }
            if (gameData.getKeys().isDown(GameKeys.SPACE)) {
                getBulletSPIs().stream().findFirst().ifPresent(
                        spi -> {
                            world.addEntity(spi.createBullet(weaponUzi, gameData));
                        }
                );
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
