package game;

import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;

public class Projectile {

    protected double x,y;
    protected char direction;
    protected Image bullet;

    public Projectile(double x, double y, String file, char direction) {
        this.x = x;
        this.y = y;
        this.bullet = new ImageIcon(this.getClass().getResource(file)).getImage();
        this.direction = direction;
    }

    public void draw(Graphics2D g2d, ImageObserver IO, Grid grid){
        switch(direction){
            case 'w':
                y -= 0.2;
                break;
            case 's':
                y += 0.2;
                break;
            case 'a':
                x -= 0.2;
                break;
            case 'd':
                x += 0.2;
                break;
        }
        g2d.drawImage(bullet,grid.getX(x),grid.getY(y),grid.getScale()/4,grid.getScale()/4,IO);
    }
}
