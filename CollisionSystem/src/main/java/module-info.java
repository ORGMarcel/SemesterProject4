import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;
import dk.sdu.mmmi.cbse.collisionsystem.CollisionControlSystem;

module CollisionSystem {
    exports dk.sdu.mmmi.cbse.collisionsystem;
    requires Common;
    requires CommonBullet;
    requires CommonEnemy;
    requires CommonPlayer;
    requires CommonWeapon;
    requires CommonMapObject;
    requires CommonObstacle;
    provides IPostEntityProcessingService with CollisionControlSystem;
}