package dk.sdu.mmmi.cbse.weaponsystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.entityparts.ColorPart;


public class Weapon extends Entity {

    public Weapon(){
        this.add(new ColorPart(1));
    }

}

