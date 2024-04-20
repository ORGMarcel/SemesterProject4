package dk.sdu.mmmi.cbse.collisionsystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.commonbullet.Bullet;
import dk.sdu.mmmi.cbse.commonenemy.Enemy;
import dk.sdu.mmmi.cbse.commonplayer.Player;

public class CollisionHandler {

    public boolean handleCollision(World world, Entity co1, Entity co2) {
        Entity e1 = world.getEntity(co1.getID());
        Entity e2 = world.getEntity(co2.getID());

        // Same entity or one of them null
        if (e1 == null || e2 == null || e1.equals(e2)) {
            return false;
        }

        // Enemy and Enemy
        if (e1 instanceof Enemy && e2 instanceof Enemy) {
            return enemyAndEnemy(co1, co2, world);
        }

        // Bullet and Bullet
        if (e1 instanceof Bullet && e2 instanceof Bullet) {
            return true; // Nothing should happen
        }

        Entity enemy = this.findEnemy(e1, e2);
        Entity bullet = this.findBullet(e1, e2);
        Entity player = this.findPlayer(e1, e2);
//        Entity obstacle = this.findObstacle(e1, e2);

        // Bullet and enemy collides
        if (enemy instanceof Enemy && bullet instanceof Enemy) {
            return enemyAndBullet(enemy, bullet, world);
        }

//        // Bullet and obstacle collides
//        if (obstacle instanceof CommonObstacle && bullet instanceof CommonBullet) {
//            return obstacleAndBullet(bullet, world);
//        }

        // Player and enemy collides
        if (player instanceof Player && enemy instanceof Enemy) {
            enemyAndPlayer(enemy, player);
            return enemyAndPlayer(enemy, player);
        }

//        // Enemy and obstacle collides
//        if (enemy instanceof CommonEnemy && obstacle instanceof CommonObstacle) {
//            // if the obstacle is a box, it takes damage
//            if (obstacle instanceof CommonBox) {
//                enemyAndBox(enemy, obstacle);
//            }
//            return enemyAndObstacle(co1, co2, world);
//        }

//        // Player and obstacle collides
//        if (player instanceof CommonPlayer && obstacle instanceof CommonObstacle) {
//            return playerAndObstacle(co1, co2, world);
//        }

        return false;
    }



    private boolean enemyAndBullet(Entity enemy, Entity bullet, World world) {
        LifePart enemyLifePart = enemy.getPart(LifePart.class);
        enemyLifePart.setIsHit(true);

        // Remove bullet on impact
        world.removeEntity(bullet);
        return true;
    }

    private boolean obstacleAndBullet(Entity bullet, World world) {
        // Remove bullet on impact
        world.removeEntity(bullet);
        return true;
    }

//    private boolean enemyAndPlayer(CollisionObject enemy, CollisionObject player, World world) {
//        moveEntities(enemy, player, world);
//        return true;
//    }

    private boolean enemyAndEnemy(Entity e1, Entity e2, World world) {

        Enemy commonEnemy1 = (Enemy) e1;
        Enemy commonEnemy2 = (Enemy) e2;

        commonEnemy1.handleCollide();
        commonEnemy2.handleCollide();

        return true;
    }

//    private boolean enemyAndObstacle(CollisionObject enemy, CollisionObject obstacle, World world) {
//        moveEntities(enemy, obstacle, world);
//        return true;
//    }

    private boolean enemyAndPlayer(Entity enemy, Entity player){
        Enemy commonEnemy = (Enemy) enemy;
        Player commonPlayer = (Player) player;
//        System.out.println("Enemy and player collided");

        commonEnemy.handleCollide();
        commonPlayer.handleCollide();
        return true;
    }

//    private boolean enemyAndBox(Entity enemy, Entity box) {
//        CommonBox commonBox = (CommonBox) box;
//        CommonEnemy commonEnemy = (CommonEnemy) enemy;
//
//        commonEnemy.attack(commonBox);
//        return true;
//    }

//    private boolean playerAndObstacle(CollisionObject player, CollisionObject obstacle, World world) {
//        moveEntities(player, obstacle, world);
//        return true;
//    }

    private Entity findEnemy(Entity e, Entity r) {
        if (e instanceof Enemy) {
            return e;
        } else if (r instanceof Enemy) {
            return r;
        }
        return null;
    }

    private Entity findBullet(Entity e, Entity r) {
        if (e instanceof Bullet) {
            return e;
        } else if (r instanceof Bullet) {
            return r;
        }
        return null;
    }

    private Entity findPlayer(Entity e, Entity r) {
        if (e instanceof Player) {
            return e;
        } else if (r instanceof Player) {
            return r;
        }
        return null;
    }

//    private Entity findObstacle(Entity e, Entity r) {
//        if (e instanceof CommonObstacle) {
//            return e;
//        } else if (r instanceof CommonObstacle) {
//            return r;
//        }
//        return null;
//    }



}
