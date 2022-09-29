package game;

import java.awt.*;
import java.awt.image.ImageObserver;

public class HealthBar {
    protected double health;

    public HealthBar(double health) {
        this.health = health;
    }

    public void draw(Graphics2D g2d){
        g2d.setColor(Color.RED);
        g2d.fillRect(10,10,(300)*((int) health/10),50);
        g2d.setColor(Color.BLACK);
        g2d.drawRect(10,10,300,50);
    }

    public void damage(double damage){
        health -= damage;
    }
}
