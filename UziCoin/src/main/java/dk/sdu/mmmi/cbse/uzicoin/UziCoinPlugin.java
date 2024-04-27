package dk.sdu.mmmi.cbse.uzicoin;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import dk.sdu.mmmi.cbse.commoncoins.Coin;

public class UziCoinPlugin implements IGamePluginService {

    private Entity uziCoin;

    public UziCoinPlugin() {
    }

    @Override
    public void start(GameData gameData, World world) {

        // Add entities to the world
        uziCoin = createUziCoin(gameData);
        world.addEntity(uziCoin);
    }

    private Entity createUziCoin(GameData gameData) {

        Entity coinUzi = new Coin();
        coinUzi.setPolygonCoordinates(
                2, -0.5, 6, -0.5, 6, -1.5, 4, -1.5, 4, -3.5, 1, -3.5, 1, -4.5, -4, -4.5, -4, -2.5, -2, -2.5, -2, -1.5, -1, -1.5, -1, -0.5, -2, -0.5, -2, 0.5, -1, 0.5, -1, 1.5, -2, 1.5, -2, 0.5, -4, 0.5, -4, 2.5, -1, 2.5, -1, 0.5, 1, 0.5, 1, 2.5, 3, 2.5, 3, 1.5, 4, 1.5, 4, 0.5, 2, 0.5, 2, -0.5
        );


        coinUzi.setX(gameData.getDisplayHeight()/2-50);
        coinUzi.setY(gameData.getDisplayWidth()/2);
        return coinUzi;
    }

    @Override
    public void stop(GameData gameData, World world) {
        // Remove entities
        world.removeEntity(uziCoin);
    }

}
