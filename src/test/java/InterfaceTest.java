import dk.sdu.cbse.commonbullet.BulletSPI;
import dk.sdu.cbse.enemiessystem.EnemyControlSystem;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.playersystem.PlayerControlSystem;
import dk.sdu.cbse.weaponsystem.WeaponControlSystem;
import dk.sdu.cbse.collisionsystem.CollisionControlSystem;
import dk.sdu.cbse.bulletsystem.BulletControlSystem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InterfaceTest {
    private PlayerControlSystem playerControlSystem;
    private WeaponControlSystem weaponControlSystem;
    private CollisionControlSystem collisionControlSystem;
    private BulletControlSystem bulletControlSystem;
    private EnemyControlSystem enemyControlSystem;


    @BeforeEach
    void setUp() {
        playerControlSystem = new PlayerControlSystem();
        weaponControlSystem = new WeaponControlSystem();
        collisionControlSystem = new CollisionControlSystem();
        bulletControlSystem = new BulletControlSystem();
        enemyControlSystem = new EnemyControlSystem();
    }

    @Test
    public void testPlayerImplementsInterface() {
        assertSame(playerControlSystem.getClass().getInterfaces()[0], IEntityProcessingService.class,
                "PlayerControlSystem should implement the interface IEntityProcessingService");
    }
    @Test
    public void testEnemyImplementsInterface() {
        assertSame(enemyControlSystem.getClass().getInterfaces()[0], IEntityProcessingService.class,
                "EnemyControlSystem should implement the interface IEntityProcessingService");

    }
    @Test
    public void testCollisionImplementsInterface() {
        assertSame(collisionControlSystem.getClass().getInterfaces()[0], IEntityProcessingService.class,
                "CollisionControlSystem should implement the interface IEntityProcessingService");

    }@Test
    public void testBulletImplementsInterface() {
        Class<?>[] interfaces = bulletControlSystem.getClass().getInterfaces();
        boolean implementsInterface = false;
        boolean implementsInterface1 = false;
        for (Class<?> intf : interfaces) {
            if (intf == IEntityProcessingService.class){
                implementsInterface = true;
            } else if (intf == BulletSPI.class) {
                implementsInterface1 = true;
                break;
            }
        }
        assertTrue(implementsInterface, "BulletControlSystem should implement IEntityProcessingService");
        assertTrue(implementsInterface1, "BulletControlSystem should implement BulletSPI");
    }
    @Test
    public void testWeaponImplementsInterface() {
        assertSame(weaponControlSystem.getClass().getInterfaces()[0], IEntityProcessingService.class,
                "WeaponControlSystem should implement the interface IEntityProcessingService");

    }
}