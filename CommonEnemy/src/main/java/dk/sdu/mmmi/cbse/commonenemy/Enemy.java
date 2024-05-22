package dk.sdu.mmmi.cbse.commonenemy;

import dk.sdu.mmmi.cbse.common.data.CollideableInterface;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.entityparts.ColorPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.commonpath.CommonPath;

public class Enemy extends Entity implements CollideableInterface {


    CommonPath path;
    int currentPathIndex = 0;
    private double speed = 1;
    boolean isShooting = false;


    public boolean isShooting() {
        return isShooting;
    }

    public void setShooting(boolean isShooting) {
        this.isShooting = isShooting;
    }


    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public int getCurrentPathIndex() {
        return currentPathIndex;
    }

    public void setCurrentPathIndex(int currentPathIndex) {
        this.currentPathIndex = currentPathIndex;
    }



    public CommonPath getPath() {
        return path;
    }

    public void setPath(CommonPath path) {
        this.path = path;
        setCurrentPathIndex(0);
    }



    public Enemy() {
        this.add(new ColorPart(1));
        this.add(new LifePart(3));
    }


//    private int dmg = 10;
//
//
//    @Override
//    public int getDmg() {
//        return dmg;
//    }
//
//    @Override
//    public void setDmg(int dmg) {
//        this.dmg = dmg;
//    }


    @Override
    public void handleCollide() {
        LifePart lifePart = this.getPart(LifePart.class);
        lifePart.setLife(0);
//        lifePart.setIsHit(true);
//        lifePart.setLife(lifePart.getLife() - damage);
    }



//    public void attack(AttackableInterface entity) {
//            entity.handleAttack(this.dmg);
//    }


}
