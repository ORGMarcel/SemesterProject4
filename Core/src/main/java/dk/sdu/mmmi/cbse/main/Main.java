package dk.sdu.mmmi.cbse.main;
import dk.sdu.cbse.collisionsystem.CollisionControlSystem;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.GameKeys;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;

import java.util.Collection;
import java.util.Map;
import java.util.ServiceLoader;
import java.util.concurrent.ConcurrentHashMap;
import static java.util.stream.Collectors.toList;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {

    private final GameData gameData = new GameData();
    private final World world = new World();
    private final Map<Entity, Polygon> polygons = new ConcurrentHashMap<>();
    private final Pane gameWindow = new Pane();

    private final CollisionControlSystem collisionSystem = new CollisionControlSystem(); // Instantiate the Collision Control System

    public static void main(String[] args) {
        launch(Main.class);
    }

    @Override
    public void start(Stage window) throws Exception {
        Text text = new Text(10, 20, "Enemies killed overall: " + world.getKillsOverall());
        Text text2 = new Text(10, 40, "Enemies killed this round: " + world.getKills());
        Text roundCounter = new Text(10, 60, "Round Counter: " + world.getRound());

        gameWindow.setPrefSize(gameData.getDisplayWidth(), gameData.getDisplayHeight());
        gameWindow.getChildren().addAll(text, text2, roundCounter);


        Scene scene = new Scene(gameWindow);
        scene.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.LEFT)) {
                gameData.getKeys().setKey(GameKeys.LEFT, true);
            }
            if (event.getCode().equals(KeyCode.RIGHT)) {
                gameData.getKeys().setKey(GameKeys.RIGHT, true);
            }
            if (event.getCode().equals(KeyCode.UP)) {
                gameData.getKeys().setKey(GameKeys.UP, true);
            }
            if (event.getCode().equals(KeyCode.SPACE)) {
                gameData.getKeys().setKey(GameKeys.SPACE, true);
            }
        });
        scene.setOnKeyReleased(event -> {
            if (event.getCode().equals(KeyCode.LEFT)) {
                gameData.getKeys().setKey(GameKeys.LEFT, false);
            }
            if (event.getCode().equals(KeyCode.RIGHT)) {
                gameData.getKeys().setKey(GameKeys.RIGHT, false);
            }
            if (event.getCode().equals(KeyCode.UP)) {
                gameData.getKeys().setKey(GameKeys.UP, false);
            }
            if (event.getCode().equals(KeyCode.SPACE)) {
                gameData.getKeys().setKey(GameKeys.SPACE, false);
            }

            if (event.getCode().equals(KeyCode.SPACE)) {
                gameData.getKeys().setKey(GameKeys.SPACE, false);
            }

        });

        // Lookup all Game Plugins using ServiceLoader
        for (IGamePluginService iGamePlugin : getPluginServices()) {
            iGamePlugin.start(gameData, world);
        }
        for (Entity entity : world.getEntities()) {

            Polygon polygon = new Polygon(entity.getPolygonCoordinates());
            polygons.put(entity, polygon);
            gameWindow.getChildren().add(polygon);
        }


        // Create a wall entity
        Entity wall = createWallEntity();
        wall.setType("Wall");
        world.addEntity(wall);

        // Process collision control system
        collisionSystem.process(gameData, world);

        render();

        window.setScene(scene);
        window.setTitle("Shadow Shuriken");
        window.show();
    }

    private Entity createWallEntity() {
        Entity wall = new Entity();
        wall.setType("Wall"); // Set the type of the entity to "Wall"
        wall.setX(0);
        wall.setY(gameData.getDisplayHeight() - 10); // Adjust as needed
        wall.setPolygonCoordinates(0, 0, gameData.getDisplayWidth(), 0, gameData.getDisplayWidth(), 10, 0, 10); // Adjust as needed
        return wall;
    }








    private void render() {
        new AnimationTimer() {
            private long then = 0;

            @Override
            public void handle(long now) {
                update();
                draw();
                gameData.getKeys().update();
                // Update the text displaying the number of kills
                updateKillsOverallText();
                updateKillsText();
                updateRoundsText();
            }

        }.start();
    }

    private void updateKillsOverallText() {
        Text text = (Text) gameWindow.getChildren().get(0); // Assuming the text is the first child
        text.setText("Enemies killed overall: " + world.getKillsOverall());
//        Text text2 = (Text) gameWindow.getChildren().get(0); // Assuming the text is the first child
//        text2.setText("Enemies killed this round: " + world.getKills());
    }

    private void updateKillsText() {
        Text text = (Text) gameWindow.getChildren().get(1); // Assuming the text is the first child
        text.setText("Enemies killed this round: " + world.getKills());
    }


    private void updateRoundsText() {
        Text text = (Text) gameWindow.getChildren().get(2); // Assuming the text is the first child
        text.setText("Round counter: " + world.getRound());
    }

    private void update() {

        // Update
        for (IEntityProcessingService entityProcessorService : getEntityProcessingServices()) {
            entityProcessorService.process(gameData, world);
        }
        for (Entity entity : world.getEntities()) {
            if (polygons.get(entity) == null){
                Polygon polygon = new Polygon(entity.getPolygonCoordinates());
                polygons.put(entity, polygon);
                gameWindow.getChildren().add(polygon);
            }
        }


//        for (IPostEntityProcessingService postEntityProcessorService : getPostEntityProcessingServices()) {
//            postEntityProcessorService.process(gameData, world);
//        }

    }

    private void draw() {

        for (Entity entityInPolygons : polygons.keySet()) {
            if(!world.getEntities().contains(entityInPolygons)){
                gameWindow.getChildren().remove(polygons.get(entityInPolygons));
                polygons.remove(entityInPolygons);
            }
        }



        for (Entity entity : world.getEntities()) {
            Polygon polygon = polygons.get(entity);
            if (polygon == null) {
                polygon = new Polygon(entity.getPolygonCoordinates());
                polygons.put(entity, polygon);
                gameWindow.getChildren().add(polygon);
            }
            polygon.setTranslateX(entity.getX());
            polygon.setTranslateY(entity.getY());
            polygon.setRotate(entity.getRotation());
        }

    }

    private Collection<? extends IGamePluginService> getPluginServices() {
        return ServiceLoader.load(IGamePluginService.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }

    private Collection<? extends IEntityProcessingService> getEntityProcessingServices() {
        return ServiceLoader.load(IEntityProcessingService.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }

    private Collection<? extends IPostEntityProcessingService> getPostEntityProcessingServices() {
        return ServiceLoader.load(IPostEntityProcessingService.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }
}
