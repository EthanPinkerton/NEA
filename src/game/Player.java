package game;

import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;

public class Player{

    protected double x;
    protected double y;
    protected Image image;

    public Player(double x, double y, String file) {
        this.x = x;
        this.y = y;
        this.image = new ImageIcon(this.getClass().getResource(file)).getImage();
    }

    public void draw(Graphics2D g2d, ImageObserver IO,Grid grid){
        g2d.drawImage(image,(int) grid.getX(x),(int) grid.getY(y),grid.getScale(),grid.getScale(),IO);
    }

    public void keyPress(KeyListener kl){
        if(kl.isKeyW()){addY(-0.2);}
        if(kl.isKeyS()){addY(0.2);}
        if(kl.isKeyA()){addX(-0.2);}
        if(kl.isKeyD()){addX(0.2);}
    }

    public void addY(double y) {
        this.y += y;
    }

    public void addX(double x) {
        this.x += x;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
