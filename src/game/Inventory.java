package game;

import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.util.ArrayList;

public class Inventory {

    protected int bombs,teleport,speed,bullets;
    private final Image imageB,imageT,imageS,imageP;

    public Inventory(){
        bombs = 1;
        teleport = 1;
        speed = 1;
        bullets = 1;
        imageB = new ImageIcon(this.getClass().getResource("bomb.jpg")).getImage();
        imageT = new ImageIcon(this.getClass().getResource("teleport.jpeg")).getImage();
        imageS = new ImageIcon(this.getClass().getResource("speed.jpg")).getImage();
        imageP = new ImageIcon(this.getClass().getResource("bullet.jpg")).getImage();
    }

    public void keyPress(KeyListener kl){
        if(kl.isKey1() && bombs > 0){
            bombs -= 1;
        }
        if(kl.isKey2() && teleport > 0){
            teleport -= 1;
        }
        if(kl.isKey3() && speed > 0){
            speed -= 1;
        }
        if(kl.isKey4() && bullets > 0){
            bullets -= 1;
        }
    }

    public void draw(Graphics2D g2d, ImageObserver IO){
        g2d.drawImage(imageB,10,80,80,80,IO);
        g2d.drawString(Integer.toString(bombs),10,160);
        g2d.drawImage(imageT,10,160,80,80,IO);
        g2d.drawString(Integer.toString(teleport),10,240);
        g2d.drawImage(imageS,10,240,80,80,IO);
        g2d.drawString(Integer.toString(speed),10,320);
        g2d.drawImage(imageP,10,320,80,80,IO);
        g2d.drawString(Integer.toString(bullets),10,400);
    }

    public void addItem(char type){
        switch (type){
            case 'b':
                bombs ++;
                break;
            case 't':
                teleport ++;
                break;
            case 's':
                speed ++;
                break;
            case 'p':
                bullets ++;
                break;
        }
    }
}
