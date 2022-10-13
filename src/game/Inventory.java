package game;

import java.awt.*;
import java.awt.image.ImageObserver;

public class Inventory {

    private byte keyPress;
    protected int bombs,teleport,speed,bullets;
    private final Image imageB,imageT,imageS,imageP;

    public Inventory(){
        bombs = 1;
        teleport = 1;
        speed = 1;
        bullets = 1;
        imageB = GetImage.get("bomb.jpg");
        imageT = GetImage.get("teleport.jpeg");
        imageS = GetImage.get("speed.jpg");
        imageP = GetImage.get("bullet.jpg");
        keyPress = 0;
    }

    public void keyPress(KeyListener kl, Maze maze, Player player){
        if(keyPress > 0) {
            keyPress -= 1;
        }
        if(kl.isKey1() && bombs > 0 && keyPress == 0){
            bombs -= 1;
            Bomb.use(player, maze);
            keyPress = 10;
        }
        if(kl.isKey2() && teleport > 0 && keyPress == 0){
            teleport -= 1;
            keyPress = 10;
        }
        if(kl.isKey3() && speed > 0 && keyPress == 0){
            speed -= 1;
            keyPress = 10;
        }
        if(kl.isKey4() && bullets > 0 && keyPress == 0){
            bullets -= 1;
            keyPress = 10;
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
