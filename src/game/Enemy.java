package game;

import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;

public class Enemy {

    protected double x,y;
    protected Image image;
    protected int health;
    private final double scale = 0.4;

    public Enemy(double x, double y, int health, String file) {
        this.x = x;
        this.y = y;
        this.health = health;
        this.image = new ImageIcon(this.getClass().getResource(file)).getImage();
    }

    public void draw(Graphics2D g2d, ImageObserver IO, Grid grid){
        g2d.drawImage(image,grid.getX(x-0.05),grid.getY(y-0.1),(int) (grid.getScale()*scale),(int) (grid.getScale()*scale),IO);
    }

    public double r1dp(double x){
        return Math.round(x*10)/10.0;
    }

    public void update(double pX, double pY, Maze maze){
        if(Math.floor(x) == Math.floor(pX-0.25) || Math.floor(x+0.25) == Math.floor(pX)){
            if(y < pY){
                for (int i = 0; i < 3; i++) {
                    if(maze.getTile((int)Math.floor(x),(int)Math.floor(y)+i*2+1).equals("x") && y+i*2+1 < pY){
                        return;
                    }
                }
                y += 0.05;
            }else if(y > pY){
                for (int i = 0; i < 3; i++) {
                    if(maze.getTile((int)Math.floor(x),(int)Math.floor(y)-i*2-1).equals("x") && y-i*2-1 > pY){
                        return;
                    }
                }
                y -= 0.05;
            }
        }if(Math.floor(y) == Math.floor(pY-0.25) || Math.floor(y+0.25) == Math.floor(pY)){
            if(x < pX){
                for (int i = 0; i < 3; i++) {
                    if(maze.getTile((int)Math.floor(x)+i*2+1,(int)Math.floor(y)).equals("x") && x+i*2+1 < pX){
                        return;
                    }
                }
                x += 0.05;
            }else if(x > pX){
                for (int i = 0; i < 3; i++) {
                    if(maze.getTile((int)Math.floor(x)-i*2-1,(int)Math.floor(y)).equals("x") && x-i*2-1 < pX){
                        return;
                    }
                }
                x -= 0.05;
            }
        }
    }

    public void update(double pX, double pY, Maze maze, int l){
        boolean playerVisible = false;
        if(Math.round(x) == Math.round(pX)){
            System.out.println(x + "," + y + " " + pX + "," + pY);
            if(Math.round(y) < Math.round(pY)) {
                playerVisible = true;
                for (int i = 0; i < 3; i++) {
                    if (maze.getTile((int)Math.round(x),(int)Math.round(y)+i*2+1).equals("x") && Math.round(y)+i*2+1 < Math.round(pY)){
                        playerVisible = false;
                    }
                }
                if(playerVisible) {
                    y += 0.05;
                }
            }else if(Math.round(y) > Math.round(pY)){
                for (int i = 0; i < 3; i++) {
                    playerVisible = true;
                    if (maze.getTile((int)Math.round(x), (int)Math.round(y)-i*2-1).equals("x") && Math.round(y)-i*2-1 > Math.round(pY)){
                        playerVisible = false;
                    }
                }
                if(playerVisible) {
                    y -= 0.05;
                }
            }
        }else if(Math.round(y) == Math.round(pY)){
            if((int) x < (int) pX) {
                for (int i = 0; i < 3; i++) {
                    playerVisible = true;
                    if (maze.getTile((int)Math.round(x)+i*2+1,(int)Math.round(y)).equals("x") && Math.round(x)+i*2+1 < Math.round(pX)){
                        playerVisible = false;
                    }
                }
                if(playerVisible) {
                    x += 0.05;
                }
            }else if(Math.round(x) > Math.round(pX)){
                for (int i = 0; i < 3; i++) {
                    playerVisible = true;
                    if (maze.getTile((int)Math.floor(x)-i*2-1, (int)Math.floor(y)).equals("x") && Math.round(x)-i*2-1 > Math.round(pX)){
                        playerVisible = false;
                    }
                }
                if(playerVisible) {
                    x -= 0.05;
                }
            }
        }
    }
}
