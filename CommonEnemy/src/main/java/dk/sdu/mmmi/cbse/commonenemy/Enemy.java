package dk.sdu.mmmi.cbse.commonenemy;

import dk.sdu.mmmi.cbse.common.data.CollideableInterface;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;

public class Enemy extends Entity implements CollideableInterface {



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
