import dk.sdu.mmmi.cbse.aisystem.AiControlSystem;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import dk.sdu.mmmi.cbse.aisystem.AiPlugin;

module Ai {
    exports dk.sdu.mmmi.cbse.aisystem;
    requires Common;
    requires CommonMap;
    requires CommonMapObject;
    requires CommonObstacle;
    requires CommonMapPlayer;
    requires CommonMapEnemy;
    requires CommonEnemy;
    requires CommonPath;
//    requires CommonBullet;
//    uses dk.sdu.mmmi.cbse.common.bullet.BulletSPI;
    provides IGamePluginService with AiPlugin;
    provides IEntityProcessingService with AiControlSystem;
}
