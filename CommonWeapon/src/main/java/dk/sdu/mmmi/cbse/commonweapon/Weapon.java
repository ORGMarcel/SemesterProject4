package dk.sdu.mmmi.cbse.commonweapon;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.entityparts.ColorPart;


public class Weapon extends Entity {

    public Weapon(){
        this.add(new ColorPart(1));
    }

}
