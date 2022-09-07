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
        if((int) x == (int) pX){
            if((int) y < (int) pY) {
                for (int i = 0; i < 4; i++) {
                    if (maze.getTile((int) x,(int) y+i*2+1).equals("x") && (int) y+i*2+1 < (int) pY){
                        return;
                    }
                }
                y += 0.05;
            }else if((int) y > (int) pY){
                for (int i = 0; i < 4; i++) {
                    if (maze.getTile((int) x, (int) y-i*2-1).equals("x") && (int) y-i*2-1 > (int) pY){
                        return;
                    }
                }
                y -= 0.05;
            }
        }else if((int) y == (int) pY){
            if((int) x < (int) pX) {
                for (int i = 0; i < 4; i++) {
                    if (maze.getTile((int) x+i*2+1,(int) y).equals("x") && (int) x+i*2+1 < (int) pX){
                        return;
                    }
                }
                x += 0.05;
            }else if((int) x > (int) pX){
                for (int i = 0; i < 4; i++) {
                    if (maze.getTile((int) x-i*2-1, (int) y).equals("x") && (int) x-i*2-1 > (int) pX){
                        return;
                    }
                }
                x -= 0.05;
            }
        }
    }
}
