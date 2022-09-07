package game;

import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;

public class Projectile {

    protected double x,y;
    protected char direction;
    protected Image bullet;
    private double scale;

    public Projectile(double x, double y, String file, char direction, double scale) {
        this.x = x;
        this.y = y;
        this.bullet = new ImageIcon(this.getClass().getResource(file)).getImage();
        this.direction = direction;
        this.scale = scale;
    }

    public void draw(Graphics2D g2d, ImageObserver IO, Grid grid){
        g2d.drawImage(bullet,grid.getX(x),grid.getY(y),(int) (grid.getScale()*scale),(int) (grid.getScale()*scale),IO);
    }

    public boolean collision(Maze maze){
        x = Math.round(x*100)/100.0;
        y = Math.round(y*100)/100.0;
        String[][] chunk;
        switch (direction){
            case 'w':
                chunk = maze.getChunk((int) Math.floor(x/10),(int) Math.floor((y-1)/10));
                if(y%1 == 0 && chunk[Math.floorMod((int) Math.floor(y-1),10)][Math.floorMod((int) Math.floor(x),10)].equals("x")){
                    return true;
                }else if(y%1 == 0 && chunk[Math.floorMod((int) Math.floor(y-1),10)][Math.floorMod((int) Math.floor(x+scale-0.05),10)].equals("x")){
                    return true;
                }else{y -= 0.1; return false;}
            case 's':
                chunk = maze.getChunk((int) Math.floor(x/10),(int) Math.floor((y+1)/10));
                if(Math.abs(y%1) == Math.round(1-scale*10)/10.0 && chunk[Math.floorMod((int) Math.floor(y+1),10)][Math.floorMod((int) Math.floor(x),10)].equals("x")){
                    return true;
                }else if(Math.abs(y%1) == Math.round(1-scale*10)/10.0 && chunk[Math.floorMod((int) Math.floor(y+1),10)][Math.floorMod((int) Math.floor(x+scale-0.05),10)].equals("x")){
                    return true;
                }else{y += 0.1; return false;}
            case 'a':
                chunk = maze.getChunk((int) Math.floor((x-1)/10),(int) Math.floor(y/10));
                if(x%1 == 0 && chunk[Math.floorMod((int) Math.floor(y),10)][Math.floorMod((int) Math.floor(x-1),10)].equals("x")){
                    return true;
                }else if(x%1 == 0 && chunk[Math.floorMod((int) Math.floor(y+scale-0.05),10)][Math.floorMod((int) Math.floor(x-1),10)].equals("x")){
                    return true;
                }else{x -= 0.1; return false;}
            case 'd':
                chunk = maze.getChunk((int) Math.floor((x+1)/10),(int) Math.floor(y/10));
                if(Math.abs(x%1) == Math.round(1-scale*10)/10.0 && chunk[Math.floorMod((int) Math.floor(y),10)][Math.floorMod((int) Math.floor(x+1),10)].equals("x")){
                    return true;
                }else if(Math.abs(x%1) == Math.round(1-scale*10)/10.0 && chunk[Math.floorMod((int) Math.floor(y+scale-0.05),10)][Math.floorMod((int) Math.floor(x+1),10)].equals("x")){
                    return true;
                }else{x += 0.1; return false;}
        }
        return false;
    }
}
