package dk.sdu.mmmi.cbse.weaponsystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;


import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

import java.util.Random;

public class ShurikenCoinSpawnSystem implements IEntityProcessingService {

    Random random = new Random();
    int randomNumber = random.nextInt(200);
    @Override
    public void process(GameData gameData, World world) {

            if (randomNumber == 1) {
                // Add entities to the world
                Entity coinShuriken = createShotgunCoin(gameData);
                world.addEntity(coinShuriken);
                System.out.println("Shuriken coin added");
            }
        }


    private Entity createShotgunCoin(GameData gameData) {

        Entity coinShuriken = new Shuriken();
        coinShuriken.setPolygonCoordinates(6, -0.5, 4, -0.5, 4, -1.5, 3, -1.5, 3, -2.5, -1, -2.5, -1, -3.5, 0, -3.5, 0, -4.5, -5, -4.5, -5, -2.5, -4, -2.5, -4, -1.5, -3, -1.5, -3, -0.5, -5, -0.5, -5, 0.5, -3, 0.5, -3, 1.5, -4, 1.5, -4, 2.5, -5, 2.5, -5, 4.5, 0, 4.5, 0, 3.5, -1, 3.5, -1, 2.5, 1, 2.5, 1, 0.5, 2, 0.5, 2, -0.5, 1, -0.5, 1, -1.5, 2, -1.5, 2, -0.5, 3, -0.5, 3, 0.5, 2, 0.5, 2, 1.5, 1, 1.5, 1, 2.5, 3, 2.5, 3, 1.5, 4, 1.5, 4, 0.5, 6, 0.5, 6, 0.5, 8, 0.5, 8, 0.5, 12, 0.5);


        int randomX = random.nextInt(gameData.getDisplayHeight()) + 1;
        int randomY = random.nextInt(gameData.getDisplayHeight()) + 1;

        coinShuriken.setX(randomX);
        coinShuriken.setY(randomY);
        return coinShuriken;
    }

}
