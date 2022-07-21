package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.ImageObserver;

public class Player{

    private final int scale = 32;
    protected int x;
    protected int y;
    protected Image image;

    public Player(int x, int y, String file) {
        this.x = x;
        this.y = y;
        this.image = new ImageIcon(this.getClass().getResource(file)).getImage();
    }

    public void draw(Graphics2D g2d, ImageObserver IO){
        g2d.drawImage(image,x,y,scale,scale,IO);
    }

    public void keyPress(KeyListener kl){
        if(kl.isKeyW()){addY(-1);}
        if(kl.isKeyS()){addY(1);}
        if(kl.isKeyA()){addX(-1);}
        if(kl.isKeyD()){addX(1);}
    }

    public void addY(int y) {
        this.y += y;
    }

    public void addX(int x) {
        this.x += x;
    }

    public void collide(){

    }
}
