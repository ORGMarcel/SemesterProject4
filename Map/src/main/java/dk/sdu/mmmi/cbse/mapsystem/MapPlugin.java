package dk.sdu.mmmi.cbse.mapsystem;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import dk.sdu.mmmi.cbse.commoninvisibleobject.InvisibleObject;
import dk.sdu.mmmi.cbse.commonmapobject.CommonMapObject;
import dk.sdu.mmmi.cbse.commonobstacle.Obstacle;
import dk.sdu.mmmi.cbse.commonweaponcoin.WeaponCoin;

import java.util.Random;

public class MapPlugin implements IGamePluginService {

    CommonMapObject[][] mapObstacle;
    @Override
    public void start(GameData gameData, World world) {
        // Code to create the whole map.
        System.out.println("MapPlugin start method ran");
        createMap(world, gameData);
    }

    @Override
    public void stop(GameData gameData, World world) {
        // Code to remove the whole map.
        System.out.println("MapPlugin stop method ran");
        for (int i = 0; i < mapObstacle.length; i++) {
            for (int j = 0; j < mapObstacle[i].length; j++) {
                world.removeEntity(mapObstacle[i][j]);
            }
        }
    }

    public void createMap(World world, GameData gameData) {
        mapObstacle = createObstacleList(world, gameData);
        for (int i = 0; i < mapObstacle.length; i++) {
            for (int j = 0; j < mapObstacle[i].length; j++) {
                world.addEntity(mapObstacle[i][j]);
            }
        }

    }


    private CommonMapObject[][] createObstacleList(World world, GameData gameData) {

        CommonMapObject[][] mapList = {
                {new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject()},
                {new InvisibleObject(), new Obstacle(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject()},
                {new InvisibleObject(), new Obstacle(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject()},
                {new InvisibleObject(), new Obstacle(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject()},
                {new InvisibleObject(), new Obstacle(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject()},
                {new InvisibleObject(), new Obstacle(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new Obstacle(), new Obstacle(), new Obstacle(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject()},
                {new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject()},
                {new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject()},
                {new InvisibleObject(), new Obstacle(), new Obstacle(), new Obstacle(), new Obstacle(), new Obstacle(), new Obstacle(), new Obstacle(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new Obstacle(), new Obstacle(), new Obstacle(), new Obstacle(), new Obstacle(), new Obstacle()},
                {new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new Obstacle(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject()},
                {new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new Obstacle(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new Obstacle(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject()},
                {new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new Obstacle(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject()},
                {new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new Obstacle(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject()},
                {new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject()},
                {new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject()},
                {new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new Obstacle(), new Obstacle(), new Obstacle(), new Obstacle(), new Obstacle(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject()},
                {new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new Obstacle(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject()},
                {new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new Obstacle(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject()},
                {new InvisibleObject(), new Obstacle(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new Obstacle(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject()},
                {new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject()},
        };

        for (int i = 0; i < mapList.length; i++) {
            for (int j = 0; j < mapList[i].length; j++) {
                CommonMapObject mapElement = mapList[i][j];
                float obstacleLength = (float) gameData.getDisplayWidth() / mapList.length;
                float obstacleHeight = (float) gameData.getDisplayHeight() / mapList[i].length;

                mapElement.setHeight((float) gameData.getDisplayWidth() / mapList.length);
                mapElement.setLength((float) gameData.getDisplayHeight() / mapList[i].length);

                mapElement.setY((double) (gameData.getDisplayWidth() / mapList.length * (i + 1)) - (obstacleLength / 2));
                mapElement.setX((double) (gameData.getDisplayHeight() / mapList.length * (j + 1)) - (obstacleHeight / 2));

                mapElement.setPolygonCoordinates(mapElement.getHeight() / 2, mapElement.getLength() / 2,
                        -(mapElement.getHeight() / 2), mapElement.getLength() / 2, -(mapElement.getHeight() / 2),
                        -(mapElement.getLength() / 2), mapElement.getHeight() / 2, -(mapElement.getLength() / 2));
                mapList[i][j] = mapElement;
            }
        }
        return mapList;
    }


}
