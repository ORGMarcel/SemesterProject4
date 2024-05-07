package dk.sdu.mmmi.cbse.playersystem;

import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertSame;

public class InterfaceTestPlayer {

    @Test
    public void testPlayerImplementsInterface() {
        PlayerControlSystem playerControlSystem = new PlayerControlSystem();
        assertSame(playerControlSystem.getClass().getInterfaces()[0], IEntityProcessingService.class,
                "PlayerControlSystem should implement the interface IEntityProcessingService");
    }
}
