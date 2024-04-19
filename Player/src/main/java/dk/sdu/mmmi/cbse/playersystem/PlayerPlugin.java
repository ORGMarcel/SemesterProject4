package dk.sdu.mmmi.cbse.playersystem;

import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.commonplayer.Player;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
public class PlayerPlugin implements IGamePluginService {

    private Player playerShip;

    public PlayerPlugin() {
    }

    @Override
    public void start(GameData gameData, World world) {

        // Add entities to the world
        createPlayerShip(gameData, world);

    }

    private Entity createPlayerShip(GameData gameData, World world) {

        playerShip = new Player();
        playerShip.setPolygonCoordinates(-9.4, 11.84, 6.36, -6.0, -12.4, 10.16, -13.84, 13.36, -9.32, 13.24, 3.2, -0.16, 16.0, 12.0, 20.72, 11.96, 19.16, 9.16, 16.08, 10.56, 4.64, -0.44, 8.24, -5.44, 12.88, -3.44, 15.8, 1.0, 13.52, 2.6, 16.2, 2.0, 19.32, 3.76, 18.76, 1.72, 16.8, 0.32, 13.92, -4.28, 9.8, -6.36, 13.2, -8.28, 14.76, -10.64, 14.88, -13.92, 13.08, -16.48, 10.2, -17.84, 7.12, -17.48, 5.48, -16.36, 2.68, -18.52, -1.08, -18.12, -3.0, -18.56, -1.16, -17.08, 2.56, -17.68, 3.96, -16.56, 4.4, -15.0, 1.48, -15.48, -1.56, -16.12, -6.12, -14.8, -4.64, -14.08, -1.6, -15.12, 3.84, -13.92, 3.76, -11.0, 5.28, -8.56, 7.4, -7.16, 1.28, -9.64, -1.16, -9.84, -4.0, -8.0, -7.36, -7.28, -8.48, -5.52, -7.36, -4.88, -5.76, -6.8, -5.72, -4.72, -4.08, -6.36, -2.6, -5.56, -1.16, -5.84, -2.76, -7.88, -0.76, -9.0);
        playerShip.setX(gameData.getDisplayHeight()/2);
        playerShip.setY(gameData.getDisplayWidth()/2);
        playerShip.setHealthPoints(5);
        playerShip.setDmg(10);
        playerShip.add(new LifePart(3));
        world.addEntity(playerShip);

        return playerShip;
    }

    @Override
    public void stop(GameData gameData, World world) {
        // Remove entities
        world.removeEntity(playerShip);
    }

}
