package dk.sdu.mmmi.cbse.commonplayer;

import dk.sdu.mmmi.cbse.common.data.CollideableInterface;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.ColorPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;

/**
 *
 * @author Emil
 */
public class Player extends Entity implements CollideableInterface {

    public Player() {
        this.add(new ColorPart(1));
//        this.add(new LifePart(5));
        this.add(new MovingPart());
    }


    private boolean jumping;


    public boolean isJumping() {
        return jumping;
    }

    public void setJumping(boolean jumping) {
        this.jumping = jumping;
    }


    @Override
    public void handleCollide() {
        LifePart playerLife = this.getPart(LifePart.class);
        playerLife.setLife(0);
//        playerLife.setLife(playerLife.getLife() - 1);
    }
}
