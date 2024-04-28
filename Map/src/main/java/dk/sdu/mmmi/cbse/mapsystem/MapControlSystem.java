package dk.sdu.mmmi.cbse.mapsystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.commonenemy.Enemy;
import dk.sdu.mmmi.cbse.commoninvisibleobject.InvisibleObject;
import dk.sdu.mmmi.cbse.commonmap.Map;
import dk.sdu.mmmi.cbse.commonmapenemy.MapEnemy;
import dk.sdu.mmmi.cbse.commonmapobject.CommonMapObject;
import dk.sdu.mmmi.cbse.commonmapplayer.MapPlayer;
import dk.sdu.mmmi.cbse.commonplayer.Player;

public class MapControlSystem implements IEntityProcessingService {
    Map map;
    @Override
    public void process(GameData gameData, World world) {



        for (Entity e : world.getEntities(Map.class)) {
            map = (Map) e;
        }



        // Code to update map position
        for (Entity player : world.getEntities(Player.class)) {
            int playerPositionI = (int) (player.getY() / 40);
            int playerPositionJ = (int) (player.getX() / 40);
            CommonMapObject[][] mapArray = map.getMap();
            for (int i = 0; i < mapArray.length; i++) {
                for (int j = 0; j < mapArray[i].length; j++) {
                    if(mapArray[i][j] instanceof MapPlayer){
                        System.out.println("First is: " + i + " second is: " + j);
                        mapArray[i][j] = new InvisibleObject();
                        playerPositionI = i;
                        playerPositionJ = j;

                    }
                }
            }
            mapArray[playerPositionI][playerPositionJ] = new InvisibleObject();
            mapArray[(int) (player.getY() / 40)][(int) (player.getX() / 40)] = new MapPlayer();
        }


        // Code to update map position
        for (Entity enemy : world.getEntities(Enemy.class)) {
            int enemyPositionI = (int) (enemy.getY() / 40);
            int enemyPositionJ = (int) (enemy.getX() / 40);
            CommonMapObject[][] mapArray = map.getMap();
            for (int i = 0; i < mapArray.length; i++) {
                for (int j = 0; j < mapArray[i].length; j++) {
                    if(mapArray[i][j] instanceof MapEnemy){
//                        System.out.println("First is: " + i + " second is: " + j);
                        mapArray[i][j] = new InvisibleObject();
                        enemyPositionI = i;
                        enemyPositionJ = j;

                    }
                }
            }
            mapArray[enemyPositionI][enemyPositionJ] = new InvisibleObject();
            mapArray[(int) (enemy.getY() / 40)][(int) (enemy.getX() / 40)] = new MapEnemy();
        }










    }







}
