package dk.sdu.cbse.collisionsystem;

import dk.sdu.cbse.commonbullet.Bullet;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.cbse.enemiessystem.Enemy;
import dk.sdu.mmmi.playersystem.Player;

import static java.lang.Math.sqrt;


public class CollisionControlSystem implements IEntityProcessingService {
    @Override
    public void process(GameData gameData, World world) {

        // Get current kills from the world
        int kills = world.getKills();

        // for loop for collision between bullet and Enemy
        for (Entity entityEnemy : world.getEntities(Enemy.class)) {
            for (Entity entityBullet : world.getEntities(Bullet.class)) {
                if (isCollided(entityEnemy, entityBullet)) {
                    entityEnemy.setHitPoints(entityEnemy.getHitPoints() - 1);
                    if (entityEnemy.getHitPoints() < 1) {
                        world.removeEntity(entityEnemy);
                        kills++;
                        System.out.println(kills);
                    }
                    world.removeEntity(entityBullet);
                }
            }
        }

        // Update the kills in the world
        world.setKills(kills);

        // for loop for collision between bullet and Player
        for (Entity entityPlayer : world.getEntities(Player.class)) {
            for (Entity entityBullet : world.getEntities(Bullet.class)) {
                if (isCollided(entityPlayer, entityBullet)) {
                    entityPlayer.setHitPoints(entityPlayer.getHitPoints()-1);
                    if (entityPlayer.getHitPoints()<1) {
                        world.removeEntity(entityPlayer);
                    }
                    world.removeEntity(entityBullet);
                }
            }
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

}