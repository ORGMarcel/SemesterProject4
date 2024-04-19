
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

module Player {
    exports dk.sdu.mmmi.playersystem;
    requires Common;
    requires CommonBullet;
    requires CommonPlayer;
    uses dk.sdu.cbse.commonbullet.BulletSPI;
    provides IGamePluginService with dk.sdu.mmmi.playersystem.PlayerPlugin;
    provides IEntityProcessingService with dk.sdu.mmmi.playersystem.PlayerControlSystem;

}
