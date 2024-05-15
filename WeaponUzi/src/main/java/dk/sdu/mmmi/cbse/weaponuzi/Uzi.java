package dk.sdu.mmmi.cbse.weaponuzi;

import dk.sdu.mmmi.cbse.commonweapon.Weapon;

public class Uzi extends Weapon {

    private boolean isShooting = false;


    public boolean isShooting() {
        return isShooting;
    }

    public void setShooting(boolean shooting) {
        isShooting = shooting;
    }
}
