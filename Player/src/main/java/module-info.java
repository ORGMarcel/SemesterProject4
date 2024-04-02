
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

module Player {
    exports dk.sdu.mmmi.playersystem;
    uses dk.sdu.cbse.commonbullet.BulletSPI;
    requires Common;
    requires CommonBullet;
//    requires CommonBullet;
//    uses dk.sdu.mmmi.cbse.common.bullet.BulletSPI;
    provides IGamePluginService with dk.sdu.mmmi.playersystem.PlayerPlugin;
    provides IEntityProcessingService with dk.sdu.mmmi.playersystem.PlayerControlSystem;
}
