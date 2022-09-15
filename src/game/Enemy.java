package game;

import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;

public class Enemy {

    protected double x,y;
    protected Image image;
    protected int health;
    private final double scale = 0.4;
    protected boolean playerVisible;

    public Enemy(double x, double y, int health, String file) {
        this.x = x;
        this.y = y;
        this.health = health;
        this.image = new ImageIcon(this.getClass().getResource(file)).getImage();
        playerVisible = false;
    }

    public void draw(Graphics2D g2d, ImageObserver IO, Grid grid){
        g2d.drawImage(image,grid.getX(x-0.05),grid.getY(y-0.1),(int) (grid.getScale()*scale),(int) (grid.getScale()*scale),IO);
    }

    public double r1dp(double x){
        return Math.round(x*10)/10.0;
    }

    public void update(double pX, double pY, Maze maze){
        if(Math.floor(x) == Math.floor(pX)){
            if(y < pY){
                for (int i = 0; i < 3; i++) {
                    if(maze.getTile(x,y+i*2+1).equals("x") && y+i*2+1 < pY){
                        return;
                    }
                }
                y += 0.04;
            }else if(y > pY){
                for (int i = 0; i < 3; i++) {
                    if(maze.getTile(x,y-i*2-1).equals("x") && y-i*2-1 > pY){
                        return;
                    }
                }
                y -= 0.04;
            }
        }if(Math.floor(y) == Math.floor(pY)){
            if(x < pX){
                for (int i = 0; i < 3; i++) {
                    if(maze.getTile(x+i*2+1,y).equals("x") && x+i*2+1 < pX){
                        return;
                    }
                }
                x += 0.04;
            }else if(x > pX){
                for (int i = 0; i < 3; i++) {
                    if(maze.getTile(x-i*2-1,y).equals("x") && x-i*2-1 > pX){
                        return;
                    }
                }
                x -= 0.04;
            }
        }
    }
}
