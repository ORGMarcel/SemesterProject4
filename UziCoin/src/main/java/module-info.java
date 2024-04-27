//import dk.sdu.mmmi.cbse.uzicoin.UziCoinPlugin;
import dk.sdu.mmmi.cbse.uzicoin.UziCoinControlSystem;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

module UziCoin {
    requires Common;
    requires CommonCoin;
//    provides IGamePluginService with UziCoinPlugin;
    provides IEntityProcessingService with UziCoinControlSystem;
}
