package dk.sdu.mmmi.playersystem;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import dk.sdu.cbse.commonbullet.BulletSPI;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.GameKeys;
import dk.sdu.mmmi.cbse.common.data.World;


import static org.junit.jupiter.api.Assertions.*;

class PlayerControlSystemTest {
    private PlayerControlSystem playerControlSystem;
    private GameData gameData;
    private World world;

    @BeforeEach
    public void setUp() {
        playerControlSystem = new PlayerControlSystem();
        gameData = new GameData();
        world = new World();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    public void testProcess_LeftKey() {
        // Prepare test data
        Entity player = new Player();
        player.setX(100);
        player.setY(100);
        gameData.getKeys().setKey(GameKeys.LEFT, true);

        // Call the method under test
        playerControlSystem.process(gameData, world);

        // Assertions
        assertEquals(95, player.getX()); // Check if player moves left by 5 units
        assertEquals(180, player.getRotation()); // Check if player rotation is set correctly
        // Add more assertions as needed
    }

    @Test
    public void testProcess_RightKey() {
        // Prepare test data
        Entity player = new Player();
        player.setX(100);
        player.setY(100);
        gameData.getKeys().setKey(GameKeys.RIGHT, true);

        // Call the method under test
        playerControlSystem.process(gameData, world);

        // Assertions
        assertEquals(105, player.getX()); // Check if player moves right by 5 units
        assertEquals(360, player.getRotation()); // Check if player rotation is set correctly
        // Add more assertions as needed
    }
}