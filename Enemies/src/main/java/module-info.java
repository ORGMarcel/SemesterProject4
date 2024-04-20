
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import dk.sdu.mmmi.cbse.commonbullet.BulletSPI;
import dk.sdu.mmmi.cbse.enemiessystem.EnemyControlSystem;
import dk.sdu.mmmi.cbse.enemiessystem.EnemyPlugin;

module Enemies {
    exports dk.sdu.mmmi.cbse.enemiessystem;
    requires Common;
    requires CommonBullet;
    requires CommonEnemyy;
    uses BulletSPI;
    provides IGamePluginService with EnemyPlugin;
    provides IEntityProcessingService with EnemyControlSystem;

}
