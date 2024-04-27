package dk.sdu.mmmi.cbse.mapsystem;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import dk.sdu.mmmi.cbse.commoninvisibleobject.InvisibleObject;
import dk.sdu.mmmi.cbse.commonmap.Map;
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
//        CommonMapObject[][] mapObstacle = createObstacleList(world, gameData, 40, 40);
        CommonMapObject[][] mapObstacle = createObstacleList(world, gameData);

        // TODO: Line to add the map to world of entities
        Map map = new Map();
        map.setMap(mapObstacle);
        world.addEntity(map);

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
    private CommonMapObject[][] createObstacleListOld (World world, GameData gameData, int obstacleLength, int obstacleHeight){

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

                mapElement.setX((double) (gameData.getDisplayWidth() / mapList.length * (i+1))-(obstacleLength/2));
                // Thinking the height is the first array
                mapElement.setY((double) (gameData.getDisplayHeight() / mapList.length *  (j+1))-(obstacleHeight/2));
                mapElement.setPolygonCoordinates(mapElement.getHeight()/2, mapElement.getLength()/2, -(mapElement.getHeight()/2), mapElement.getLength()/2, -(mapElement.getHeight()/2), -(mapElement.getLength()/2), mapElement.getHeight()/2, -(mapElement.getLength()/2));

                mapList[i][j] = mapElement;
            }
        }



        return mapList;

    }


    private CommonMapObject[][] createObstacleList (World world, GameData gameData){

        // Method to create the obstacle list in list, which is the map.


        // Random map generator
//        Random random = new Random();
//        CommonMapObject[][] mapList = new CommonMapObject[gameData.getDisplayWidth()/obstacleLength][gameData.getDisplayHeight()/obstacleHeight];

        CommonMapObject[][] mapList = {
                {new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject()},
                {new InvisibleObject(), new Obstacle(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject()},
                {new InvisibleObject(), new Obstacle(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject()},
                {new InvisibleObject(), new Obstacle(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject()},
                {new InvisibleObject(), new Obstacle(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject()},
                {new InvisibleObject(), new Obstacle(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject()},
                {new InvisibleObject(), new Obstacle(), new WeaponCoin(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject()},
                {new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject()},
                {new InvisibleObject(), new Obstacle(), new Obstacle(), new Obstacle(), new Obstacle(), new Obstacle(), new Obstacle(), new Obstacle(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new Obstacle(), new Obstacle(), new Obstacle(), new Obstacle(), new Obstacle(), new Obstacle()},
                {new InvisibleObject(), new InvisibleObject(), new WeaponCoin(), new InvisibleObject(), new Obstacle(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject()},
                {new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new Obstacle(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject()},
                {new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new Obstacle(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject()},
                {new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject()},
                {new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject()},
                {new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new WeaponCoin(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject()},
                {new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new Obstacle(), new Obstacle(), new Obstacle(), new Obstacle(), new Obstacle(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject()},
                {new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new Obstacle(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject()},
                {new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new Obstacle(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject()},
                {new InvisibleObject(), new Obstacle(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new Obstacle(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject()},
                {new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject()},
        };

        // TODO:  Chance to spawn
        int chance = 30;
        for (int i = 0; i < mapList.length; i++) {
            chance = 5;

            for (int j = 0; j < mapList[i].length; j++) {
//                CommonMapObject mapElement;
////                ColorPart colorPart = mapElement.getPart(ColorPart.class);
//
//                if(random.nextInt(0, chance) == 1){
//                    mapElement = new Obstacle();
////                    mapElement.setType(ElementType.WALL);
////                    colorPart.setColorInt(1);
//                    chance = 30;
//                }else if(random.nextInt(0, chance) == 2){
//                    mapElement = new WeaponCoin();
////                    mapElement.setType(ElementType.WEAPON);
////                    colorPart.setColorInt(2);
//                    chance = 30;
//                }else{
//                    mapElement = new InvisibleObject();
////                    mapElement.setType(ElementType.NOTHING);
////                    colorPart.setColorInt(0);
//                    chance = 30;
//                }

                CommonMapObject mapElement = mapList[i][j];
                // TODO: Maybe change height with length
                float obstacleLength = (float) gameData.getDisplayWidth() / mapList.length;
                float obstacleHeight = (float) gameData.getDisplayHeight() / mapList[i].length;

                mapElement.setHeight((float) gameData.getDisplayWidth() / mapList.length);
                mapElement.setLength((float) gameData.getDisplayHeight() / mapList[i].length);

//                System.out.println("Height is: " + mapElement.getHeight());
//                System.out.println(" Width is: " + mapElement.getLength());
//                System.out.println("Hello");
                // Calculate x by map length divided by index of the element in the mapList. (Think length is the second array)

                mapElement.setY((double) (gameData.getDisplayWidth() / mapList.length * (i+1))-(obstacleLength/2));
                // Thinking the height is the first array
                mapElement.setX((double) (gameData.getDisplayHeight() / mapList.length *  (j+1))-(obstacleHeight/2));
                mapElement.setPolygonCoordinates(mapElement.getHeight()/2, mapElement.getLength()/2, -(mapElement.getHeight()/2), mapElement.getLength()/2, -(mapElement.getHeight()/2), -(mapElement.getLength()/2), mapElement.getHeight()/2, -(mapElement.getLength()/2));

                mapList[i][j] = mapElement;
            }
        }



        return mapList;

    }




    private CommonMapObject[][] createObstacleList1(World world, GameData gameData, int obstacleLength, int obstacleHeight) {
        // Define a bigger premade map
        CommonMapObject[][] bigMap = {
                {new Obstacle(), new Obstacle(), new Obstacle(), new Obstacle(), new Obstacle()},
                {new Obstacle(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new Obstacle()},
                {new Obstacle(), new InvisibleObject(), new WeaponCoin(), new InvisibleObject(), new Obstacle()},
                {new Obstacle(), new InvisibleObject(), new InvisibleObject(), new InvisibleObject(), new Obstacle()},
                {new Obstacle(), new Obstacle(), new Obstacle(), new Obstacle(), new Obstacle()}
        };

        // Calculate the number of tiles based on the selected map
        int numTilesX = bigMap.length;
        int numTilesY = bigMap[0].length;

        CommonMapObject[][] mapList = new CommonMapObject[numTilesX][numTilesY];

        // Loop through each tile position and copy the selected map
        for (int x = 0; x < numTilesX; x++) {
            for (int y = 0; y < numTilesY; y++) {
                CommonMapObject mapElement = bigMap[x][y];

                // Calculate position and dimensions for the tile
                float blockWidth = (float) gameData.getDisplayWidth() / numTilesX;
                float blockHeight = (float) gameData.getDisplayHeight() / numTilesY;
                float centerX = (x + 0.5f) * blockWidth;
                float centerY = (y + 0.5f) * blockHeight;
                mapElement.setHeight(blockHeight);
                mapElement.setLength(blockWidth);
                mapElement.setX(centerX);
                mapElement.setY(centerY);
                mapElement.setPolygonCoordinates(blockHeight / 2, blockWidth / 2, -blockHeight / 2, blockWidth / 2, -blockHeight / 2, -blockWidth / 2, blockHeight / 2, -blockWidth / 2);

                mapList[x][y] = mapElement;
            }
        }

        return mapList;
    }


    private CommonMapObject[][] selectRandomMapLayout(CommonMapObject[][]... mapLayouts) {
        Random random = new Random();
        return mapLayouts[random.nextInt(mapLayouts.length)];
    }






}
