package dk.sdu.mmmi.mapsystem;

import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.commonmap.CommonMap;
//import dk.sdu.mmmi.commonwall.CommonWall;
//TODO: Dont think this is componentbased
//import dk.sdu.mmmi.commonwall.WallSPI;

public class MapElement extends CommonMap {


    ElementType type;
    boolean wall;
    float length;
    float height;





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
        switch (type){
            case WALL:
                setColorInt(1);
                break;
            case WEAPON:
                setColorInt(2);
                break;
            case NOTHING:
                setColorInt(0);
        }
        this.type = type;
    }

    public boolean isWall() {
        return wall;
    }

    public void setWall(boolean wall) {
        this.wall = wall;
    }





}
