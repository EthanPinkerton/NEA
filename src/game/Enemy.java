package game;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.util.Random;

abstract class Enemy {

    protected double x, y;
    protected Image image;
    protected double health;
    protected final double scale = 0.4;
    protected Vector vector = new Vector(0, 0);
    private char direction;
    protected boolean following;
    private boolean onScreen;
    protected int hitTimer;
    protected double speed;

    public Enemy(double x, double y, double health, String file, double speed) {
        this.x = x;
        this.y = y;
        this.health = health;
        this.image = GetResource.getImage(file);
        this.speed = speed;
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
            drawAttack(g2d, IO, grid);
        } else {
            onScreen = false;
        }
    }

    abstract void drawAttack(Graphics2D g2d, ImageObserver IO, Grid grid);

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
                if (!maze.collision(x + speed * vector.iDirection(), y, x + speed * vector.iDirection() + scale, y + scale)) {
                    if (Math.abs(vector.getI()) < speed) {
                        x += 0.01 * vector.iDirection();
                    } else {
                        x += speed * vector.iDirection();
                    }
                }
                if (!maze.collision(x, y + speed * vector.jDirection(), x + scale, y + speed * vector.jDirection() + scale)) {
                    if (Math.abs(vector.getJ()) < speed) {
                        y += 0.01 * vector.jDirection();
                    } else {
                        y += speed * vector.jDirection();
                    }
                }
            } else {
                following = false;
            }
        } else if (onScreen) {
            switch (direction) {
                case 'w':
                    if (!maze.collision(x, y - (speed / 2), x + scale, y + scale - (speed / 2))) {
                        y -= (speed / 2);
                    } else {
                        direction = rChar();
                    }
                    break;
                case 's':
                    if (!maze.collision(x, y + (speed / 2), x + scale, y + scale + (speed / 2))) {
                        y += (speed / 2);
                    } else {
                        direction = rChar();
                    }
                    break;
                case 'a':
                    if (!maze.collision(x - (speed / 2), y, x + scale - (speed / 2), y + scale)) {
                        x -= (speed / 2);
                    } else {
                        direction = rChar();
                    }
                    break;
                case 'd':
                    if (!maze.collision(x + (speed / 2), y, x + scale + (speed / 2), y + scale)) {
                        x += (speed / 2);
                    } else {
                        direction = rChar();
                    }
                    break;
            }
        }
    }

     abstract boolean checkPlayer(Player player, Maze maze);

    abstract void attack(Player player);

    abstract char getType();

    public void update(Player player, Maze maze) {
        if (hitTimer != 0) {
            hitTimer -= 1;
        }
        x = Math.round(x * 100.0) / 100.0;
        y = Math.round(y * 100.0) / 100.0;
        checkProjectiles(player);
        if (checkPlayer(player, maze)) {
            return;
        }
        seePlayer(player.getX(), player.getY(), maze);
        move(player.getX(), player.getY(), maze);
    }
}
