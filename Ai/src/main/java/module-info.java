import dk.sdu.mmmi.cbse.aisystem.AiControlSystem;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import dk.sdu.mmmi.cbse.aisystem.AiPlugin;

module Ai {
    requires Common;
    requires CommonMapObject;
    requires CommonObstacle;
    requires CommonEnemy;
    requires CommonPlayer;
    requires CommonInvisibleObject;
//    requires CommonBullet;
//    uses dk.sdu.mmmi.cbse.common.bullet.BulletSPI;
    provides IGamePluginService with AiPlugin;
    provides IEntityProcessingService with AiControlSystem;
}
