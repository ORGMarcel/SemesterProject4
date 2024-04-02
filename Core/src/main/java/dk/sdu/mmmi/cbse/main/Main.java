package dk.sdu.mmmi.cbse.main;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.GameKeys;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
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
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;

import java.util.*;

public class Main extends Application {

    private final GameData gameData = new GameData();
    private final World world = new World();
    private final Map<Entity, Polygon> polygons = new ConcurrentHashMap<>();
    private final Pane gameWindow = new Pane();

    private final Random random = new Random();
    private int tileSize;

    private final int TILE_SIZE = 20;
    private final int MAP_WIDTH = 40;
    private final int MAP_HEIGHT = 40;
    private int[][] map = new int[MAP_HEIGHT][MAP_WIDTH];




    @Override
    public void start(Stage window) throws Exception {
        gameWindow.setPrefSize(gameData.getDisplayWidth(), gameData.getDisplayHeight());

        Pane root = new Pane();
        int gameWindowWidth = 800; // Adjust as needed
        int gameWindowHeight = 800; // Adjust as needed

        generateMap(); // Generate the map

        // Calculate the tile size based on the map dimensions and the window size
        tileSize = calculateTileSize(gameWindowWidth, gameWindowHeight, MAP_WIDTH, MAP_HEIGHT);

        root.setPrefSize(gameWindowWidth, gameWindowHeight);
        drawMap(root);

        Scene scene = new Scene(root, gameWindowWidth, gameWindowHeight);
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

        render();


        window.setScene(scene);
        window.setTitle("Shadow Shuriken");
        window.show();



    }

    private int calculateTileSize(int windowWidth, int windowHeight, int mapColumns, int mapRows) {
        int tileSizeWidth = windowWidth / mapColumns;
        int tileSizeHeight = windowHeight / mapRows;
        // Use the smaller of the two to ensure the entire map fits in the window
        return Math.min(tileSizeWidth, tileSizeHeight);
    }

    private void generateMap() {
        Random rand = new Random();
        int[][] tempMap = new int[MAP_HEIGHT][MAP_WIDTH];

        // Initial random placement
        for (int i = 1; i < MAP_HEIGHT - 1; i++) {
            for (int j = 1; j < MAP_WIDTH - 1; j++) {
                tempMap[i][j] = rand.nextInt(4) == 0 ? 1 : 0; // 25% chance of being a wall
            }
        }

        // Modify the map based on neighbors to cluster walls
        for (int iteration = 0; iteration < 2; iteration++) { // Repeat to enhance clustering
            for (int i = 1; i < MAP_HEIGHT - 1; i++) {
                for (int j = 1; j < MAP_WIDTH - 1; j++) {
                    int wallsNearby = countWallsNearby(tempMap, i, j);

                    if (wallsNearby > 4) {
                        map[i][j] = 1; // Become a wall if surrounded by walls
                    } else if (wallsNearby < 2) {
                        map[i][j] = 0; // Become empty space if too isolated
                    } else {
                        map[i][j] = tempMap[i][j]; // Otherwise, keep the current state
                    }
                }
            }
        }

        // Ensure the border is always walls
        for (int i = 0; i < MAP_HEIGHT; i++) {
            for (int j = 0; j < MAP_WIDTH; j++) {
                if (i == 0 || j == 0 || i == MAP_HEIGHT - 1 || j == MAP_WIDTH - 1) {
                    map[i][j] = 1;
                }
            }
        }
    }

    private int countWallsNearby(int[][] tempMap, int y, int x) {
        int count = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (tempMap[y + i][x + j] == 1) {
                    count++;
                }
            }
        }
        return count;
    }




    private void drawMap(Pane pane) {
        for (int i = 0; i < MAP_HEIGHT; i++) {
            for (int j = 0; j < MAP_WIDTH; j++) {
                Rectangle rect = new Rectangle(j * tileSize, i * tileSize, tileSize, tileSize);
                if (map[i][j] == 1) {
                    rect.setFill(Color.BLACK); // Wall
                } else {
                    rect.setFill(Color.WHITE); // Empty space
                }
                pane.getChildren().add(rect);
            }
        }
    }






    private void render() {
        new AnimationTimer() {
            private long then = 0;

            @Override
            public void handle(long now) {
                update();
                draw();
                gameData.getKeys().update();
            }

        }.start();
    }



    private void update() {

        // Update
        for (IEntityProcessingService entityProcessorService : getEntityProcessingServices()) {
            entityProcessorService.process(gameData, world);
        }
        for (Entity entity : world.getEntities())
        {
            if (polygons.get(entity) == null)
            {
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

    public static void main(String[] args) {
        launch(Main.class);
    }
}
