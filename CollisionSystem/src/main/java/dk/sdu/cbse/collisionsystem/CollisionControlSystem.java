package dk.sdu.cbse.collisionsystem;

import dk.sdu.cbse.commonbullet.Bullet;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.cbse.enemiessystem.Enemy;
import dk.sdu.mmmi.playersystem.Player;

import java.util.List;

import static java.lang.Math.sqrt;

public class CollisionControlSystem implements IEntityProcessingService {

    @Override
    public void process(GameData gameData, World world) {

        // Get current kills from the world
        int killsOverall = world.getKillsOverall();
        int kills = world.getKills();

        // for loop for collision between bullet and Enemy
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

        // Update the kills in the world
        world.setKillsOverall(killsOverall);
        world.setKills(kills);

        // Check for rounds and update
        if (world.getKills() == world.getRound() * 2) {
            world.setRound(world.getRound() + 1);
            world.setKills(0);
            world.setRoundRunning(false);
        }

        // for loop for collision between bullet and Player
        for (Entity entityPlayer : world.getEntities(Player.class)) {
            for (Entity entityBullet : world.getEntities(Bullet.class)) {
                if (isCollided(entityPlayer, entityBullet)) {
                    entityPlayer.setHealthPoints(entityPlayer.getHealthPoints() - 1);
                    if (entityPlayer.getHealthPoints() < 1) {
                        world.removeEntity(entityPlayer);
                    }
                    world.removeEntity(entityBullet);
                }
            }
        }


        // Check for collision between player and wall
        Entity player = world.getEntities(Player.class).getFirst(); // Assuming only one player entity
        for (Entity entity : world.getEntities()) {

            String entityType = entity.getType();
            if (entityType != null && entityType.equals("Wall")) {

                if (checkBoundingBoxCollision(player, entity)) {
                    System.out.println(entityType);
                    // Handle collision between player and wall
                    double wallTop = entity.getY();
                    double playerHeight = player.getHeight(); // Assuming you have a method to get player's height
                    // Set player's position just above the wall's top edge
                    player.setY(wallTop - playerHeight - 1);
                    System.out.println(wallTop);
                    System.out.println(playerHeight);// Adjust 1 to avoid being stuck in the wall
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

    // Method to check collision with another square
    // Method to check collision with another square
//    boolean checkCollision(Entity square1, Entity square2) {
//        // Get the positions and dimensions of the squares
//        double x1 = square1.getX();
//        double y1 = square1.getY();
//        double width1 = square1.getWidth();
//        double height1 = square1.getHeight();
//
//        double x2 = square2.getX();
//        double y2 = square2.getY();
//        double width2 = square2.getWidth();
//        double height2 = square2.getHeight();
//
//        // Calculate the minimum and maximum x and y coordinates for both squares
//        double minX1 = x1;
//        double maxX1 = x1 + width1;
//        double minY1 = y1;
//        double maxY1 = y1 + height1;
//
//        double minX2 = x2;
//        double maxX2 = x2 + width2;
//        double minY2 = y2;
//        double maxY2 = y2 + height2;
//
//        // Check for collision
//        boolean collisionX = maxX1 > minX2 && minX1 < maxX2;
//        boolean collisionY = maxY1 > minY2 && minY1 < maxY2;
//
//        return collisionX && collisionY; // Collision occurs if both X and Y axes overlap
//    }



    boolean checkBoundingBoxCollision(Entity entity1, Entity entity2) {
        // Get the bounding box coordinates of each entity
        double x1 = entity1.getX();
        double y1 = entity1.getY();
        double width1 = entity1.getWidth();
        double height1 = entity1.getHeight();
        double x2 = entity2.getX();
        double y2 = entity2.getY();
        double width2 = entity2.getWidth();
        double height2 = entity2.getHeight();

        // Calculate the minimum and maximum X and Y coordinates for each bounding box
        double minX1 = x1;
        double maxX1 = x1 + width1;
        double minY1 = y1;
        double maxY1 = y1 + height1;
        double minX2 = x2;
        double maxX2 = x2 + width2;
        double minY2 = y2;
        double maxY2 = y2 + height2;

        // Check for overlap along the X and Y axes
        boolean overlapX = maxX1 > minX2 && minX1 < maxX2;
        boolean overlapY = maxY1 > minY2 && minY1 < maxY2;

        // Collision occurs if there is overlap along both axes
        return overlapX && overlapY;
    }


}
