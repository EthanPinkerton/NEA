package game;

import java.awt.*;

public class HealthBar {
    protected double health;

    public HealthBar(double health) {
        this.health = health;
    }

    public void draw(Graphics2D g2d) {
        g2d.setColor(Color.RED);
        g2d.fillRect(10, 10, 300, 50);
        g2d.setColor(Color.GREEN);
        g2d.fillRect(10, 10, (int) (300 * (health / 10.0)), 50);
        g2d.setColor(Color.BLACK);
        g2d.drawRect(10, 10, 300, 50);
    }

    public void damage(double damage) {
        health -= damage;
    }

    public double getHealth() {
        return health;
    }
}
