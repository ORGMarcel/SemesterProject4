package dk.sdu.mmmi.cbse.bulletsystem;

import dk.sdu.mmmi.cbse.commonbullet.Bullet;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.commonbullet.BulletSPI;
import dk.sdu.mmmi.cbse.bulletsystem.BulletControlSystem;


public class InterfaceTestBullet {

    @Test
    public void testBulletImplementsInterface() {
        BulletControlSystem bulletControlSystem = new BulletControlSystem();
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
}
