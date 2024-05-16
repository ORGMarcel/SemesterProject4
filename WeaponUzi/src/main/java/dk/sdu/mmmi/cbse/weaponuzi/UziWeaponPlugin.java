package dk.sdu.mmmi.cbse.weaponuzi;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import dk.sdu.mmmi.cbse.commonweapon.Weapon;

import java.util.Random;

public class UziWeaponPlugin implements IGamePluginService {

    private Entity Uzi;

    public UziWeaponPlugin() {
    }

    @Override
    public void start(GameData gameData, World world) {

        // Add entities to the world
        Uzi = createBaseWeapon(gameData);
        world.addEntity(Uzi);
    }

    private Entity createBaseWeapon(GameData gameData) {

        Uzi uziWeapon = new Uzi();
        uziWeapon.setPolygonCoordinates(
                2, -0.5, 6, -0.5, 6, -1.5, 4, -1.5, 4, -3.5, 1, -3.5, 1, -4.5, -4, -4.5, -4, -2.5, -2, -2.5, -2, -1.5, -1, -1.5, -1, -0.5, -2, -0.5, -2, 0.5, -1, 0.5, -1, 1.5, -2, 1.5, -2, 0.5, -4, 0.5, -4, 2.5, -1, 2.5, -1, 0.5, 1, 0.5, 1, 2.5, 3, 2.5, 3, 1.5, 4, 1.5, 4, 0.5, 2, 0.5, 2, -0.5
        );
        uziWeapon.setDurability(10);
        Random random = new Random();

        int randomX = random.nextInt(gameData.getDisplayHeight()) + 1;
        int randomY = random.nextInt(gameData.getDisplayHeight()) + 1;

        uziWeapon.setX(randomX);
        uziWeapon.setY(randomY);



//        uziWeapon.setX(gameData.getDisplayHeight()/2+20);
//        uziWeapon.setY(gameData.getDisplayWidth()/2);
        return uziWeapon;
    }

    @Override
    public void stop(GameData gameData, World world) {
        // Remove entities
        world.removeEntity(Uzi);
    }

}
