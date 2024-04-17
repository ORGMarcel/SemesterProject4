package dk.sdu.cbse.collisionsystem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import dk.sdu.cbse.commonbullet.Bullet;
import dk.sdu.cbse.enemiessystem.Enemy;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.playersystem.Player;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class CollisionControlSystemTest {

    private CollisionControlSystem collisionControlSystem;
    private GameData gameData;
    private World world;


    @BeforeEach
    public void setUp() {
        collisionControlSystem = new CollisionControlSystem();
        gameData = new GameData();
        world = new World();
    }
    @Test
    public void testIsCollided_ReturnsTrue_WhenEntitiesCollide() {
        // Prepare test data
        Entity entity1 = new Enemy();
        entity1.setX(100);
        entity1.setY(100);
        entity1.setWidth(20); // Assuming the width is 20 for testing

        Entity entity2 = new Bullet();
        entity2.setX(110);
        entity2.setY(110);
        entity2.setWidth(20); // Assuming the width is 20 for testing

        // Call the method under test
        boolean result = collisionControlSystem.isCollided(entity1, entity2);

        // Assertion
        assertTrue(result); // Expecting collision
    }

    @Test
    public void testIsCollided_ReturnsFalse_WhenEntitiesDoNotCollide() {
        // Prepare test data
        Entity entity1 = new Enemy();
        entity1.setX(100);
        entity1.setY(100);
        entity1.setWidth(20); // Assuming the width is 20 for testing

        Entity entity2 = new Bullet();
        entity2.setX(200);
        entity2.setY(200);
        entity2.setWidth(20); // Assuming the width is 20 for testing

        // Call the method under test
        boolean result = collisionControlSystem.isCollided(entity1, entity2);

        // Assertion
        assertFalse(result); // Expecting no collision
    }

    // Add more tests to cover other scenarios and edge cases
}