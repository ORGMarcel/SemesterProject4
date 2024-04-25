import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import dk.sdu.mmmi.cbse.commonbullet.BulletSPI;
import dk.sdu.mmmi.cbse.weaponshotgun.ShotgunWeaponControlSystem;
import dk.sdu.mmmi.cbse.weaponshotgun.ShotgunWeaponPlugin;

module WeaponShotgun {
    uses BulletSPI;
    requires Common;
    requires CommonBullet;
    requires Player;
    requires CommonPlayer;
    requires CommonWeapon;
//    requires CommonBullet;
//    uses dk.sdu.mmmi.cbse.common.bullet.BulletSPI;
    provides IGamePluginService with ShotgunWeaponPlugin;
    provides IEntityProcessingService with ShotgunWeaponControlSystem;
}
