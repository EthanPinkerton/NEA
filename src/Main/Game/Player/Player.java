package Main.Game.Player;

import Main.Game.Maze.Maze;
import Main.Game.GetResource;
import Main.Game.GUI.Grid;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.util.ArrayList;

public class Player {

    protected double x, y;
    private char direction;
    private final Image image;
    private final ArrayList<Projectile> projectiles;
    private int keyPress;
    private final double scale;
    private final double xScale;
    private final double yScale;
    private final HealthBar healthBar;
    private final Inventory inventory;
    protected double speed;
    protected int pDelay;
    protected int score;
    private int damageImage;

    public Player(double x, double y, double health, int score) {
        this.x = x;
        this.y = y;
        this.healthBar = new HealthBar(health);
        this.direction = 'd';
        this.keyPress = 0;
        this.image = GetResource.getImage("player.png");
        projectiles = new ArrayList<>();
        inventory = new Inventory();
        scale = 0.57;
        xScale = 0.37;
        yScale = 0.5;
        speed = 0.05;
        pDelay = 10;
        this.score = score;
        damageImage = 0;
    }

    public void draw(Graphics2D g2d, ImageObserver IO, Grid grid) {
        if (damageImage != 0) {
            g2d.drawImage(GetResource.getImage("playerHit.png"), grid.getX(x - 0.1), grid.getY(y), (int) (grid.getScale() * scale), (int) (grid.getScale() * scale), IO);
            damageImage -= 1;
        } else {
            g2d.drawImage(image, grid.getX(x - 0.1), grid.getY(y), (int) (grid.getScale() * scale), (int) (grid.getScale() * scale), IO);
        }
        for (Projectile projectile : projectiles) {
            projectile.draw(g2d, IO, grid);
        }
        healthBar.draw(g2d);
        inventory.draw(g2d, IO);
        g2d.scale(2, 2);
        g2d.setColor(Color.BLUE);
        g2d.drawString(Integer.toString(score), 5, 40);
        g2d.scale(0.5, 0.5);
    }

    public void update(KeyListener kl, Maze maze) {
        keyPress(kl, maze);
        if (keyPress > 0) {
            keyPress -= 1;
        }
        int i = 0;
        while (i < projectiles.size()) {
            try {
                if (projectiles.get(i).collision(maze)) {
                    projectiles.remove(i);
                    i--;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                projectiles.remove(i);
                i--;
            }
            i++;
        }
        inventory.update(kl, maze, this);
    }

    public void keyPress(KeyListener kl, Maze maze) {
        if (kl.isKeyW() && !collision(maze, 'w')) {
            addY(-speed);
            if (changeDirection(kl)) {
                direction = 'w';
            }
        }
        if (kl.isKeyS() && !collision(maze, 's')) {
            addY(speed);
            if (changeDirection(kl)) {
                direction = 's';
            }
        }
        if (kl.isKeyA() && !collision(maze, 'a')) {
            addX(-speed);
            if (changeDirection(kl)) {
                direction = 'a';
            }
        }
        if (kl.isKeyD() && !collision(maze, 'd')) {
            addX(speed);
            if (changeDirection(kl)) {
                direction = 'd';
            }
        }
        if (kl.isKeyUp() && keyPress == 0) {
            projectiles.add(new Projectile(x + xScale / 4, y + yScale / 4, "bullet.png", 'w', scale / 2));
            keyPress = pDelay;
        }
        if (kl.isKeyDown() && keyPress == 0) {
            projectiles.add(new Projectile(x + xScale / 4, y + yScale / 4, "bullet.png", 's', scale / 2));
            keyPress = pDelay;
        }
        if (kl.isKeyLeft() && keyPress == 0) {
            projectiles.add(new Projectile(x + xScale / 4, y + yScale / 4, "bullet.png", 'a', scale / 2));
            keyPress = pDelay;
        }
        if (kl.isKeyRight() && keyPress == 0) {
            projectiles.add(new Projectile(x + xScale / 4, y + yScale / 4, "bullet.png", 'd', scale / 2));
            keyPress = pDelay;
        }
        if (kl.isKeySpace() && keyPress == 0) {
            projectiles.add(new Projectile(x + xScale / 4, y + yScale / 4, "bullet.png", direction, scale / 2));
            keyPress = pDelay;
        }
    }

    private boolean changeDirection(KeyListener kl) {
        if (direction == 'w' && kl.isKeyW()) {
            return false;
        } else if (direction == 's' && kl.isKeyS()) {
            return false;
        } else if (direction == 'a' && kl.isKeyA()) {
            return false;
        } else if (direction == 'd' && kl.isKeyD()) {
            return false;
        }
        return true;
    }

    private boolean collision(Maze maze, char key) {
        x = Math.round(x * 100) / 100.0;
        y = Math.round(y * 100) / 100.0;
        switch (key) {
            case 'w':
                return maze.collision(x, y - speed, x + xScale, y + yScale - speed);
            case 's':
                return maze.collision(x, y + speed, x + xScale, y + yScale + speed);
            case 'a':
                return maze.collision(x - speed, y, x + xScale - speed, y + yScale);
            case 'd':
                return maze.collision(x + speed, y, x + xScale + speed, y + yScale);
        }
        return false;
    }

    public void addItem(char type) {
        inventory.addItem(type);
        score += 5;
    }

    public void addY(double y) {
        this.y += y;
    }

    public void addX(double x) {
        this.x += x;
    }

    public double getHealth() {
        return healthBar.getHealth();
    }

    public char getDirection() {
        return direction;
    }

    public boolean intersect(Rectangle rec) {
        return rec.intersects((int) (x * 100), (int) (y * 100), (int) (xScale * 100), (int) (yScale * 100));
    }

    public void damage(double damage) {
        healthBar.damage(damage);
        damageImage = 3;
    }

    public void removeProjectile(int i) {
        projectiles.remove(i);
    }

    public int projectilesSize() {
        return projectiles.size();
    }

    public Projectile getProjectile(int i) {
        return projectiles.get(i);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public void setPDelay(int newDelay) {
        pDelay = newDelay;
    }

    public void addScore(int score) {
        this.score += score;
    }

    public int getScore() {
        return score;
    }
}
