package dk.sdu.mmmi.cbse.weaponsystem;

import dk.sdu.mmmi.cbse.commonweapon.Weapon;

public class Shuriken extends Weapon {

    private boolean isShooting = false;


    public boolean isShooting() {
        return isShooting;
    }

    public void setShooting(boolean shooting) {
        isShooting = shooting;
    }
}
