package dk.sdu.mmmi.cbse.weaponsystem;

import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertSame;

public class InterfaceTestWeapon {

    @Test
    public void testWeaponImplementsInterface() {
        WeaponControlSystem weaponControlSystem = new WeaponControlSystem();
        assertSame(weaponControlSystem.getClass().getInterfaces()[0], IEntityProcessingService.class, "WeaponControlSystem should implement the interface IEntityProcessingService");
    }
}
