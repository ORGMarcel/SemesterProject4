package dk.sdu.mmmi.cbse.bulletsystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
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
        World world = new World();
        Entity bullet = bulletControlSystem.createBullet(shooter, gameData);
        world.addEntity(bullet);
        bulletControlSystem.process(gameData, world);
        // Check if bullet's position has changed
        assertNotEquals(shooter.getX(), bullet.getX());
        assertNotEquals(shooter.getY(), bullet.getY());
        // Check if bullet is removed when out of bounds
        bullet.setX(gameData.getDisplayWidth() + 1);
        bulletControlSystem.process(gameData, world);
        assertFalse(world.getEntities().contains(bullet));
    }

    @Test
    public void testCreateBullet() {
        BulletControlSystem bulletControlSystem = new BulletControlSystem();
        Entity shooter = new Entity();
        GameData gameData = new GameData();
        Entity bullet = bulletControlSystem.createBullet(shooter, gameData);
        assertNotNull(bullet);
        //LifePart lifePart = new LifePart(5);
        //bullet.add(lifePart);
        // assertEquals(3, ((LifePart) bullet.getPart(LifePart.class)).getLife());
    }


}