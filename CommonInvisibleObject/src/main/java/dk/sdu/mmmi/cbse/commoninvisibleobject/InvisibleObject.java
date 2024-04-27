package dk.sdu.mmmi.cbse.commoninvisibleobject;

import dk.sdu.mmmi.cbse.common.data.entityparts.ColorPart;
import dk.sdu.mmmi.cbse.commonmapobject.CommonMapObject;

public class InvisibleObject extends CommonMapObject {

    public InvisibleObject() {
        this.add(new ColorPart(0));
    }

}
