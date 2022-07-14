package game;

import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;

public class Player{
    protected int x;
    protected int y;
    protected Image image;

    public Player(int x, int y, String file) {
        this.x = x;
        this.y = y;
        this.image = new ImageIcon(this.getClass().getResource(file)).getImage();
    }

    public void draw(Graphics2D g2d, ImageObserver i){
        g2d.drawImage(image,x,y,i);
    }

    public void addY(int y) {
        this.y += y;
    }

    public void addX(int x) {
        this.x += x;
    }
}
