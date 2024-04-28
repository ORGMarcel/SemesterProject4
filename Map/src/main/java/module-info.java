
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import dk.sdu.mmmi.cbse.mapsystem.MapControlSystem;
import dk.sdu.mmmi.cbse.mapsystem.MapPlugin;

module Map {
    exports dk.sdu.mmmi.cbse.mapsystem;
    requires Common;
    requires CommonMap;
    requires CommonMapObject;
    requires CommonWeaponCoin;
    requires CommonObstacle;
    requires CommonInvisibleObject;
    requires CommonPlayer;
    requires CommonMapPlayer;
    requires CommonEnemyy;
    requires CommonMapEnemy;
//    requires CommonBullet;
//    uses dk.sdu.mmmi.cbse.common.bullet.BulletSPI;
    provides IGamePluginService with MapPlugin;
    provides IEntityProcessingService with MapControlSystem;
}
