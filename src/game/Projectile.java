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
                chunk = maze.getChunkArr((int) Math.floor(x / 10), (int) Math.floor((y - 1) / 10));
                if (y % 1 == 0 && chunk[Math.floorMod((int) Math.floor(y - 1), 10)][Math.floorMod((int) Math.floor(x), 10)].equals("x")) {
                    return true;
                } else if (y % 1 == 0 && chunk[Math.floorMod((int) Math.floor(y - 1), 10)][Math.floorMod((int) Math.floor(x + scale - 0.05), 10)].equals("x")) {
                    return true;
                } else {
                    y -= 0.1;
                    return false;
                }
            case 's':
                chunk = maze.getChunkArr((int) Math.floor(x / 10), (int) Math.floor((y + 1) / 10));
                if (Math.round(Math.abs(y % 1) * 10) / 10.0 == Math.round((1 - scale) * 10) / 10.0 && chunk[Math.floorMod((int) Math.floor(y + 1), 10)][Math.floorMod((int) Math.floor(x), 10)].equals("x")) {
                    return true;
                } else if (Math.round(Math.abs(y % 1) * 10) / 10.0 == Math.round((1 - scale) * 10) / 10.0 && chunk[Math.floorMod((int) Math.floor(y + 1), 10)][Math.floorMod((int) Math.floor(x + scale - 0.05), 10)].equals("x")) {
                    return true;
                } else {
                    y += 0.1;
                    return false;
                }
            case 'a':
                chunk = maze.getChunkArr((int) Math.floor((x - 1) / 10), (int) Math.floor(y / 10));
                if (x % 1 == 0 && chunk[Math.floorMod((int) Math.floor(y), 10)][Math.floorMod((int) Math.floor(x - 1), 10)].equals("x")) {
                    return true;
                } else if (x % 1 == 0 && chunk[Math.floorMod((int) Math.floor(y + scale - 0.05), 10)][Math.floorMod((int) Math.floor(x - 1), 10)].equals("x")) {
                    return true;
                } else {
                    x -= 0.1;
                    return false;
                }
            case 'd':
                chunk = maze.getChunkArr((int) Math.floor((x + 1) / 10), (int) Math.floor(y / 10));
                if (Math.round(Math.abs(x % 1) * 10) / 10.0 == Math.round((1 - scale) * 10) / 10.0 && chunk[Math.floorMod((int) Math.floor(y), 10)][Math.floorMod((int) Math.floor(x + 1), 10)].equals("x")) {
                    return true;
                } else if (Math.round(Math.abs(x % 1) * 10) / 10.0 == Math.round((1 - scale) * 10) / 10.0 && chunk[Math.floorMod((int) Math.floor(y + scale - 0.05), 10)][Math.floorMod((int) Math.floor(x + 1), 10)].equals("x")) {
                    return true;
                } else {
                    x += 0.1;
                    return false;
                }
        }
        return false;
    }
}
