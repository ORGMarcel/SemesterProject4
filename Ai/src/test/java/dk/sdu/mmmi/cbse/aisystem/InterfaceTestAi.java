package dk.sdu.mmmi.cbse.aisystem;

import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertSame;

public class InterfaceTestAi {

    @Test
    public void testPlayerImplementsInterface() {
        AiControlSystem aiControlSystem = new AiControlSystem();
        assertSame(aiControlSystem.getClass().getInterfaces()[0], IEntityProcessingService.class, "AiControlSystem should implement the interface IEntityProcessingService");
    }
}