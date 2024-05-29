
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import dk.sdu.mmmi.cbse.mapsystem.MapControlSystem;
import dk.sdu.mmmi.cbse.mapsystem.MapPlugin;

module Map {
    requires Common;
    requires CommonMapObject;
    requires CommonObstacle;
    requires CommonInvisibleObject;
    requires CommonPlayer;
    requires CommonEnemy;
//    requires CommonBullet;
//    uses dk.sdu.mmmi.cbse.common.bullet.BulletSPI;
    provides IGamePluginService with MapPlugin;
    provides IEntityProcessingService with MapControlSystem;
}
