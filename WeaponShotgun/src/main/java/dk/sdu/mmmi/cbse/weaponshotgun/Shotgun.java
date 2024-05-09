package dk.sdu.mmmi.cbse.weaponshotgun;

import dk.sdu.mmmi.cbse.commonweapon.Weapon;

public class Shotgun extends Weapon {

    private boolean isShooting = false;


    public boolean isShooting() {
        return isShooting;
    }

    public void setShooting(boolean shooting) {
        isShooting = shooting;
    }
}
