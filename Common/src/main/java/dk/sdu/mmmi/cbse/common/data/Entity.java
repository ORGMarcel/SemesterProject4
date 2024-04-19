package dk.sdu.mmmi.cbse.common.data;

import java.io.Serializable;
import java.util.Arrays;
import java.util.UUID;


public class Entity implements Serializable {

    private final UUID ID = UUID.randomUUID();
    
    private double[] polygonCoordinates;
    private double x;
    private double y;
    private double rotation;

    // 1 = Black
    // 2 = Invisible
    // 3 = Red
    protected int colorInt = 1;


    protected EntityType entityType;

    private int healthPoints;

    private int dmg;

    private double gravity;

    private boolean immortal;

    private boolean invisible;


    public EntityType getEntityType() {
        return entityType;
    }

    public void setEntityType(EntityType entityType) {
        this.entityType = entityType;
    }


    public int getColorInt() {
        return colorInt;
    }

    public void setColorInt(int colorInt) {
        this.colorInt = colorInt;
    }



    public boolean isInvisible() {
        return invisible;
    }

    public void setInvisible(boolean invisible) {
        this.invisible = invisible;
    }

    public boolean isImmortal() {
        return immortal;
    }

    public void setImmortal(boolean immortal) {
        this.immortal = immortal;
    }


    public double getGravity() {
        return gravity;
    }

    public void setGravity(double gravity) {
        this.gravity = gravity;
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


    public void setHealthPoints(int healthPoints) {
        this.healthPoints = healthPoints;
    }


    public int getHealthPoints() {
        return healthPoints;
    }


    public void setDmg(int dmg) {
        this.dmg = dmg;
    }


    public int getDmg() {
        return dmg;
    }



        

}
