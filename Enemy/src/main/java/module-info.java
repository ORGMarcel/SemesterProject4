module Enemy {
    exports dk.sdu.mmmi.enemy;
    requires Common;
    //requires CommonBullet;
    //uses dk.sdu.mmmi.cbse.common.bullet.BulletSPI;
    uses dk.sdu.mmmi.cbse.common.services.IGamePluginService;
    uses dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
}