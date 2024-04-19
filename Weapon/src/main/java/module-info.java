
import dk.sdu.cbse.weaponsystem.WeaponControlSystem;
import dk.sdu.cbse.weaponsystem.WeaponPlugin;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

module Weapon {
    uses dk.sdu.cbse.commonbullet.BulletSPI;
    requires Common;
    requires CommonBullet;
    requires Player;
    requires CommonPlayer;
//    requires CommonBullet;
//    uses dk.sdu.mmmi.cbse.common.bullet.BulletSPI;
    provides IGamePluginService with WeaponPlugin;
    provides IEntityProcessingService with WeaponControlSystem;
}
