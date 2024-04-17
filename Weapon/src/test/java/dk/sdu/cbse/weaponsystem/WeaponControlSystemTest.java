package dk.sdu.cbse.weaponsystem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import dk.sdu.cbse.commonbullet.BulletSPI;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.GameKeys;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.playersystem.Player;

class WeaponControlSystemTest {

    private WeaponControlSystem weaponControlSystem;
    private GameData gameData;
    private World world;

    @BeforeEach
    public void setUp() {
        weaponControlSystem = new WeaponControlSystem();
        gameData = new GameData();
        world = new World();
    }
    @Test
    public void testProcess_ShootBullet() {
        // Prepare test data
        Entity weapon = new Weapon();
        weapon.setX(100);
        weapon.setY(100);
        weapon.setRotation(45);

        Player player = new Player();
        player.setX(100);
        player.setY(100);
        player.setRotation(45);
        world.addEntity(player);

        gameData.getKeys().setKey(GameKeys.SPACE, true);

        // Mock BulletSPI
        BulletSPI bulletSPI = mock(BulletSPI.class);
        when(bulletSPI.createBullet(any(Entity.class), any(GameData.class))).thenReturn(new Entity());

        // Call the method under test
        weaponControlSystem.process(gameData, world);

        // Assertions
        verify(world, times(1)).addEntity(any(Entity.class)); // Check if addEntity is called
        // Add more assertions as needed
    }

    @Test
    public void testProcess_UpdateWeaponPosition() {
        // Prepare test data
        Entity weapon = new Weapon();
        weapon.setX(100);
        weapon.setY(100);
        weapon.setRotation(45);

        Player player = new Player();
        player.setX(100);
        player.setY(100);
        player.setRotation(45);
        world.addEntity(player);

        // Set keys to simulate player movement
        gameData.getKeys().setKey(GameKeys.LEFT, true);
        gameData.getKeys().setKey(GameKeys.RIGHT, true);
        gameData.getKeys().setKey(GameKeys.UP, true);

        // Call the method under test
        weaponControlSystem.process(gameData, world);

        // Assertions
        assertEquals(weapon.getX(), player.getX(), 0.001); // Check if weapon's X position matches player's X position
        assertEquals(weapon.getY(), player.getY(), 0.001); // Check if weapon's Y position matches player's Y position
        assertEquals(weapon.getRotation(), player.getRotation(), 0.001); // Check if weapon's rotation matches player's rotation
        // Add more assertions as needed
    }

    // Add more tests to cover other scenarios and edge cases
}