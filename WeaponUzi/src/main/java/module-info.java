import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import dk.sdu.mmmi.cbse.commonbullet.BulletSPI;
import dk.sdu.mmmi.cbse.weaponuzi.UziCoinSpawnSystem;
import dk.sdu.mmmi.cbse.weaponuzi.UziWeaponControlSystem;
import dk.sdu.mmmi.cbse.weaponuzi.UziWeaponPlugin;

module UziShotgun {
    uses BulletSPI;
    requires Common;
    requires CommonBullet;
    requires CommonPlayer;
    requires CommonWeapon;
    provides IGamePluginService with UziWeaponPlugin;
    provides IEntityProcessingService with UziWeaponControlSystem, UziCoinSpawnSystem;
}
