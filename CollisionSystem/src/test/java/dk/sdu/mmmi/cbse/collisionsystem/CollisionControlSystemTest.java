package dk.sdu.mmmi.cbse.collisionsystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.commonbullet.Bullet;
import dk.sdu.mmmi.cbse.commonplayer.Player;
import org.junit.jupiter.api.Test;
import dk.sdu.mmmi.cbse.collisionsystem.CollisionControlSystem;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


import static org.junit.jupiter.api.Assertions.*;
class CollisionControlSystemTest {


    @org.junit.jupiter.api.BeforeEach
    void setUp() {
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
    }

//    @Test
//    public void testIsCollided() {
//        CollisionControlSystem collisionControlSystem = new CollisionControlSystem();
//        Entity e1 = new Entity();
//        e1.setX(0);
//        e1.setY(0);
//        e1.setWidth(2);
//        Entity e2 = new Entity();
//        e2.setX(1);
//        e2.setY(1);
//        e2.setWidth(2);
//        assertTrue(collisionControlSystem.isCollided(e1, e2));
//    }

//    @Test
//    public void testProcess() {
//        CollisionControlSystem collisionControlSystem = new CollisionControlSystem();
//        GameData gameData = new GameData();
//        World world = new World();
//        Entity player = new Player();
//        player.setX(0);
//        player.setY(0);
//        player.setWidth(2);
//        player.setHealthPoints(10);
//        world.addEntity(player);
//        Entity bullet = new Bullet();
//        bullet.setX(1);
//        bullet.setY(1);
//        bullet.setWidth(2);
//        world.addEntity(bullet);
//        collisionControlSystem.process(gameData, world);
//    // Check if player's health points have decreased
//        assertEquals(9, player.getHealthPoints());
//    // Check if bullet is removed
//        assertFalse(world.getEntities().contains(bullet));
//}
}