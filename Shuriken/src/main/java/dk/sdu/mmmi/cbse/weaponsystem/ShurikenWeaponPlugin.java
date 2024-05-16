package dk.sdu.mmmi.cbse.weaponsystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import dk.sdu.mmmi.cbse.commonplayer.Player;

import java.util.Random;

public class ShurikenWeaponPlugin implements IGamePluginService {

    private Entity weaponShuriken;

    public ShurikenWeaponPlugin() {

    }

    @Override
    public void start(GameData gameData, World world) {

        weaponShuriken = createBaseWeapon(gameData);
        world.addEntity(weaponShuriken);

//         Add entities to the world
//        weaponShuriken = (Shuriken) createBaseWeapon(gameData);
//        world.addEntity(weaponShuriken);
//        weaponShuriken.setEquipped(true);

//        for (Entity player : world.getEntities(Player.class)) {
//            Player player1 = (Player) player;
//            System.out.println("Added to inventory");
//            player1.addWeaponToInventory(weaponShuriken);
//            player1.setEquippedWeapon(weaponShuriken);
//            player1.setCurrentWeapon(0);
//            for (int i = 0; i < player1.getInventory().length; i++) {
//                System.out.println(player1.getInventory()[i]);
//            }
//        }

    }

    private Entity createBaseWeapon(GameData gameData) {

        Shuriken baseWeapon = new Shuriken();
        baseWeapon.setPolygonCoordinates(6, -0.5, 4, -0.5, 4, -1.5, 3, -1.5, 3, -2.5, -1, -2.5, -1, -3.5, 0, -3.5, 0, -4.5, -5, -4.5, -5, -2.5, -4, -2.5, -4, -1.5, -3, -1.5, -3, -0.5, -5, -0.5, -5, 0.5, -3, 0.5, -3, 1.5, -4, 1.5, -4, 2.5, -5, 2.5, -5, 4.5, 0, 4.5, 0, 3.5, -1, 3.5, -1, 2.5, 1, 2.5, 1, 0.5, 2, 0.5, 2, -0.5, 1, -0.5, 1, -1.5, 2, -1.5, 2, -0.5, 3, -0.5, 3, 0.5, 2, 0.5, 2, 1.5, 1, 1.5, 1, 2.5, 3, 2.5, 3, 1.5, 4, 1.5, 4, 0.5, 6, 0.5, 6, 0.5, 8, 0.5, 8, 0.5, 12, 0.5);

        baseWeapon.setDurability(10);

//        baseWeapon.setX(gameData.getDisplayHeight() / 2 + 20);
//        baseWeapon.setY(gameData.getDisplayWidth() / 2);
        Random random = new Random();


        int randomX = random.nextInt(gameData.getDisplayHeight()) + 1;
        int randomY = random.nextInt(gameData.getDisplayHeight()) + 1;

        baseWeapon.setX(randomX);
        baseWeapon.setY(randomY);

        return baseWeapon;
    }

    @Override
    public void stop(GameData gameData, World world) {
        // Remove entities
        world.removeEntity(weaponShuriken);
    }

}
