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

    public Player(double x, double y, String file) {
        this.x = x;
        this.y = y;
        this.direction = 'd';
        this.keyPress = 0;
        this.image = new ImageIcon(this.getClass().getResource(file)).getImage();
    }

    public void draw(Graphics2D g2d, ImageObserver IO,Grid grid){
        g2d.drawImage(image,grid.getX(x),grid.getY(y),grid.getScale()/2,grid.getScale()/2,IO);
        for (int i = 0; i < projectiles.size(); i++) {
            projectiles.get(i).draw(g2d, IO, grid);
        }
    }

    public void update(KeyListener kl, Maze maze){
        if(keyPress > 0){keyPress -= 1;}
        int i = 0;
        while(i < projectiles.size()){
            if(projectiles.get(i).collision(maze)){
                projectiles.remove(i);
                i--;
            }
            i++;
        }
        keyPress(kl, maze);
    }

    public void keyPress(KeyListener kl, Maze maze){
//        if(kl.isKeyW()){addY(-0.1);}
//        if(kl.isKeyS()){addY(0.1);}
//        if(kl.isKeyA()){addX(-0.1);}
//        if(kl.isKeyD()){addX(0.1);}
        if(kl.isKeyW() && !collision(maze,'w')){addY(-0.1); direction = 'w';}
        if(kl.isKeyS() && !collision(maze,'s')){addY(0.1); direction = 's';}
        if(kl.isKeyA() && !collision(maze,'a')){addX(-0.1); direction = 'a';}
        if(kl.isKeyD() && !collision(maze,'d')){addX(0.1); direction = 'd';}
        if(kl.isKeyE() && keyPress == 0){projectiles.add(new Projectile(x,y,"bullet.png",direction)); keyPress = 10;}
    }

    private boolean collision(Maze maze,char key){
        x = Math.round(x*10)/10.0;
        y = Math.round(y*10)/10.0;
        String[][] chunk;
        switch (key){
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
