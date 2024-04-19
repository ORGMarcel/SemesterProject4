package dk.sdu.mmmi.commonwall;

import dk.sdu.mmmi.cbse.common.data.World;

public interface WallSPI {

    CommonWall createWall(World world, int positionX, int positionY);

}
