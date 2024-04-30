package dk.sdu.mmmi.cbse.commonplayer;

import dk.sdu.mmmi.cbse.common.data.CollideableInterface;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.ColorPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;


public class Player extends Entity implements CollideableInterface {

    public Player() {
        this.add(new ColorPart(1));
//        this.add(new LifePart(5));
        this.add(new MovingPart());
    }



    @Override
    public void handleCollide() {
        LifePart playerLife = this.getPart(LifePart.class);
        playerLife.setLife(0);
//        playerLife.setLife(playerLife.getLife() - 1);
    }
}
