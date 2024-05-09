package dk.sdu.mmmi.cbse.weaponsystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.GameKeys;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.commonplayer.Player;
import dk.sdu.mmmi.cbse.commonweapon.Weapon;
import dk.sdu.mmmi.cbse.playersystem.PlayerControlSystem;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WeaponControlSystemTest {

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
    }

//    @Test
//    public void testProcess() {
//        WeaponControlSystem weaponControlSystem = new WeaponControlSystem();
//        Entity weapon = new Weapon();
//        Entity player = new Player();
//        GameData gameData = new GameData();
//        World world = new World();
//        world.addEntity(weapon);
//        world.addEntity(player);
//        // Set the player's position and rotation
//        player.setX(100);
//        player.setY(200);
//        player.setRotation(45);
//        weaponControlSystem.process(gameData, world);
//        // Check if weapon's position and rotation match the player's
//        assertEquals(player.getX(), weapon.getX());
//        assertEquals(player.getY(), weapon.getY());
//        assertEquals(player.getRotation(), weapon.getRotation());
//    }
    @Test
    public void testProcessWhenMovingUp() {
        PlayerControlSystem playerControlSystem = new PlayerControlSystem();
        Player player = new Player();
        GameData gameData = new GameData();
        World world = new World();
        world.addEntity(player);
        // Set the UP key to down
        gameData.getKeys().setKey(GameKeys.UP, true);
        double initialY = player.getY();
        playerControlSystem.process(gameData, world);
        // Check if player's Y position has increased
        assertTrue(player.getY() > initialY);
    }

    @Test
    public void testProcessWhenAtEdge() {
        PlayerControlSystem playerControlSystem = new PlayerControlSystem();
        Player player = new Player();
        GameData gameData = new GameData();
        World world = new World();
        world.addEntity(player);
        // Set player's X position to the edge of the game display
        player.setX(gameData.getDisplayWidth());
        double initialX = player.getX();
        playerControlSystem.process(gameData, world);
        // Check if player's X position has not changed
        assertEquals(initialX, player.getX());
    }

//    @Test
//    public void testProcessWhenJumpingAndAtTop() {
//        PlayerControlSystem playerControlSystem = new PlayerControlSystem();
//        Player player = new Player();
//        GameData gameData = new GameData();
//        World world = new World();
//        world.addEntity(player);
//        // Set player's jumping status to true and Y position to the top of the jump
//        player.setJumping(true);
//        player.setY(gameData.getDisplayHeight());
//        double initialY = player.getY();
//        playerControlSystem.process(gameData, world);
//        // Check if player's Y position has increased due to gravity
//        assertTrue(player.getY() > initialY);
//    }
}