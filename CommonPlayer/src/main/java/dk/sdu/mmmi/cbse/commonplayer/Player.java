package dk.sdu.mmmi.cbse.commonplayer;

import dk.sdu.mmmi.cbse.common.data.ICollideable;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.ColorPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.commonweapon.Weapon;

import java.util.ArrayList;
import java.util.List;


public class Player extends Entity implements ICollideable {


    List<Weapon> inventory = new ArrayList<Weapon>();
    private int currentWeapon = 0;
    Weapon equippedWeapon;

    public Player() {
        this.add(new ColorPart(1));
//        this.add(new LifePart(5));
        this.add(new MovingPart());
    }

    public Weapon getEquippedWeapon() {
        return equippedWeapon;
    }

    public void setEquippedWeapon(Weapon equippedWeapon) {
        this.equippedWeapon = equippedWeapon;
    }

    public int getCurrentWeapon() {
        return currentWeapon;
    }

    public void setCurrentWeapon(int currentWeapon) {
        this.currentWeapon = currentWeapon;
    }

    public List<Weapon> getInventory() {
        return inventory;
    }

    public void setInventory(List<Weapon> inventory) {
        this.inventory = inventory;
    }

    public void addWeaponToInventory(Weapon weapon) {

        for (int i = 0; i < inventory.size(); i++) {
            if(inventory.get(i).getClass()==weapon.getClass()){
                inventory.get(i).setDurability(inventory.get(i).getDurability()+weapon.getDurability());
                return;
            }

        }
        inventory.add(weapon);

    }

    // Remove from inventory method
    public void removeWeaponFromInventory(Weapon weapon) {
        inventory.remove(weapon);
    }



    @Override
    public void handleCollide() {
        LifePart playerLife = this.getPart(LifePart.class);
        playerLife.setLife(0);
//        playerLife.setLife(playerLife.getLife() - 1);
    }
}
