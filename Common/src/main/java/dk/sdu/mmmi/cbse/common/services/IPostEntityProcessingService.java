package dk.sdu.mmmi.cbse.common.services;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;

/**
 *
 * @author jcs
 */
public interface IPostEntityProcessingService {

    /**
     *
     * Pre-condition: All gamedata must be loaded and a world must be set.
     * Post-condition: All processes must be terminated
     *
     * @param gameData
     * @param world
     * @throws NullPointerException if no gameData or world
     *
     */

    void process(GameData gameData, World world);
}
