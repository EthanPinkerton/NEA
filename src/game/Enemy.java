package game;

import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.util.Random;

public class Enemy {

    protected double x,y;
    protected Image image;
    protected int health;
    private final double scale = 0.4;
    protected boolean playerVisible;
    private char direction;

    public Enemy(double x, double y, int health, String file) {
        this.x = x;
        this.y = y;
        this.health = health;
        this.image = new ImageIcon(this.getClass().getResource(file)).getImage();
        playerVisible = false;
        direction = rChar();
    }

    private char rChar(){
        char[] chars = new char[]{'w','s','a','d'};
        Random random = new Random();
        return chars[random.nextInt(4)];
    }

    public void draw(Graphics2D g2d, ImageObserver IO, Grid grid){
        g2d.drawImage(image,grid.getX(x-0.05),grid.getY(y-0.1),(int) (grid.getScale()*scale),(int) (grid.getScale()*scale),IO);
    }

    public double r1dp(double x){
        return Math.round(x*10)/10.0;
    }

    public boolean moveY(double pX, double pY, Maze maze) {
        if (Math.floor(x) == Math.floor(pX)) {
            if (y < pY) {
                for (int i = 0; i < 6; i++) {
                    if (maze.getTile(x, y + i + 1).equals("x") && y + i + 1 < pY) {
                        return false;
                    }
                }
                y += 0.04;
            } else if (y > pY) {
                for (int i = 0; i < 6; i++) {
                    if (maze.getTile(x, y - i - 1).equals("x") && y - i - 1 > pY) {
                        return false;
                    }
                }
                y -= 0.04;
            }
        }
        return true;
    }

    public boolean moveX(double pX, double pY, Maze maze){
        if(Math.floor(y) == Math.floor(pY)){
            if(x < pX){
                for (int i = 0; i < 6; i++) {
                    if(maze.getTile(x+i+1,y).equals("x") && x+i+1 < pX){
                        return false;
                    }
                }
                x += 0.04;
            }else if(x > pX){
                for (int i = 0; i < 6; i++) {
                    if(maze.getTile(x-i-1,y).equals("x") && x-i-1 > pX){
                        return false;
                    }
                }
                x -= 0.04;
            }
        }
        return true;
    }

    public void update(double pX, double pY, Maze maze){
        boolean yTrue = moveY(pX,pY,maze);
        boolean xTrue = moveX(pX,pY,maze);
        if(!yTrue && !xTrue){

        }
    }
}
