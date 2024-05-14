package dk.sdu.mmmi.cbse.commonplayer;

import dk.sdu.mmmi.cbse.common.data.CollideableInterface;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.ColorPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.commonweapon.Weapon;


public class Player extends Entity implements CollideableInterface {


    Weapon[] inventory;
    private int currentWeapon;

    public Player() {
        this.add(new ColorPart(1));
//        this.add(new LifePart(5));
        this.add(new MovingPart());
    }



    public int getCurrentWeapon() {
        return currentWeapon;
    }

    public void setCurrentWeapon(int currentWeapon) {
        this.currentWeapon = currentWeapon;
    }

    public Weapon[] getInventory() {
        return inventory;
    }

    public void setInventory(Weapon[] inventory) {
        this.inventory = inventory;
    }



    @Override
    public void handleCollide() {
        LifePart playerLife = this.getPart(LifePart.class);
        playerLife.setLife(0);
//        playerLife.setLife(playerLife.getLife() - 1);
    }
}
