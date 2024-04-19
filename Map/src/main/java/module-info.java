
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

module Map {
    exports dk.sdu.mmmi.mapsystem;
    requires Common;
    requires CommonMap;
//    requires CommonBullet;
//    uses dk.sdu.mmmi.cbse.common.bullet.BulletSPI;
    provides IGamePluginService with dk.sdu.mmmi.mapsystem.MapPlugin;
    provides IEntityProcessingService with dk.sdu.mmmi.mapsystem.MapControlSystem;
}
