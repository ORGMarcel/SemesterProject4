package dk.sdu.mmmi.mapsystem;

import dk.sdu.mmmi.cbse.common.data.entityparts.ColorPart;
import dk.sdu.mmmi.cbse.commonmap.CommonMap;
//import dk.sdu.mmmi.commonwall.CommonWall;
//TODO: Dont think this is componentbased
//import dk.sdu.mmmi.commonwall.WallSPI;

public class MapElement extends CommonMap {


    ElementType type;
    float length;
    float height;

    public MapElement(){
        this.add(new ColorPart(0));
    }





    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getLength() {
        return length;
    }

    public void setLength(float length) {
        this.length = length;
    }

    public ElementType getType() {
        return type;
    }

    public void setType(ElementType type) {
        ColorPart colorPart = this.getPart(ColorPart.class);
        switch (type){
            case WALL:
                colorPart.setColorInt(1);
                break;
            case WEAPON:
                colorPart.setColorInt(2);
                break;
            case NOTHING:
                colorPart.setColorInt(3);
        }
        this.type = type;
    }







}
