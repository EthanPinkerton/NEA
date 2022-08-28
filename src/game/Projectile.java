package game;

import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;

public class Projectile {

    protected double x,y;
    protected char direction;
    protected Image bullet;

    public Projectile(double x, double y, String file, char direction) {
        this.x = x;
        this.y = y;
        this.bullet = new ImageIcon(this.getClass().getResource(file)).getImage();
        this.direction = direction;
    }

    public void draw(Graphics2D g2d, ImageObserver IO, Grid grid){
        switch(direction){
            case 'w':
                y -= 0.1;
                break;
            case 's':
                y += 0.1;
                break;
            case 'a':
                x -= 0.1;
                break;
            case 'd':
                x += 0.1;
                break;
        }
        g2d.drawImage(bullet,grid.getX(x),grid.getY(y),grid.getScale()/2,grid.getScale()/2,IO);
    }

    public boolean collision(Maze maze){
        x = Math.round(x*10)/10.0;
        y = Math.round(y*10)/10.0;
        String[][] chunk;
        switch (direction){
            case 'w':
                chunk = maze.getChunk((int) Math.floor(x/10),(int) Math.floor((y-1)/10));
                if(y%1 == 0 && chunk[Math.floorMod((int) Math.floor(y-1),10)][Math.floorMod((int) Math.floor(x),10)].equals("x")){
                    return true;
                }else if(y%1 == 0 && chunk[Math.floorMod((int) Math.floor(y-1),10)][Math.floorMod((int) Math.floor(x+0.4),10)].equals("x")){
                    return true;
                }else{return false;}
            case 's':
                chunk = maze.getChunk((int) Math.floor(x/10),(int) Math.floor((y+1)/10));
                if(Math.abs(y%1) == 0.5 && chunk[Math.floorMod((int) Math.floor(y+1),10)][Math.floorMod((int) Math.floor(x),10)].equals("x")){
                    return true;
                }else if(Math.abs(y%1) == 0.5 && chunk[Math.floorMod((int) Math.floor(y+1),10)][Math.floorMod((int) Math.floor(x+0.4),10)].equals("x")){
                    return true;
                }else{return false;}
            case 'a':
                chunk = maze.getChunk((int) Math.floor((x-1)/10),(int) Math.floor(y/10));
                if(x%1 == 0 && chunk[Math.floorMod((int) Math.floor(y),10)][Math.floorMod((int) Math.floor(x-1),10)].equals("x")){
                    return true;
                }else if(x%1 == 0 && chunk[Math.floorMod((int) Math.floor(y+0.4),10)][Math.floorMod((int) Math.floor(x-1),10)].equals("x")){
                    return true;
                }else{return false;}
            case 'd':
                chunk = maze.getChunk((int) Math.floor((x+1)/10),(int) Math.floor(y/10));
                if(Math.abs(x%1) == 0.5 && chunk[Math.floorMod((int) Math.floor(y),10)][Math.floorMod((int) Math.floor(x+1),10)].equals("x")){
                    return true;
                }else if(Math.abs(x%1) == 0.5 && chunk[Math.floorMod((int) Math.floor(y+0.4),10)][Math.floorMod((int) Math.floor(x+1),10)].equals("x")){
                    return true;
                }else{return false;}
        }
        return false;
    }
}
