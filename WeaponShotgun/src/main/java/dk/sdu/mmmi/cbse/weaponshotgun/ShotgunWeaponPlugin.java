package dk.sdu.mmmi.cbse.weaponshotgun;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import dk.sdu.mmmi.cbse.commonweapon.Weapon;

public class ShotgunWeaponPlugin implements IGamePluginService {

    private Entity weaponShotgun;

    public ShotgunWeaponPlugin() {
    }

    @Override
    public void start(GameData gameData, World world) {

        // Add entities to the world
//        weaponShotgun = createBaseWeapon(gameData);
//        world.addEntity(weaponShotgun);
    }

    private Entity createBaseWeapon(GameData gameData) {

        Entity shotgunWeapon = new Shotgun();
        shotgunWeapon.setPolygonCoordinates(
                4, -0.5, 12, -0.5, 12, -1.5, 8, -1.5, 8, -3.5, 6, -3.5, 6, -4.5, -4, -4.5, -4, -2.5, -8, -2.5, -8,
                -1.5, -6, -1.5, -6, -0.5, -8, -0.5, -8, 0.5, -6, 0.5, -6, 1.5, -8, 1.5, -8, 0.5, -12, 0.5, -12, 2.5,
                -6, 2.5, -6, 0.5, 2, 0.5, 2, 2.5, 10, 2.5, 10, 1.5, 12, 1.5, 12, 0.5, 4, 0.5, 4, -0.5
        );


        shotgunWeapon.setX(gameData.getDisplayHeight()/2+20);
        shotgunWeapon.setY(gameData.getDisplayWidth()/2);
        return shotgunWeapon;
    }

    @Override
    public void stop(GameData gameData, World world) {
        // Remove entities
        world.removeEntity(weaponShotgun);
    }

}
