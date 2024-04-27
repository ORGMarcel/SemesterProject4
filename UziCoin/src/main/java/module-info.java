import dk.sdu.mmmi.cbse.uzicoin.UziCoinPlugin;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

module UziCoin {
    requires Common;
    requires CommonCoin;
    provides IGamePluginService with UziCoinPlugin;
//    provides IEntityProcessingService with UziWeaponControlSystem;
}
