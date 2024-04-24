package dk.sdu.mmmi.cbse.commonmap;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.commonmapobject.CommonMapObject;


public class CommonMap extends Entity {

    private CommonMapObject[][] map;


    public CommonMapObject[][] getMap() {
        return map;
    }

    public void setMap(CommonMapObject[][] map) {
        this.map = map;
    }
}
