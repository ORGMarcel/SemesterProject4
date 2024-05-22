package dk.sdu.mmmi.cbse.main;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.GameKeys;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.ColorPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.EntityPart;
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
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {

    private final GameData gameData = new GameData();
    private final World world = new World();
    private final Map<Entity, Polygon> polygons = new ConcurrentHashMap<>();
    private final Pane gameWindow = new Pane();


    public static void main(String[] args) {
        launch(Main.class);
    }

    @Override
    public void start(Stage window) throws Exception {
        Text text = new Text(10, 20, "Enemies killed overall: " + world.getKillsOverall());
        Text text2 = new Text(10, 40, "Enemies killed this round: " + world.getKills());
        Text roundCounter = new Text(10, 60, "Round Counter: " + world.getRound());

        gameWindow.setPrefSize(gameData.getDisplayWidth(), gameData.getDisplayHeight());
        gameWindow.getChildren().add(text);
        gameWindow.getChildren().add(text2);
        gameWindow.getChildren().add(roundCounter);

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
            if (event.getCode().equals(KeyCode.DIGIT1)) {
                gameData.getKeys().setKey(GameKeys.NUM1, true);
            }
            if (event.getCode().equals(KeyCode.DIGIT2)) {
                gameData.getKeys().setKey(GameKeys.NUM2, true);
            }
            if (event.getCode().equals(KeyCode.DIGIT3)) {
                gameData.getKeys().setKey(GameKeys.NUM3, true);
            }
            if (event.getCode().equals(KeyCode.DIGIT4)) {
                gameData.getKeys().setKey(GameKeys.NUM4, true);
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
            if (event.getCode().equals(KeyCode.DIGIT1)) {
                gameData.getKeys().setKey(GameKeys.NUM1, false);
            }
            if (event.getCode().equals(KeyCode.DIGIT2)) {
                gameData.getKeys().setKey(GameKeys.NUM2, false);
            }
            if (event.getCode().equals(KeyCode.DIGIT3)) {
                gameData.getKeys().setKey(GameKeys.NUM3, false);
            }
            if (event.getCode().equals(KeyCode.DIGIT4)) {
                gameData.getKeys().setKey(GameKeys.NUM4, false);
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

        render();

        window.setScene(scene);
        window.setTitle("Shadow Shuriken");
        window.show();

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

//            // Check condition to make specific entities invisible
//            // TODO: Check this
//            if(entity.getColorInt() == 0){
//                polygon.setFill(Color.TRANSPARENT);
//            }else if(entity.getColorInt() == 1){
//                polygon.setFill(Color.BLACK);
//            }else if(entity.getColorInt() == 2){
//                polygon.setFill(Color.RED);
//            }else {
//                polygon.setFill(Color.BLACK); // Default visible color
//            }

            // TODO: Check this
            ColorPart colorPart = entity.getPart(ColorPart.class);

            if(colorPart.getColorInt() == 0){
                polygon.setFill(Color.TRANSPARENT);
            }else if(colorPart.getColorInt() == 1){
                polygon.setFill(Color.BLACK);
            }else if(colorPart.getColorInt() == 2){
                polygon.setFill(Color.RED);
            }else if(colorPart.getColorInt() == 3){
                polygon.setFill(Color.BLUE);
            }else {
                polygon.setFill(Color.BLACK); // Default visible color
            }

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

//    private Collection<? extends EntityPart> getEntityPart() {
//        return ServiceLoader.load(EntityPart.class).stream().map(ServiceLoader.Provider::get).collect(toList());
//    }
}
