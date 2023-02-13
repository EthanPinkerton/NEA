package game;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.util.Random;

public class Enemy {

    protected double x, y;
    protected Image image;
    protected double health;
    protected final double scale = 0.4;
    protected Vector vector = new Vector(0, 0);
    private char direction;
    protected boolean following;
    private boolean onScreen;
    private int hitTimer;

    public Enemy(double x, double y, double health, String file) {
        this.x = x;
        this.y = y;
        this.health = health;
        this.image = GetResource.getImage(file);
        following = false;
        direction = rChar();
        onScreen = false;
        hitTimer = 0;
    }

    private char rChar() {
        char[] chars = new char[]{'w', 's', 'a', 'd'};
        Random random = new Random();
        return chars[random.nextInt(4)];
    }

    public double getHealth() {
        return health;
    }

    public void draw(Graphics2D g2d, ImageObserver IO, Grid grid) {
        if (grid.onScreen(x, y)) {
            onScreen = true;
            g2d.drawImage(image, grid.getX(x), grid.getY(y), (int) (grid.getScale() * scale), (int) (grid.getScale() * scale), IO);
            g2d.setColor(Color.RED);
            g2d.fillRect(grid.getX(x), grid.getY(y), (int) (grid.getScale() * scale * (health / 10.0)), (int) (grid.getScale() * scale / 8.0));
            g2d.setColor(Color.BLACK);
            g2d.drawRect(grid.getX(x), grid.getY(y), (int) (grid.getScale() * scale), (int) (grid.getScale() * scale / 8.0));
        } else {
            onScreen = false;
        }
    }

    public boolean inView(Maze maze) {
        return !maze.inBounds((int) x, (int) y);
    }

    private void checkProjectiles(Player player) {
        Rectangle rec = new Rectangle((int) (x * 100), (int) (y * 100), (int) (scale * 100), (int) (scale * 100));

        if (Math.abs(x - player.getX()) < 3 || Math.abs(y - player.getY()) < 3) {
            int i = 0;
            while (i < player.projectilesSize()) {
                if (player.getProjectile(i).intersect(rec)) {
                    health -= 1;
                    player.removeProjectile(i);
                    i--;
                }
                i++;
            }
        }
    }

    protected boolean checkPlayer(Player player) {
        Rectangle rec = new Rectangle((int) (x * 100), (int) (y * 100), (int) (scale * 100), (int) (scale * 100));
        if (player.intersect(rec)) {
            attack(player);
            return true;
        }
        return false;
    }

    private void seePlayer(double pX, double pY, Maze maze) {
        if (Math.floor(x) == Math.floor(pX)) {
            if (!maze.collision(x, y, (int) (pY - y), true)) {
                following = true;
            }
        }
        if (Math.floor(y) == Math.floor(pY)) {
            if (!maze.collision(x, y, (int) (pX - x), false)) {
                following = true;
            }
        }
    }

    private void move(double pX, double pY, Maze maze) {
        if (following) {
            vector.setI(pX - x);
            vector.setJ(pY - y);
            if (vector.getMod() < 7) {
                if (!maze.collision(x + 0.04 * vector.iDirection(), y, x + 0.04 * vector.iDirection() + scale, y + scale)) {
                    if (Math.abs(vector.getI()) < 0.04) {
                        x += 0.01 * vector.iDirection();
                    } else {
                        x += 0.04 * vector.iDirection();
                    }
                }
                if (!maze.collision(x, y + 0.04 * vector.jDirection(), x + scale, y + 0.04 * vector.jDirection() + scale)) {
                    if (Math.abs(vector.getJ()) < 0.04) {
                        y += 0.01 * vector.jDirection();
                    } else {
                        y += 0.04 * vector.jDirection();
                    }
                }
            } else {
                following = false;
            }
        } else if (onScreen) {
            switch (direction) {
                case 'w':
                    if (!maze.collision(x, y - 0.02, x + scale, y + scale - 0.02)) {
                        y -= 0.02;
                    } else {
                        direction = rChar();
                    }
                    break;
                case 's':
                    if (!maze.collision(x, y + 0.02, x + scale, y + scale + 0.02)) {
                        y += 0.02;
                    } else {
                        direction = rChar();
                    }
                    break;
                case 'a':
                    if (!maze.collision(x - 0.02, y, x + scale - 0.02, y + scale)) {
                        x -= 0.02;
                    } else {
                        direction = rChar();
                    }
                    break;
                case 'd':
                    if (!maze.collision(x + 0.02, y, x + scale + 0.02, y + scale)) {
                        x += 0.02;
                    } else {
                        direction = rChar();
                    }
                    break;
            }
        }
    }

    protected void attack(Player player) {
        if (hitTimer == 0) {
            player.damage(0.7);
            hitTimer = 16;
        }
    }

    public void update(Player player, Maze maze) {
        if (hitTimer != 0) {
            hitTimer -= 1;
        }
        x = Math.round(x * 100.0) / 100.0;
        y = Math.round(y * 100.0) / 100.0;
        checkProjectiles(player);
        if (checkPlayer(player)) {
            return;
        }
        seePlayer(player.getX(), player.getY(), maze);
        move(player.getX(), player.getY(), maze);
    }
}
