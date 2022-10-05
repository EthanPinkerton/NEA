package game;

import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;

public class Item {
    protected double x,y;
    protected Image image;
    protected double scale;
    protected boolean collected;

    public Item(double x, double y, String file) {
        this.x = x;
        this.y = y;
        this.scale = 0.25;
        this.image = new ImageIcon(this.getClass().getResource(file)).getImage();
        collected = false;
    }

    public void draw(Graphics2D g2d, ImageObserver IO, Grid grid){
        if(grid.onScreen(x,y)){
            g2d.drawImage(image, grid.getX(x), grid.getY(y), (int) (grid.getScale() * scale), (int) (grid.getScale() * scale), IO);
        }
    }

    public void update(Player player){
        Rectangle rec = new Rectangle((int) (x*100),(int) (y*100),(int) (scale*100),(int) (scale*100));
        if(player.intersect(rec)){
            collected = true;
        }
    }

    public boolean isCollected() {
        return collected;
    }
}
