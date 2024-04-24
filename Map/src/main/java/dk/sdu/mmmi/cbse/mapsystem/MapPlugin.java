package dk.sdu.mmmi.cbse.mapsystem;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.ColorPart;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import dk.sdu.mmmi.cbse.commoninvisibleobject.InvisibleObject;
import dk.sdu.mmmi.cbse.commonmap.CommonMap;
import dk.sdu.mmmi.cbse.commonmapobject.CommonMapObject;
import dk.sdu.mmmi.cbse.commonobstacle.Obstacle;
import dk.sdu.mmmi.cbse.commonweaponcoin.WeaponCoin;

import java.util.Random;

public class MapPlugin implements IGamePluginService {
    @Override
    public void start(GameData gameData, World world) {

        // Code to create the whole map.
        System.out.println("MapPlugin start method ran");
        createMap(world, gameData);



    }

    @Override
    public void stop(GameData gameData, World world) {

    }


    // Change Entity to MapObstacle
    public void createMap(World world, GameData gameData){

        // Code to create the arraylist of map
//        MapElement[][] mapObstacle = createObstacleList(world, gameData, 40, 40);
        CommonMapObject[][] mapObstacle = createObstacleList(world, gameData, 40, 40);

        // TODO: Line to add the map to world of entities
        CommonMap commonMap = new CommonMap();
        commonMap.setMap(mapObstacle);
        world.addEntity(commonMap);

        for (int i = 0; i < mapObstacle.length; i++) {
            for (int j = 0; j < mapObstacle[i].length; j++) {
                System.out.println("x: " + mapObstacle[i][j].getX() + " y: " + mapObstacle[i][j].getY() + ": ");
                world.addEntity(mapObstacle[i][j]);
            }
        }

    }


//    // Change Entity to MapObstacle
//    private MapElement[][] createObstacleList ( World world, GameData gameData, int obstacleLength, int obstacleHeight){
//
//        // Method to create the obstacle list in list, which is the map.
//
//
//        // Random map generator
//        Random random = new Random();
//        MapElement[][] mapList = new MapElement[gameData.getDisplayWidth()/obstacleLength][gameData.getDisplayHeight()/obstacleHeight];
//
//        // TODO:  Chance to spawn
//        int chance = 30;
//        for (int i = 0; i < mapList.length; i++) {
//            chance = 5;
//
//            for (int j = 0; j < mapList[i].length; j++) {
//                MapElement mapElement = new MapElement();
//                ColorPart colorPart = mapElement.getPart(ColorPart.class);
//
//                if(random.nextInt(0, chance) == 1){
//                    mapElement.setType(ElementType.WALL);
//                    colorPart.setColorInt(1);
//                    chance = 30;
//                }else if(random.nextInt(0, chance) == 2){
//                    mapElement.setType(ElementType.WEAPON);
//                    colorPart.setColorInt(2);
//                    chance = 30;
//                }else{
//                    mapElement.setType(ElementType.NOTHING);
//                    colorPart.setColorInt(0);
//                    chance = 30;
//                }
//                // TODO: Maybe change height with length
//                mapElement.setHeight((float) gameData.getDisplayWidth() / mapList.length);
//                mapElement.setLength((float) gameData.getDisplayHeight() / mapList[i].length);
////                System.out.println("Height is: " + mapElement.getHeight());
////                System.out.println(" Width is: " + mapElement.getLength());
////                System.out.println("Hello");
//                // Calculate x by map length divided by index of the element in the mapList. (Think length is the second array)
//
//                mapElement.setX((double) gameData.getDisplayWidth() / mapList.length * (i+1));
//                // Thinking the height is the first array
//                mapElement.setY((double) gameData.getDisplayHeight() / mapList.length *  (j+1));
//                mapElement.setPolygonCoordinates(mapElement.getHeight()/2, mapElement.getLength()/2, -(mapElement.getHeight()/2), mapElement.getLength()/2, -(mapElement.getHeight()/2), -(mapElement.getLength()/2), mapElement.getHeight()/2, -(mapElement.getLength()/2));
//
//                mapList[i][j] = mapElement;
//            }
//        }
//
//
//        return mapList;
//
//    }


    // Change Entity to MapObstacle
    private CommonMapObject[][] createObstacleList (World world, GameData gameData, int obstacleLength, int obstacleHeight){

        // Method to create the obstacle list in list, which is the map.


        // Random map generator
        Random random = new Random();
        CommonMapObject[][] mapList = new CommonMapObject[gameData.getDisplayWidth()/obstacleLength][gameData.getDisplayHeight()/obstacleHeight];

        // TODO:  Chance to spawn
        int chance = 30;
        for (int i = 0; i < mapList.length; i++) {
            chance = 5;

            for (int j = 0; j < mapList[i].length; j++) {
                CommonMapObject mapElement;
//                ColorPart colorPart = mapElement.getPart(ColorPart.class);

                if(random.nextInt(0, chance) == 1){
                    mapElement = new Obstacle();
//                    mapElement.setType(ElementType.WALL);
//                    colorPart.setColorInt(1);
                    chance = 30;
                }else if(random.nextInt(0, chance) == 2){
                    mapElement = new WeaponCoin();
//                    mapElement.setType(ElementType.WEAPON);
//                    colorPart.setColorInt(2);
                    chance = 30;
                }else{
                    mapElement = new InvisibleObject();
//                    mapElement.setType(ElementType.NOTHING);
//                    colorPart.setColorInt(0);
                    chance = 30;
                }
                // TODO: Maybe change height with length
                mapElement.setHeight((float) gameData.getDisplayWidth() / mapList.length);
                mapElement.setLength((float) gameData.getDisplayHeight() / mapList[i].length);
//                System.out.println("Height is: " + mapElement.getHeight());
//                System.out.println(" Width is: " + mapElement.getLength());
//                System.out.println("Hello");
                // Calculate x by map length divided by index of the element in the mapList. (Think length is the second array)

                mapElement.setX((double) gameData.getDisplayWidth() / mapList.length * (i+1));
                // Thinking the height is the first array
                mapElement.setY((double) gameData.getDisplayHeight() / mapList.length *  (j+1));
                mapElement.setPolygonCoordinates(mapElement.getHeight()/2, mapElement.getLength()/2, -(mapElement.getHeight()/2), mapElement.getLength()/2, -(mapElement.getHeight()/2), -(mapElement.getLength()/2), mapElement.getHeight()/2, -(mapElement.getLength()/2));

                mapList[i][j] = mapElement;
            }
        }



        return mapList;

    }

}
