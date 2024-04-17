package dk.sdu.cbse.enemiessystem;

import dk.sdu.cbse.commonbullet.BulletSPI;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class EnemyControlSystemTest {

    //@org.junit.jupiter.api.AfterEach
    //void tearDown()

    //@org.junit.jupiter.api.Test
    //void process() {


    private EnemyControlSystem enemyControlSystem;
    private GameData gameData;
    private World world;

    @org.junit.jupiter.api.BeforeEach
    public void setUp() {
        enemyControlSystem = new EnemyControlSystem();
        gameData = new GameData();
        world = new World();
    }


    @org.junit.jupiter.api.Test
    public void testProcess() {
        // Prepare test data
        Entity enemy = new Enemy();
        enemy.setX(100);
        enemy.setY(100);
        world.addEntity(enemy);

        // Mock the BulletSPI
        BulletSPI bulletSPI = mock(BulletSPI.class);
        when(bulletSPI.createBullet(any(Entity.class), any(GameData.class))).thenReturn(new Entity());

        // Set up game data
        gameData.setDisplayWidth(800);
        gameData.setDisplayHeight(600);

        // Set up round
        world.setRound(1);
        world.setRoundRunning(false);

        // Call the method under test
        enemyControlSystem.process(gameData, world);

        // Assertions
        assertEquals(2, world.getEntities(Enemy.class).size()); // Check if new enemies are spawned

        // Add more assertions as needed

    }

    // Add more tests as needed
}
