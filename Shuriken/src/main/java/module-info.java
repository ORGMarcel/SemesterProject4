import dk.sdu.mmmi.cbse.weaponsystem.ShurikenWeaponControlSystem;
import dk.sdu.mmmi.cbse.weaponsystem.ShurikenWeaponPlugin;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import dk.sdu.mmmi.cbse.commonbullet.BulletSPI;

module Weapon {
    uses BulletSPI;
    requires Common;
    requires CommonBullet;
    requires Player;
    requires CommonPlayer;
    requires CommonWeapon;
//    requires CommonBullet;
//    uses dk.sdu.mmmi.cbse.common.bullet.BulletSPI;
    provides IGamePluginService with ShurikenWeaponPlugin;
    provides IEntityProcessingService with ShurikenWeaponControlSystem;
}
