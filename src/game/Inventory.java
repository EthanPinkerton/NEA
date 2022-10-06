package game;

import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.util.ArrayList;

public class Inventory {

    protected int bombs;
    private Image image;

    public Inventory(){
        bombs = 1;
        image = new ImageIcon(this.getClass().getResource("item.jpg")).getImage();
    }

    public void keyPress(KeyListener kl){
        if(kl.isKey1() && bombs > 0){
            bombs -= 1;
        }
    }

    public void draw(Graphics2D g2d, ImageObserver IO){
        g2d.drawImage(image,10,80,80,80,IO);
        g2d.drawString(Integer.toString(bombs),10,160);
    }

    public void addItem(String type){

    }
}
