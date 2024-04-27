import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import dk.sdu.mmmi.cbse.shotguncoin.ShotgunCoinPlugin;

module ShotgunCoin {
    requires Common;
    requires CommonCoin;
    provides IGamePluginService with ShotgunCoinPlugin;
//    provides IEntityProcessingService with UziWeaponControlSystem;
}
