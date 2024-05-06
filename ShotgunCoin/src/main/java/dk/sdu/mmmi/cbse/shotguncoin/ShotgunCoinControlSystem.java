package dk.sdu.mmmi.cbse.shotguncoin;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.commoncoins.Coin;

import java.util.Random;

public class ShotgunCoinControlSystem implements IEntityProcessingService {

    Random random = new Random();
    int randomNumber = random.nextInt(600);
    @Override
    public void process(GameData gameData, World world) {

            if (randomNumber == 1) {
                // Add entities to the world
                Entity shotgunCoin = createShotgunCoin(gameData);
                world.addEntity(shotgunCoin);
                System.out.println("Shotgun coin added");
            }
        }


    private Entity createShotgunCoin(GameData gameData) {

        Entity coinShotgun = new Coin();
        coinShotgun.setPolygonCoordinates(
                4, -0.5, 12, -0.5, 12, -1.5, 8, -1.5, 8, -3.5, 6, -3.5, 6, -4.5, -4, -4.5, -4, -2.5, -8, -2.5, -8,
                -1.5, -6, -1.5, -6, -0.5, -8, -0.5, -8, 0.5, -6, 0.5, -6, 1.5, -8, 1.5, -8, 0.5, -12, 0.5, -12, 2.5,
                -6, 2.5, -6, 0.5, 2, 0.5, 2, 2.5, 10, 2.5, 10, 1.5, 12, 1.5, 12, 0.5, 4, 0.5, 4, -0.5
        );

        int randomX = random.nextInt(gameData.getDisplayHeight()) + 1;
        int randomY = random.nextInt(gameData.getDisplayHeight()) + 1;

        coinShotgun.setX(randomX);
        coinShotgun.setY(randomY);
        return coinShotgun;
    }

}