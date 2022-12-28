package game;

import java.awt.*;
import java.awt.image.ImageObserver;

public class Projectile {

    protected double x, y;
    protected char direction;
    protected Image bullet;
    private final double scale;

    public Projectile(double x, double y, String file, char direction, double scale) {
        this.x = Math.round(x * 10) / 10.0;
        this.y = Math.round(y * 10) / 10.0;
        this.bullet = GetResource.get(file);
        this.direction = direction;
        this.scale = scale;
    }

    public void draw(Graphics2D g2d, ImageObserver IO, Grid grid) {
        g2d.drawImage(bullet, grid.getX(x), grid.getY(y), (int) (grid.getScale() * scale), (int) (grid.getScale() * scale), IO);
    }

    public boolean intersect(Rectangle rec) {
        return rec.intersects((int) (x * 100), (int) (y * 100), (int) (scale * 100), (int) (scale * 100));
    }

    public boolean collision(Maze maze) {
        x = Math.round(x * 10) / 10.0;
        y = Math.round(y * 10) / 10.0;
        String[][] chunk;
        switch (direction) {
            case 'w':
                if(maze.collision(x, y - 0.1, x + scale, y + scale - 0.1)){
                    return true;
                }else {
                    y -= 0.1;
                    return false;
                }
            case 's':
                if(maze.collision(x, y + 0.1, x + scale, y + scale + 0.1)){
                    return true;
                }else {
                    y += 0.1;
                    return false;
                }
            case 'a':
                if(maze.collision(x - 0.1, y, x + scale - 0.1, y + scale)){
                    return true;
                }else {
                    x -= 0.1;
                    return false;
                }
            case 'd':
                if(maze.collision(x + 0.1, y, x + scale + 0.1, y + scale)){
                    return true;
                }else {
                    x += 0.1;
                    return false;
                }
        }
        return false;
    }
}
