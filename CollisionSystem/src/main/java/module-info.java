import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

module CollisionSystem {
    exports dk.sdu.cbse.collisionsystem;
    requires Common;
    requires CommonBullet;
    requires Player;
    requires Enemies;
    requires CommonEnemyy;
    requires CommonPlayer;
    provides IEntityProcessingService with dk.sdu.cbse.collisionsystem.CollisionControlSystem;
}