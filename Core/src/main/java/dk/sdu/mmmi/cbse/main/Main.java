package dk.sdu.mmmi.cbse.main;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.GameKeys;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import static java.lang.Math.sqrt;
import static java.util.stream.Collectors.toList;

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

    private List<TileEntity> mapEntities; // Declare list of entities

    public class TileEntity {
        private static final int TILE_SIZE = 32; // Adjust as needed
        private boolean isWall;
        private int x;
        private int y;

        public TileEntity(boolean isWall, int x, int y) {
            this.isWall = isWall;
            this.x = x;
            this.y = y;
        }

        public boolean isWall() {
            return isWall;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public int getTileSize() {
            return TILE_SIZE;
        }

        public int getWidth() {
            return TILE_SIZE;
        }

        public int getHeight() {
            return TILE_SIZE;
        }
    }

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
        // Generate the map and get the list of entities
        mapEntities = generateMap();

        // Draw the map using the list of entities
        drawMap(root, mapEntities);

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

    private List<TileEntity> generateMap() {
        List<TileEntity> mapEntities = new ArrayList<>();
        Random rand = new Random();
        float wallProbability = 0.2f; // Adjust as needed

        for (int i = 0; i < MAP_HEIGHT; i++) {
            for (int j = 0; j < MAP_WIDTH; j++) {
                boolean isWall = (i == 0 || j == 0 || i == MAP_HEIGHT - 1 || j == MAP_WIDTH - 1)
                        || (rand.nextFloat() < wallProbability);
                mapEntities.add(new TileEntity(isWall, j * tileSize, i * tileSize));
            }
        }

        return mapEntities;
    }

    private void drawMap(Pane pane, List<TileEntity> mapEntities) {
        for (TileEntity entity : mapEntities) {
            Rectangle rect = new Rectangle(entity.getX(), entity.getY(), tileSize, tileSize);
            if (entity.isWall()) {
                rect.setFill(Color.BLACK); // Wall
            } else {
                rect.setFill(Color.TRANSPARENT); // Empty space
            }
            pane.getChildren().add(rect);
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


                System.out.println("Entities in the game world:");
                for (Entity entity : world.getEntities()) {
                    System.out.println(entity);
                }

                System.out.println("Tile entities in the map:");
                for (TileEntity tileEntity : mapEntities) {
                    System.out.println(tileEntity);
                }
            }

        }.start();
    }

    public class CollisionControlSystem {

        public boolean isCollided(Entity player, TileEntity tile) {
            double playerLeft = player.getX();
            double playerRight = player.getX() + player.getWidth();
            double playerTop = player.getY();
            double playerBottom = player.getY() + player.getHeight();

            double wallLeft = tile.getWidth();
            double wallRight = tile.getWidth() + tile.getWidth();
            double wallTop = tile.getHeight();
            double wallBottom = tile.getHeight() + tile.getHeight();

            return playerLeft < wallRight &&
                    playerRight > wallLeft &&
                    playerTop < wallBottom &&
                    playerBottom > wallTop;
        }
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

    public boolean isCollided(Entity e1, Entity e2) {

        double x1 = e1.getX();
        double y1 = e1.getY();

        double x2 = e2.getX();
        double y2 = e2.getY();

        double result = sqrt(((x1 - x2) * (x1 - x2)) + ((y1 - y2) * (y1 - y2)));
        double e1Width = e1.getWidth() / 2;
        double e2Width = e2.getWidth() / 2;


        if (result < e1Width + e2Width) {
            return true;
        }

        return false;
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
