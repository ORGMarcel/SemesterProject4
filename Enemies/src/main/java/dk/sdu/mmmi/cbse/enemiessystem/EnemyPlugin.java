package dk.sdu.mmmi.cbse.enemiessystem;

import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.commonenemy.Enemy;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
public class EnemyPlugin implements IGamePluginService {

    private Entity enemy;

    public EnemyPlugin() {
    }

    @Override
    public void start(GameData gameData, World world) {

        // Add entities to the world
        enemy = createEnemyShip(gameData, gameData.getDisplayHeight()/5+500, gameData.getDisplayWidth()/5+500);
        world.addEntity(enemy);

        // Start the first round
        gameData.setRounds(1);

//        // Add entities to the world
//        enemy = createEnemyShip(gameData, gameData.getDisplayHeight()/5+100, gameData.getDisplayWidth()/5+100);
//        world.addEntity(enemy);
    }

    private Entity createEnemyShip(GameData gameData, double x, double y) {

        Enemy enemyShip = new Enemy();
        enemyShip.setPolygonCoordinates(18.0, -1.5, 12.0, -1.5, 12.0, -4.5, 9.0, -4.5, 9.0, -7.5, -3.0, -7.5, -3.0, -10.5, 0.0, -10.5, 0.0, -13.5, -15.0, -13.5, -15.0, -7.5, -12.0, -7.5, -12.0, -4.5, -9.0, -4.5, -9.0, -1.5, -15.0, -1.5, -15.0, 1.5, -9.0, 1.5, -9.0, 4.5, -12.0, 4.5, -12.0, 7.5, -15.0, 7.5, -15.0, 13.5, 0.0, 13.5, 0.0, 10.5, -6.0, 10.5, -6.0, 7.5, 6.0, 7.5, 6.0, 1.5, 12.0, 1.5, 12.0, -1.5, 6.0, -1.5, 6.0, -4.5, 12.0, -4.5, 12.0, -1.5, 18.0, -1.5);
        enemyShip.setX(x);
        enemyShip.setY(y);
        enemyShip.add(new LifePart(5));
        return enemyShip;
    }

    @Override
    public void stop(GameData gameData, World world) {
        // Remove entities
        world.removeEntity(enemy);
    }

}
