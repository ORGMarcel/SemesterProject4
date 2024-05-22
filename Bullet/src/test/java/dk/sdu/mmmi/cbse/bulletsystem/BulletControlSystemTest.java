package dk.sdu.mmmi.cbse.bulletsystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import org.junit.jupiter.api.Test;
import dk.sdu.mmmi.cbse.bulletsystem.BulletControlSystem;
import dk.sdu.mmmi.cbse.commonbullet.Bullet;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.*;

class BulletControlSystemTest {

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
    }
    @Test
    public void testProcess() {
        BulletControlSystem bulletControlSystem = new BulletControlSystem();
        Entity shooter = new Entity();
        GameData gameData = new GameData();
        Entity bullet = bulletControlSystem.createBullet(shooter, gameData);
        assertNotNull(bullet);
        // Check if bullet's position is correct
        assertEquals(shooter.getX() + Math.cos(Math.toRadians(bullet.getRotation())) * 25, bullet.getX());
        assertEquals(shooter.getY() + Math.sin(Math.toRadians(bullet.getRotation())) * 25, bullet.getY());
    }

    @Test
    public void testCreateBullet() {
        BulletControlSystem bulletControlSystem = new BulletControlSystem();
        Entity shooter = new Entity();
        GameData gameData = new GameData();
        Entity bullet = bulletControlSystem.createBullet(shooter, gameData);
        assertNotNull(bullet);
        // Check if bullet's position is correct
        assertEquals(shooter.getX() + Math.cos(Math.toRadians(bullet.getRotation())) * 25, bullet.getX());
        assertEquals(shooter.getY() + Math.sin(Math.toRadians(bullet.getRotation())) * 25, bullet.getY());
    }


}