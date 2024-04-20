package dk.sdu.mmmi.cbse.commonbullet;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.entityparts.ColorPart;


public class Bullet extends Entity {
    public Bullet() {
        this.add(new ColorPart(2));
        ColorPart colorPart = this.getPart(ColorPart.class);
        colorPart.setColorInt(2);
    }
}



