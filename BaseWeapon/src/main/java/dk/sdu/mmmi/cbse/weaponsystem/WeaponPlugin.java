package dk.sdu.mmmi.cbse.weaponsystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import dk.sdu.mmmi.cbse.commonweapon.Weapon;

public class WeaponPlugin implements IGamePluginService {

    private Entity weapon;

    public WeaponPlugin() {

    }

    @Override
    public void start(GameData gameData, World world) {

//         Add entities to the world
        weapon = createBaseWeapon(gameData);
        world.addEntity(weapon);
    }

    private Entity createBaseWeapon(GameData gameData) {

        Entity baseWeapon = new Weapon();
        baseWeapon.setPolygonCoordinates(6, -0.5, 4, -0.5, 4, -1.5, 3, -1.5, 3, -2.5, -1, -2.5, -1, -3.5, 0, -3.5, 0, -4.5, -5, -4.5, -5, -2.5, -4, -2.5, -4, -1.5, -3, -1.5, -3, -0.5, -5, -0.5, -5, 0.5, -3, 0.5, -3, 1.5, -4, 1.5, -4, 2.5, -5, 2.5, -5, 4.5, 0, 4.5, 0, 3.5, -1, 3.5, -1, 2.5, 1, 2.5, 1, 0.5, 2, 0.5, 2, -0.5, 1, -0.5, 1, -1.5, 2, -1.5, 2, -0.5, 3, -0.5, 3, 0.5, 2, 0.5, 2, 1.5, 1, 1.5, 1, 2.5, 3, 2.5, 3, 1.5, 4, 1.5, 4, 0.5, 6, 0.5, 6, 0.5, 8, 0.5, 8, 0.5, 12, 0.5);


        baseWeapon.setX(gameData.getDisplayHeight()/2+20);
        baseWeapon.setY(gameData.getDisplayWidth()/2);
        return baseWeapon;
    }

    @Override
    public void stop(GameData gameData, World world) {
        // Remove entities
        world.removeEntity(weapon);
    }

}