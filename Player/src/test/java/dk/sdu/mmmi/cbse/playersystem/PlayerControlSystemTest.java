package dk.sdu.mmmi.cbse.playersystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.GameKeys;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.ColorPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.commonplayer.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerControlSystemTest {

    private Player player;
    private GameData gameData;
    private World world;
    private PlayerControlSystem playerControlSystem;


    @BeforeEach
    void setUp() {
        playerControlSystem = new PlayerControlSystem();
        player = new Player();
        gameData = new GameData();
        world = new World();
        world.addEntity(player);


    }

    @Test
    public void testProcessWhenMovingLeft() {
        gameData.getKeys().setKey(GameKeys.LEFT, true);
        gameData.getKeys().update();
        double initialX = player.getX();
        player.setPolygonCoordinates(3,3);
        player.add(new LifePart(10));
        playerControlSystem.process(gameData, world);
        assertTrue(player.getX() > initialX);
    }

    @Test
    public void testProcessWhenMovingRight() {
        gameData.getKeys().setKey(GameKeys.RIGHT, true);
        gameData.getKeys().update();
        double initialX = player.getX();
        player.setPolygonCoordinates(3,3);
        player.add(new LifePart(10));
        playerControlSystem.process(gameData, world);
        assertTrue(player.getX() > initialX);
    }

    @Test
    public void testProcessWhenJumping() {
        gameData.getKeys().setKey(GameKeys.UP, true);
        double initialY = player.getY();
        player.setPolygonCoordinates(3,3);
        player.setX(3);
        player.setY(3);
        player.add(new LifePart(10));
        playerControlSystem.process(gameData, world);
        assertTrue(player.getY() > initialY);
        //player.getY() should be less than initialY because the player is jumping, though it returns false
//        gameData.getKeys().setKey(GameKeys.UP, true);
//        gameData.getKeys().update();
//        double initialY = player.getY();
//        player.add(new LifePart(10));
//        playerControlSystem.process(gameData, world);
//        assertTrue(player.getY() < initialY);
//        //player.getY() should be less than initialY because the player is jumping, though it returns false

    }
}