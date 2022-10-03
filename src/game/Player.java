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
    private HealthBar healthBar;

    public Player(double x, double y, double health, String file) {
        this.x = x;
        this.y = y;
        this.healthBar = new HealthBar(health);
        this.direction = 'd';
        this.keyPress = 0;
        this.image = new ImageIcon(this.getClass().getResource(file)).getImage();
    }

    public void draw(Graphics2D g2d, ImageObserver IO,Grid grid){
        g2d.drawImage(image,grid.getX(x),grid.getY(y), (int) (grid.getScale()*scale),(int) (grid.getScale()*scale),IO);
        healthBar.draw(g2d);
        for (Projectile projectile : projectiles) {
            projectile.draw(g2d, IO, grid);
        }
    }

    public void update(KeyListener kl, Maze maze){
        keyPress(kl, maze);
        if(keyPress > 0){keyPress -= 1;}
        int i = 0;
        while(i < projectiles.size()){
            try{
                if(projectiles.get(i).collision(maze)){
                    projectiles.remove(i);
                    i--;
                }
            }catch (ArrayIndexOutOfBoundsException e){
                projectiles.remove(i);
                i--;
            }
            i++;
        }
    }

    public void keyPress(KeyListener kl, Maze maze){
        if(kl.isKeyW() && !collision(maze,'w')){
            addY(-0.05);
            if(changeDirection(kl)){direction = 'w';}
        }
        if(kl.isKeyS() && !collision(maze,'s')){
            addY(0.05);
            if(changeDirection(kl)){direction = 's';}
        }
        if(kl.isKeyA() && !collision(maze,'a')){
            addX(-0.05);
            if(changeDirection(kl)){direction = 'a';}
        }
        if(kl.isKeyD() && !collision(maze,'d')) {
            addX(0.05);
            if (changeDirection(kl)) {direction = 'd';}
        }
        if(kl.isKeyUp() && keyPress == 0){projectiles.add(new Projectile(x,y,"bullet.png",'w',scale)); keyPress = 10;}
        if(kl.isKeyDown() && keyPress == 0){projectiles.add(new Projectile(x,y,"bullet.png",'s',scale)); keyPress = 10;}
        if(kl.isKeyLeft() && keyPress == 0){projectiles.add(new Projectile(x,y,"bullet.png",'a',scale)); keyPress = 10;}
        if(kl.isKeyRight() && keyPress == 0){projectiles.add(new Projectile(x,y,"bullet.png",'d',scale)); keyPress = 10;}
        if(kl.isKeySpace() && keyPress == 0){projectiles.add(new Projectile(x,y,"bullet.png",direction,scale)); keyPress = 10;}
//        if(kl.isKeyW()){addY(-0.1);}
//        if(kl.isKeyS()){addY(0.1);}
//        if(kl.isKeyA()){addX(-0.1);}
//        if(kl.isKeyD()){addX(0.1);}
    }

    private boolean changeDirection(KeyListener kl){
        if(direction == 'w' && kl.isKeyW()){
            return false;
        }else if(direction == 's' && kl.isKeyS()){
            return false;
        }else if(direction == 'a' && kl.isKeyA()){
            return false;
        }else if(direction == 'd' && kl.isKeyD()){
            return false;
        }
        return true;
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

    public double getHealth(){
        return healthBar.getHealth();
    }

    public boolean intersect(Rectangle rec){
        if(rec.intersects((int) (x*100),(int) (y*100),(int) (scale*100),(int) (scale*100))){
            healthBar.damage(0.05);
            return true;
        }
        return false;
    }

    public void removeProjectile(int i){
        projectiles.remove(i);
    }

    public int projectilesSize(){
        return projectiles.size();
    }

    public ArrayList<Projectile> getProjectiles() {
        return projectiles;
    }

    public Projectile getProjectile(int i){
        return projectiles.get(i);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
