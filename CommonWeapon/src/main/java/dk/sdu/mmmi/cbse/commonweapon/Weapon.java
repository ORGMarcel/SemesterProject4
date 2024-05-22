package dk.sdu.mmmi.cbse.commonweapon;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.entityparts.ColorPart;


public class Weapon extends Entity {

    public Weapon(){
        this.add(new ColorPart(1));
    }

    private boolean isShooting = false;
    private int durability = 10;
    private boolean Equipped = false;

    public boolean isShooting() {
        return isShooting;
    }

    public void setShooting(boolean shooting) {
        isShooting = shooting;
    }

    public boolean isEquipped() {
        return Equipped;
    }

    public void setEquipped(boolean equipped) {
        Equipped = equipped;
    }

    public int getDurability() {
        return durability;
    }

    public void setDurability(int durability) {
        this.durability = durability;
    }
}




