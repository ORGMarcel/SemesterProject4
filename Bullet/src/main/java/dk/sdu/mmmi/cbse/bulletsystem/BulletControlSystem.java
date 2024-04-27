package dk.sdu.mmmi.cbse.bulletsystem;

import dk.sdu.mmmi.cbse.commonbullet.BulletSPI;
import dk.sdu.mmmi.cbse.commonbullet.Bullet;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

public class BulletControlSystem implements IEntityProcessingService, BulletSPI {

    @Override
    public void process(GameData gameData, World world) {

        for (Entity bullet : world.getEntities(Bullet.class)) {
            double changeX = Math.cos(Math.toRadians(bullet.getRotation()));
            double changeY = Math.sin(Math.toRadians(bullet.getRotation()));
            bullet.setX(bullet.getX() + changeX * 3);
            bullet.setY(bullet.getY() + changeY * 3);


            if (bullet.getX() < 0) {
                world.removeEntity(bullet);
            }

            if (bullet.getX() > gameData.getDisplayWidth()) {
                world.removeEntity(bullet);
            }

            if (bullet.getY() < 0) {
                world.removeEntity(bullet);
            }

            if (bullet.getY() > gameData.getDisplayHeight()) {
                world.removeEntity(bullet);
            }


        }


    }

    @Override
    public Entity createBullet(Entity shooter, GameData gameData) {
        Entity bullet = new Bullet();
        bullet.setPolygonCoordinates(0, 3, 1, 1, 3, 0, 1, -1, 0, -3, -1, -1, -3, 0, -1, 1, 0);
        bullet.setX(shooter.getX());
        bullet.setY(shooter.getY());
        bullet.setRotation(shooter.getRotation());
        double changeX = Math.cos(Math.toRadians(bullet.getRotation()));
        double changeY = Math.sin(Math.toRadians(bullet.getRotation()));
        //Any lower than 25, and the collision system won't work, and the
        // enemies will kill the self
        bullet.setX(shooter.getX() + changeX * 25);
        bullet.setY(shooter.getY() + changeY * 25);
        return bullet;
    }
}
