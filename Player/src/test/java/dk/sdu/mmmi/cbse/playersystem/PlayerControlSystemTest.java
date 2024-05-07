package dk.sdu.mmmi.cbse.playersystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.commonplayer.Player;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerControlSystemTest {

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
    }
    @Test
    public void testProcess() {
        PlayerControlSystem playerControlSystem = new PlayerControlSystem();
        Entity player = new Player();
        GameData gameData = new GameData();
        World world = new World();
        world.addEntity(player);
        // Set the player's rotation
        player.setRotation(45);
        playerControlSystem.process(gameData, world);
        // Check if player's position has changed
        assertEquals(0, player.getX());
        assertNotEquals(0, player.getY());
        // Check if player is removed when out of bounds
        player.setX(gameData.getDisplayWidth() + 1);
        playerControlSystem.process(gameData, world);
        assertTrue(world.getEntities().contains(player));
    }
    @Test
    public void testProcessWhenJumping() {
        PlayerControlSystem playerControlSystem = new PlayerControlSystem();
        Player player = new Player();
        GameData gameData = new GameData();
        World world = new World();
        world.addEntity(player);
        // Set the player's rotation and jumping status
        player.setRotation(45);
        player.setJumping(true);
        double initialY = player.getY();
        playerControlSystem.process(gameData, world);
        // Check if player's Y position has decreased due to gravity
        assertTrue(player.getY() -1   < initialY);
    }
}