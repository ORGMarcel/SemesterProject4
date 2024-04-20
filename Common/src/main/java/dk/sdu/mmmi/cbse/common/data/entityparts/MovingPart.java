package dk.sdu.mmmi.cbse.common.data.entityparts;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;

public class MovingPart implements EntityPart {

    private float acceleration = 0;

    private boolean atObstacle = false;


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
        if(getAcceleration()<2 && !atObstacle){
            setAcceleration((float) (getAcceleration()+0.1));
        }
//        if(atObstacle){
////            setAcceleration(-2.1F);
//            setAtObstacle(false);
//        }
    }




}
