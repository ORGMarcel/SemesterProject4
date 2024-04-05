import dk.sdu.cbse.commonbullet.BulletSPI;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;


module Bullet {
    requires Common;
    requires CommonBullet;
//    requires CommonBullet;
    provides IGamePluginService with dk.sdu.cbse.bulletsystem.BulletPlugin;
    provides BulletSPI with dk.sdu.cbse.bulletsystem.BulletControlSystem;
    provides IEntityProcessingService with dk.sdu.cbse.bulletsystem.BulletControlSystem;
}