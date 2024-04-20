package dk.sdu.mmmi.cbse.common.data;

import dk.sdu.mmmi.cbse.common.data.entityparts.ColorPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.EntityPart;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;


public class Entity implements Serializable {

    private final UUID ID = UUID.randomUUID();
    
    private double[] polygonCoordinates;
    private double x;
    private double y;
    private double rotation;

    private Map<Class, EntityPart> parts;



//    protected EntityType entityType;
//
//    private int healthPoints;
//
//    private int dmg;


//    public void setEntityType(EntityType entityType) {
//        this.entityType = entityType;
//    }


    public Entity() {
        parts = new ConcurrentHashMap<>();
        ColorPart colorPart = new ColorPart(0);
        add(colorPart);
    }


    public void add(EntityPart part) {
        parts.put(part.getClass(), part);
    }


    public void remove(Class partClass) {
        parts.remove(partClass);
    }


    public <E extends EntityPart> E getPart(Class partClass) {
        return (E) parts.get(partClass);
    }
            

    public String getID() {
        return ID.toString();
    }

    public double getWidth(){
        double[] coordinates = getPolygonCoordinates();
        Double max = Arrays.stream(coordinates).max().orElse(-1);
        return max*2;
    }

    public void setPolygonCoordinates(double... coordinates ) {
        this.polygonCoordinates = coordinates;
    }

    public double[] getPolygonCoordinates() {
        return polygonCoordinates;
    }
       

    public void setX(double x) {
        this.x =x;
    }

    public double getX() {
        return x;
    }

    
    public void setY(double y) {
        this.y = y;
    }

    public double getY() {
        return y;
    }

    public void setRotation(double rotation) {
        this.rotation = rotation;
    }

    public double getRotation() {
        return rotation;
    }


//    public void setHealthPoints(int healthPoints) {
//        this.healthPoints = healthPoints;
//    }
//
//
//    public int getHealthPoints() {
//        return healthPoints;
//    }
//
//
//    public void setDmg(int dmg) {
//        this.dmg = dmg;
//    }
//
//
//    public int getDmg() {
//        return dmg;
//    }



        

}
