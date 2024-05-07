package dk.sdu.mmmi.cbse.enemiessystem;

import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertSame;

public class InterfaceTestEnemy {

    @Test
    public void testCollisionImplementsInterface() {
        EnemyControlSystem enemyControlSystem = new EnemyControlSystem();
        assertSame(enemyControlSystem.getClass().getInterfaces()[0], IEntityProcessingService.class,
                "EnemyControlSystem should implement the interface IEntityProcessingService");
    }
}
