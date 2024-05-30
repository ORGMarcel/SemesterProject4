import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

module Core {
    requires Common;
    requires javafx.graphics;
    opens dk.sdu.mmmi.cbse.main to javafx.graphics;
    uses IGamePluginService;
    uses IEntityProcessingService;
    uses dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;

}

