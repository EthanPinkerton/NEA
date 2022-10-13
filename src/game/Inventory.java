package game;

import java.awt.*;
import java.awt.image.ImageObserver;

public class Inventory {

    private boolean bInUse,tInUse,sInUse,pInUse;
    protected int bombs,teleport,speed,bullets;
    private final Image imageB,imageT,imageS,imageP;

    public Inventory(){
        bInUse = false;
        tInUse = false;
        sInUse = false;
        pInUse = false;
        bombs = 1;
        teleport = 1;
        speed = 1;
        bullets = 1;
        imageB = GetImage.get("bomb.jpg");
        imageT = GetImage.get("teleport.jpeg");
        imageS = GetImage.get("speed.jpg");
        imageP = GetImage.get("bullet.jpg");
    }

    public void keyPress(KeyListener kl){
        if(kl.isKey1() && bombs > 0 && !bInUse){
            bombs -= 1;
            bInUse = true;
        }
        if(kl.isKey2() && teleport > 0 && !tInUse){
            teleport -= 1;
            tInUse = true;
        }
        if(kl.isKey3() && speed > 0 && !sInUse){
            speed -= 1;
            sInUse = true;
        }
        if(kl.isKey4() && bullets > 0 && !pInUse){
            bullets -= 1;
            pInUse = true;
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
