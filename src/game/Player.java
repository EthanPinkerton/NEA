package game;

import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.util.Arrays;

public class Player{

    protected double x;
    protected double y;
    protected Image image;

    public Player(double x, double y, String file) {
        this.x = x;
        this.y = y;
        this.image = new ImageIcon(this.getClass().getResource(file)).getImage();
    }

    public void draw(Graphics2D g2d, ImageObserver IO,Grid grid){
        g2d.drawImage(image,grid.getX(x),grid.getY(y),grid.getScale()/2,grid.getScale()/2,IO);
    }

    public void keyPress(KeyListener kl, Maze maze,int scale){
//        if(kl.isKeyW()){addY(-0.1);}
//        if(kl.isKeyS()){addY(0.1);}
//        if(kl.isKeyA()){addX(-0.1);}
//        if(kl.isKeyD()){addX(0.1);}
        if(kl.isKeyW() && !collision(maze,'w',scale)){addY(-0.1);}
        if(kl.isKeyS() && !collision(maze,'s',scale)){addY(0.1);}
        if(kl.isKeyA() && !collision(maze,'a',scale)){addX(-0.1);}
        if(kl.isKeyD() && !collision(maze,'d',scale)){addX(0.1);}
    }

    private boolean collision(Maze maze,char key,int scale){
        x = Math.round(x*10)/10.0;
        y = Math.round(y*10)/10.0;
        String[][] chunk;
        switch (key){
            case 'w':
                chunk = maze.getChunk((int) x/10,(int) (y-1)/10);
                if(y%1 == 0 && chunk[Math.floorMod((int) y-1,10)][Math.floorMod((int) x,10)].equals("x")){
                    System.out.println(Arrays.deepToString(chunk));
                    System.out.println(Math.floorMod((int) y-1,10)+" "+Math.floorMod((int) x,10));
//                if(y%1 == 0 && chunk[(int) (Math.abs(y-1)%10)][(int) Math.abs(x)%10].equals("x")){
                    return true;
                }else if(y%1 == 0 && chunk[Math.floorMod((int) y-1,10)][Math.floorMod((int) (x+0.5),10)].equals("x")){
                    return true;
                }else{return false;}
            case 's':
                chunk = maze.getChunk((int) x/10,(int) (y+1)/10);
                if(y%1 == 0 && chunk[Math.floorMod((int) (y+1.5),10)][Math.floorMod((int) x,10)].equals("x")){
//                if(y%1 == 0 && chunk[(int) Math.abs(y+1+scale/2.0)%10][(int) Math.abs(x)%10].equals("x")){
                    return true;
                }else if(y%1 == 0 && chunk[Math.floorMod((int) (y+1.5),10)][Math.floorMod((int) (x+0.5),10)].equals("x")){
                    return true;
                }else{return false;}
            case 'a':
                chunk = maze.getChunk((int) (x-1)/10,(int) y/10);
                if(x%1 == 0 && chunk[Math.floorMod((int) y,10)][Math.floorMod((int) x-1,10)].equals("x")){
//                if(x%1 == 0 && chunk[(int) Math.abs(y)%10][(int) Math.abs(x-1)%10].equals("x")){
                    return true;
                }else if(x%1 == 0 && chunk[Math.floorMod((int) (y+0.5),10)][Math.floorMod((int) x-1,10)].equals("x")){
                    return true;
                }else{return false;}
            case 'd':
                chunk = maze.getChunk((int) (x+1)/10,(int) y/10);
                if(x%1 == 0 && chunk[Math.floorMod((int) y,10)][Math.floorMod((int) (x+1.5),10)].equals("x")){
//                if(x%1 == 0 && chunk[(int) Math.abs(y)%10][(int) Math.abs(x+1+scale/2.0)%10].equals("x")){
                    return true;
                }else if(x%1 == 0 && chunk[Math.floorMod((int) (y+0.5),10)][Math.floorMod((int) (x+1.5),10)].equals("x")){
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
