import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.collisionsystem.CollisionControlSystem;

module CollisionSystem {
    exports dk.sdu.mmmi.cbse.collisionsystem;
    requires Common;
    requires CommonBullet;
    requires Player;
    requires Enemies;
    requires CommonEnemyy;
    requires CommonPlayer;
    requires CommonWeapon;
    requires CommonMapObject;
    requires CommonObstacle;
    requires CommonWeaponCoin;
    provides IEntityProcessingService with CollisionControlSystem;
}