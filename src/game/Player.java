package game;

import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.util.ArrayList;

public class Player{

    protected double x,y;
    private char direction;
    private final Image image;
    private ArrayList<Projectile> projectiles = new ArrayList<>();
    private int keyPress;
    private final double scale = 0.25;

    public Player(double x, double y, String file) {
        this.x = x;
        this.y = y;
        this.direction = 'd';
        this.keyPress = 0;
        this.image = new ImageIcon(this.getClass().getResource(file)).getImage();
    }

    public void draw(Graphics2D g2d, ImageObserver IO,Grid grid){
        g2d.drawImage(image,grid.getX(x),grid.getY(y), (int) (grid.getScale()*scale),(int) (grid.getScale()*scale),IO);
        for (Projectile projectile : projectiles) {
            projectile.draw(g2d, IO, grid);
        }
    }

    public void update(KeyListener kl, Maze maze){
        keyPress(kl, maze);
        if(keyPress > 0){keyPress -= 1;}
        int i = 0;
        while(i < projectiles.size()){
            if(projectiles.get(i).collision(maze)){
                projectiles.remove(i);
                i--;
            }
            i++;
        }
    }

    public void keyPress(KeyListener kl, Maze maze){
//        if(kl.isKeyW()){addY(-0.1);}
//        if(kl.isKeyS()){addY(0.1);}
//        if(kl.isKeyA()){addX(-0.1);}
//        if(kl.isKeyD()){addX(0.1);}
        if(kl.isKeyW() && !collision(maze,'w')){addY(-0.05); direction = 'w';}
        if(kl.isKeyS() && !collision(maze,'s')){addY(0.05); direction = 's';}
        if(kl.isKeyA() && !collision(maze,'a')){addX(-0.05); direction = 'a';}
        if(kl.isKeyD() && !collision(maze,'d')){addX(0.05); direction = 'd';}
        if(kl.isKeySpace() && keyPress == 0){projectiles.add(new Projectile(x,y,"bullet.png",direction,scale)); keyPress = 20;}
    }

    private boolean collision(Maze maze,char key){
        x = Math.round(x*100)/100.0;
        y = Math.round(y*100)/100.0;
        String[][] chunk;
        switch (key){
            case 'w':
                chunk = maze.getChunk((int) Math.floor(x/10),(int) Math.floor((y-1)/10));
                if(y%1 == 0 && chunk[Math.floorMod((int) Math.floor(y-1),10)][Math.floorMod((int) Math.floor(x),10)].equals("x")){
                    return true;
                }else if(y%1 == 0 && chunk[Math.floorMod((int) Math.floor(y-1),10)][Math.floorMod((int) Math.floor(x+scale-0.01),10)].equals("x")){
                    return true;
                }else{return false;}
            case 's':
                chunk = maze.getChunk((int) Math.floor(x/10),(int) Math.floor((y+1)/10));
                if((y%1 == 1-scale || y%1 == -scale) && chunk[Math.floorMod((int) Math.floor(y+1),10)][Math.floorMod((int) Math.floor(x),10)].equals("x")){
                    return true;
                }else if((y%1 == 1-scale || y%1 == -scale) && chunk[Math.floorMod((int) Math.floor(y+1),10)][Math.floorMod((int) Math.floor(x+scale-0.01),10)].equals("x")){
                    return true;
                }else{return false;}
            case 'a':
                chunk = maze.getChunk((int) Math.floor((x-1)/10),(int) Math.floor(y/10));
                if(x%1 == 0 && chunk[Math.floorMod((int) Math.floor(y),10)][Math.floorMod((int) Math.floor(x-1),10)].equals("x")){
                    return true;
                }else if(x%1 == 0 && chunk[Math.floorMod((int) Math.floor(y+scale-0.01),10)][Math.floorMod((int) Math.floor(x-1),10)].equals("x")){
                    return true;
                }else{return false;}
            case 'd':
                chunk = maze.getChunk((int) Math.floor((x+1)/10),(int) Math.floor(y/10));
                if((x%1 == 1-scale || x%1 == -scale) && chunk[Math.floorMod((int) Math.floor(y),10)][Math.floorMod((int) Math.floor(x+1),10)].equals("x")){
                    return true;
                }else if((x%1 == 1-scale || x%1 == -scale) && chunk[Math.floorMod((int) Math.floor(y+scale-0.01),10)][Math.floorMod((int) Math.floor(x+1),10)].equals("x")){
                    return true;
                }else{return false;}
        }
        return false;
    }

    public void addY(double y) {
        this.y += y;
    }

    public void addX(double x) {
        this.x += x;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
