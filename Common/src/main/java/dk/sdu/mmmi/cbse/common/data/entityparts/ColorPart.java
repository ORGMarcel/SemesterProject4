package dk.sdu.mmmi.cbse.common.data.entityparts;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;

public class ColorPart implements EntityPart {

    // 0 = Invisible
    // 1 = Black
    // 2 = Red

    private int colorInt = 1;

    public ColorPart(int colorInt) {
        this.colorInt = colorInt;
    }


    public int getColorInt() {
        return colorInt;
    }

    public void setColorInt(int colorInt) {
        this.colorInt = colorInt;
    }




    @Override
    public void process(GameData gameData, Entity entity) {
        //intentionally blank, the color does not need processing as of now
        // if an entity will be changing colors during the game, this is where to add it
    }
}
