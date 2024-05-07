package dk.sdu.mmmi.cbse.collisionsystem;

import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertSame;

public class InterfaceTestCollision {
    @Test
    public void testCollisionImplementsInterface() {
        CollisionControlSystem collisionControlSystem = new CollisionControlSystem();
        assertSame(collisionControlSystem.getClass().getInterfaces()[0], IEntityProcessingService.class,
                "CollisionControlSystem should implement the interface IEntityProcessingService");
    }
}


