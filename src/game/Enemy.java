package game;

import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.Random;

public class Enemy {

    protected double x,y;
    protected Image image;
    protected double health;
    private final double scale = 0.4;
    protected boolean playerVisible;
    private char direction;

    public Enemy(double x, double y, double health, String file) {
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

    public double getHealth() {
        return health;
    }

    public void draw(Graphics2D g2d, ImageObserver IO, Grid grid){
        g2d.drawImage(image,grid.getX(x-0.05),grid.getY(y-0.1),(int) (grid.getScale()*scale),(int) (grid.getScale()*scale),IO);
        g2d.setColor(Color.RED);
        g2d.fillRect(grid.getX(x-0.05), grid.getY(y-0.1),(int) (grid.getScale()*scale*(health/10.0)), (int) (grid.getScale()*scale/8.0));
        g2d.setColor(Color.BLACK);
        g2d.drawRect(grid.getX(x-0.05), grid.getY(y-0.1),(int) (grid.getScale()*scale),(int) (grid.getScale()*scale/8.0));
    }

    public double r1dp(double x){
        return Math.round(x*10)/10.0;
    }

    public boolean moveY(double pX, double pY, Maze maze) {
        if (Math.floor(x) == Math.floor(pX)) {
            if (y < pY && y+7 > pY) {
                for (int i = 0; i < 6; i++) {
                    if (maze.getTile(x, y+i+1).equals("x") && y+i+1 < pY) {
                        return false;
                    }
                }
                y += 0.04;
            } else if (y > pY && y-7 < pY) {
                for (int i = 0; i < 6; i++) {
                    if (maze.getTile(x, y-i-1).equals("x") && y-i-1 > pY) {
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
            if(x < pX && x+7 > pX){
                for (int i = 0; i < 6; i++) {
                    System.out.println(x+i+1 + " " + y);
                    if(maze.getTile(x+i+1,y).equals("x") && x+i+1 < pX){
                        return false;
                    }
                }
                x += 0.04;
            }else if(x > pX && x-7 < pX){
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

    private void checkProjectiles(Player player){
        Rectangle rec = new Rectangle((int) (x*100),(int) (y*100),(int) (scale*100),(int) (scale*100));

        if(Math.abs(x - player.getX()) < 3 || Math.abs(y - player.getY()) < 3){
            int i = 0;
            while(i < player.projectilesSize()){
                    if(player.getProjectile(i).intersect(rec)){
                        health -= 2;
                        player.removeProjectile(i);
                        i--;
                    }
                i++;
            }
        }
    }

    private boolean checkPlayer(Player player){
        Rectangle rec = new Rectangle((int) (x*100),(int) (y*100),(int) (scale*100),(int) (scale*100));
        return player.intersect(rec);
    }

    public void update(Player player, Maze maze){
        x = Math.round(x*100.0)/100.0;
        y = Math.round(y*100.0)/100.0;
        checkProjectiles(player);
        if(checkPlayer(player)){
            return;
        }
        boolean yTrue = moveY(player.getX(), player.getY(), maze);
        boolean xTrue = moveX(player.getX(), player.getY(), maze);
//        if(!yTrue && !xTrue){
//
//        }
    }
}
