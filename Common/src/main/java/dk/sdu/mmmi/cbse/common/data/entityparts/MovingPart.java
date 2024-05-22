package dk.sdu.mmmi.cbse.common.data.entityparts;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;

public class MovingPart implements EntityPart {

    private float acceleration = 0;

    private boolean atObstacle = false;

    private boolean jumping;


    public boolean isJumping() {
        return jumping;
    }

    public void setJumping(boolean jumping) {
        this.jumping = jumping;
    }


    public boolean isAtObstacle() {
        return atObstacle;
    }

    public void setAtObstacle(boolean atObstacle) {
        this.atObstacle = atObstacle;
    }

    public float getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(float acceleration) {
        this.acceleration = acceleration;
    }


    @Override
    public void process(GameData gameData, Entity entity) {
        if(getAcceleration()<3 && !atObstacle){
            setAcceleration((float) (getAcceleration()+0.2));
        }
        // TODO: Placeholder, it should not only do it when acceleration is positive
        if(getAcceleration()>0){
            setJumping(false);
        }
//        if(atObstacle){
////            setAcceleration(-2.1F);
//            setAtObstacle(false);
//        }

        // TODO: When moving everything about moving into this, make sure to set isJumping to false when acceleration is positive


    }




}
