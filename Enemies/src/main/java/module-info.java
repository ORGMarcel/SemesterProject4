
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

module Enemies {
    exports dk.sdu.cbse.enemiessystem;
    requires Common;
    requires CommonBullet;
    uses dk.sdu.cbse.commonbullet.BulletSPI;
    provides IGamePluginService with dk.sdu.cbse.enemiessystem.EnemyPlugin;
    provides IEntityProcessingService with dk.sdu.cbse.enemiessystem.EnemyControlSystem;

}