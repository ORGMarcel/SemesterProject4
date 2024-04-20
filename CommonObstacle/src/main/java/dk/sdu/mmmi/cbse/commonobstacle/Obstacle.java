package dk.sdu.mmmi.cbse.commonobstacle;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.entityparts.ColorPart;
import dk.sdu.mmmi.cbse.commonmapobject.CommonMapObject;


public class Obstacle extends CommonMapObject {

    public Obstacle() {
        this.add(new ColorPart(1));
    }

}


