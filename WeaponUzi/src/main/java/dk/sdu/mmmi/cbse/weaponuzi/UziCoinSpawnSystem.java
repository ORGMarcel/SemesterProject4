package dk.sdu.mmmi.cbse.weaponuzi;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

import java.util.Random;

public class UziCoinSpawnSystem implements IEntityProcessingService {

    Random random = new Random();
    int randomNumber = random.nextInt(1000);
    @Override
    public void process(GameData gameData, World world) {

            if (randomNumber == 1) {
                // Add entities to the world
                Entity shotgunCoin = createUziCoin(gameData);
                world.addEntity(shotgunCoin);
                System.out.println("Uzi coin added");
            }
        }


    private Entity createUziCoin(GameData gameData) {

        Uzi coinUzi = new Uzi();
        coinUzi.setPolygonCoordinates(
                2, -0.5, 6, -0.5, 6, -1.5, 4, -1.5, 4, -3.5, 1, -3.5, 1, -4.5, -4, -4.5, -4, -2.5, -2, -2.5, -2, -1.5, -1, -1.5, -1, -0.5, -2, -0.5, -2, 0.5, -1, 0.5, -1, 1.5, -2, 1.5, -2, 0.5, -4, 0.5, -4, 2.5, -1, 2.5, -1, 0.5, 1, 0.5, 1, 2.5, 3, 2.5, 3, 1.5, 4, 1.5, 4, 0.5, 2, 0.5, 2, -0.5
        );
        coinUzi.setDurability(10);


        int randomX = random.nextInt(gameData.getDisplayHeight()) + 1;
        int randomY = random.nextInt(gameData.getDisplayHeight()) + 1;

        coinUzi.setX(randomX);
        coinUzi.setY(randomY);
        return coinUzi;
    }

}
