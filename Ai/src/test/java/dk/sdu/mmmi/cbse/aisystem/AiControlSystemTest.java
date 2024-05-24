package dk.sdu.mmmi.cbse.aisystem;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.commonenemy.Enemy;
import dk.sdu.mmmi.cbse.commonmap.Map;
import dk.sdu.mmmi.cbse.commonmapobject.CommonMapObject;
import dk.sdu.mmmi.cbse.commonmapplayer.MapPlayer;
import dk.sdu.mmmi.cbse.commonobstacle.Obstacle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AiControlSystemTest {


    private AiControlSystem aiControlSystem;
    private Enemy enemy;
    private GameData gameData;
    private World world;
    private Map map;

    @BeforeEach
    void setUp() {
        aiControlSystem = new AiControlSystem();
        enemy = new Enemy();
        gameData = new GameData();
        world = new World();
        world.addEntity(enemy);
        map = new Map();
        world.addEntity(map);
    }

    @Test
    public void testProcessWhenNavigating() {
        // Set up the map
        CommonMapObject[][] mapArray = new CommonMapObject[20][20];
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                mapArray[i][j] = new Obstacle();
            }
        }
        mapArray[10][10] = new MapPlayer(10, 10);
        map.setMap(mapArray);

        // Process the AI
        aiControlSystem.process(gameData, world);

        // Check if the enemy has a path
        assertNotNull(enemy.getPath());
    }



}