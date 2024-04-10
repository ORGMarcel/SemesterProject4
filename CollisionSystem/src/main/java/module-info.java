import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

module CollisionSystem {
    requires Common;
    requires CommonBullet;
    requires Player;
    requires Enemies;
    provides IEntityProcessingService with dk.sdu.cbse.collisionsystem.CollisionControlSystem;
}