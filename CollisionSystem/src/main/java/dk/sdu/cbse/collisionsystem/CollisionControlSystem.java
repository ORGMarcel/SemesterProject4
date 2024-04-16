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
        int killsOverall = world.getKillsOverall();
        int kills = world.getKills();

        // for loop for collision between bullet and Enemy
        // TODO: Old collision
        for (Entity entityEnemy : world.getEntities(Enemy.class)) {
            for (Entity entityBullet : world.getEntities(Bullet.class)) {
                if (isCollided(entityEnemy, entityBullet)) {
                    entityEnemy.setHealthPoints(entityEnemy.getHealthPoints() - 1);
                    if (entityEnemy.getHealthPoints() < 1) {
                        world.removeEntity(entityEnemy);
                        killsOverall++;
                        kills++;
                    }
                    world.removeEntity(entityBullet);
                }
            }
        }


//        // TODO: New collision
//        for (Entity entity:world.getEntities()) {
//            for (Entity entity1:world.getEntities()){
//                if(isCollided(entity, entity1) && entity.getClass() != entity1.getClass()){
//                    System.out.println(entity.getClass());
//                    System.out.println(entity1.getClass());
//                    System.out.println("Entity1 : " + entity.getWidth() + " Entity2 : " + entity1.getWidth());
//
//                    System.out.println(entity.getDmg());
//                    System.out.println(entity1.getDmg());
//                    entity.setHealthPoints(entity.getHealthPoints()-entity1.getDmg());
//                    entity1.setHealthPoints(entity1.getHealthPoints()-entity.getDmg());
//
//                    // Change hitpoints
//                    if(entity1.getHealthPoints()<1){
//                        world.removeEntity(entity);
//                        killsOverall++;
//                        kills++;
//                    }
//                    if(entity.getHealthPoints()<1){
//                        world.removeEntity(entity1);
//                        killsOverall++;
//                        kills++;
//                    }
//
//
//
////                    world.removeEntity(entity);
////                    world.removeEntity(entity1);
//                }
//            }
//
//        }


        // Update the kills in the world
        world.setKillsOverall(killsOverall);
        world.setKills(kills);


        // Check for rounds and update
        if(world.getKills() == world.getRound() * 2){
            world.setRound(world.getRound()+1);
            world.setKills(0);
            world.setRoundRunning(false);
        }


        // for loop for collision between bullet and Player
        for (Entity entityPlayer : world.getEntities(Player.class)) {
            for (Entity entityBullet : world.getEntities(Bullet.class)) {
                if (isCollided(entityPlayer, entityBullet)) {
                    entityPlayer.setHealthPoints(entityPlayer.getHealthPoints()-1);
                    if (entityPlayer.getHealthPoints()<1) {
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