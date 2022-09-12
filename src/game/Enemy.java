package game;

import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;

public class Enemy {

    protected double x,y;
    protected Image image;
    protected int health;

    public Enemy(double x, double y, int health, String file) {
        this.x = x;
        this.y = y;
        this.health = health;
        this.image = new ImageIcon(this.getClass().getResource(file)).getImage();
    }

    public void draw(Graphics2D g2d, ImageObserver IO, Grid grid){
        g2d.drawImage(image,grid.getX(x),grid.getY(y),grid.getScale()/2,grid.getScale()/2,IO);
    }

    public void update(double pX, double pY, Maze maze){
        if(Math.round(x) == Math.round(pX)){
            if(Math.round(y) < Math.round(pY)) {
                for (int i = 0; i < 3; i++) {
                    if (maze.getTile((int)Math.round(x),(int)Math.round(y+i*2+1)).equals("x") && Math.round(y+i*2+1) < Math.round(pY)){
                        return;
                    }
                }
                y += 0.05;
            }else if(Math.round(y) > Math.round(pY)){
                for (int i = 0; i < 3; i++) {
                    if (maze.getTile((int)Math.round(x), (int)Math.round(y-i*2-1)).equals("x") && Math.round(y-i*2-1) > Math.round(pY)){
                        return;
                    }
                }
                y -= 0.05;
            }
        }else if(Math.round(y) == Math.round(pY)){
            if((int) x < (int) pX) {
                for (int i = 0; i < 3; i++) {
                    if (maze.getTile((int)Math.round(x+i*2+1),(int)Math.round(y)).equals("x") && Math.round(x+i*2+1) < Math.round(pX)){
                        return;
                    }
                }
                x += 0.05;
            }else if(Math.round(x) > Math.round(pX)){
                for (int i = 0; i < 3; i++) {
                    if (maze.getTile((int)Math.round(x-i*2-1), (int)Math.round(y)).equals("x") && Math.round(x-i*2-1) > Math.round(pX)){
                        return;
                    }
                }
                x -= 0.05;
            }
        }
    }
}
