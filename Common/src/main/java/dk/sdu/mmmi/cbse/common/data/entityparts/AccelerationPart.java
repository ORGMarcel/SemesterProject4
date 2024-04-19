package dk.sdu.mmmi.cbse.common.data.entityparts;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;

public class AccelerationPart implements EntityPart {

    private float acceleration;

    public float getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(float acceleration) {
        this.acceleration = acceleration;
    }


    @Override
    public void process(GameData gameData, Entity entity) {
        if(getAcceleration()<2){
            setAcceleration((float) (getAcceleration()+0.1));
        }
    }




}
