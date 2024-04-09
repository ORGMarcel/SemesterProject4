module Core {
    requires Common;
    requires javafx.graphics;
    requires CollisionSystem;
    opens dk.sdu.mmmi.cbse.main to javafx.graphics;
    uses dk.sdu.mmmi.cbse.common.services.IGamePluginService;
    uses dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

}

