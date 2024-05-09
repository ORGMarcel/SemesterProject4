package dk.sdu.mmmi.cbse.enemiessystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.commonbullet.BulletSPI;
import dk.sdu.mmmi.cbse.commonenemy.Enemy;
import org.junit.jupiter.api.Test;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;
class EnemyControlSystemTest {

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
    }
    @Test
    public void testProcess() {
//        EnemyControlSystem enemyControlSystem = new EnemyControlSystem();
//        Entity enemy = new Enemy();
//        GameData gameData = new GameData();
//        World world = new World();
//        world.addEntity(enemy);
//        // Set the enemy's rotation
//        enemy.setRotation(45);
//        enemyControlSystem.process(gameData, world);
//        // Check if enemy's position has changed
//        assertNotEquals(0, enemy.getX());
//        assertNotEquals(0, enemy.getY());
//        // Check if enemy is removed when out of bounds
//        enemy.setX(gameData.getDisplayWidth() + 1);
//        enemyControlSystem.process(gameData, world);
//        assertTrue(world.getEntities().contains(enemy));
    }

    @Test
    public void testCreateEnemyShip() {
//        EnemyControlSystem enemyControlSystem = new EnemyControlSystem();
//        GameData gameData = new GameData();
//        Entity enemyShip = enemyControlSystem.createEnemyShip(gameData);
//        // Check if enemy ship's position is within the game display
//        assertTrue(enemyShip.getX() >= 0 && enemyShip.getX() <= gameData.getDisplayWidth());
//        assertTrue(enemyShip.getY() >= 0 && enemyShip.getY() <= gameData.getDisplayHeight());
//        // Check if enemy ship's health points are correct
//        assertEquals(6, enemyShip.getHealthPoints());
    }

    @Test
    public void testGetBulletSPIs() {
        EnemyControlSystem enemyControlSystem = new EnemyControlSystem();
        Collection<? extends BulletSPI> bulletSPIs = enemyControlSystem.getBulletSPIs();
        // Check if the returned collection is empty
        assertTrue(bulletSPIs.isEmpty());
    }

}