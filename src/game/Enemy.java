package game;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.util.Random;

public class Enemy {

    protected double x,y;
    protected Image image;
    protected double health;
    private final double scale = 0.4;
    protected boolean playerVisible;
    protected Vector vector = new Vector(0,0);
    private char direction;
    private boolean following = false;

    public Enemy(double x, double y, double health, String file) {
        this.x = x;
        this.y = y;
        this.health = health;
        this.image = GetResource.get(file);
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
        if(grid.onScreen(x-0.05,y-0.1)) {
            g2d.drawImage(image, grid.getX(x - 0.05), grid.getY(y - 0.1), (int) (grid.getScale() * scale), (int) (grid.getScale() * scale), IO);
            g2d.setColor(Color.RED);
            g2d.fillRect(grid.getX(x - 0.05), grid.getY(y - 0.1), (int) (grid.getScale() * scale * (health / 10.0)), (int) (grid.getScale() * scale / 8.0));
            g2d.setColor(Color.BLACK);
            g2d.drawRect(grid.getX(x - 0.05), grid.getY(y - 0.1), (int) (grid.getScale() * scale), (int) (grid.getScale() * scale / 8.0));
        }
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

    public void moveX(double pX, double pY, Maze maze){
        if(Math.floor(y) == Math.floor(pY)){
            if(x < pX && x+7 > pX){
                for (int i = 0; i < 6; i++) {
                    if(maze.getTile(x+i+1,y).equals("x") && x+i+1 < pX){
                        following = false;
                        return;
                    }
                }
                x += 0.04;
            }else if(x > pX && x-7 < pX){
                for (int i = 0; i < 6; i++) {
                    if(maze.getTile(x-i-1,y).equals("x") && x-i-1 > pX){
                        following = false;
                        return;
                    }
                }
                x -= 0.04;
            }
        }
        following = true;
    }

    public boolean inView(Maze maze){
        return !maze.inBounds((int)x,(int)y);
    }

    private void checkProjectiles(Player player){
        Rectangle rec = new Rectangle((int) (x*100),(int) (y*100),(int) (scale*100),(int) (scale*100));

        if(Math.abs(x - player.getX()) < 3 || Math.abs(y - player.getY()) < 3){
            int i = 0;
            while(i < player.projectilesSize()){
                    if(player.getProjectile(i).intersect(rec)){
                        health -= 1;
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

    private void seePlayer(double pX, double pY, Maze maze){
        if (Math.floor(x) == Math.floor(pX)) {
            if (!maze.collision((int) x, (int) y, (int)(pY-y),true)){
                following = true;
            }
//            if (y < pY && y + 5 > pY) {
//                if (!maze.collision(new Rectangle((int) x, (int) y, 1,(int) (pY-y)))){
//                    following = true;
//                }
//            } else if (y > pY && y - 5 < pY) {
//                if (!maze.collision(new Rectangle((int) x, (int) y, 1,(int) (y-pY)))){
//                    following = true;
//                }
//            }
        }
        if (Math.floor(y) == Math.floor(pY)) {
            if (!maze.collision((int) x,(int) y,(int)(pX-x),false)){
                following = true;
            }
//            if (x < pX && x + 5 > pX) {
//                if (!maze.collision(new Rectangle((int) x,(int) y,(int) (pX-x),1))){
//                    following = true;
//                }
//            } else if (x > pY && x - 5 < pY) {
//                if (!maze.collision(new Rectangle((int) x, (int) y,(int) (x-pX),1))){
//                    following = true;
//                }
//            }
        }
    }

    private void move(double pX, double pY,Maze maze){
        if(following) {
            vector.setI(pX - x);
            vector.setJ(pY - y);
            if (vector.getMod() < 5) {
                if(!maze.collision((int)(x+0.04*vector.iDirection()),(int)y,(int)(x+0.04*vector.iDirection()+scale),(int)(y+scale))) {
                    x += 0.04 * vector.iDirection();
                }
                if(!maze.collision((int)x,(int)(y+0.04*vector.jDirection()),(int)(x+scale),(int)(y+0.04*vector.jDirection()+scale))) {
                    y += 0.04 * vector.jDirection();
                }
            }else {
                following = false;
            }
        }
//        else {
//            switch (direction){
//                case 'w':
//                    y -= 0.04;
//                    break;
//                case 's':
//                    y += 0.04;
//                    break;
//                case 'a':
//                    x -= 0.04;
//                    break;
//                case 'd':
//                    x += 0.04;
//                    break;
//            }
//        }
    }

    public void update(Player player, Maze maze){
        x = Math.round(x*100.0)/100.0;
        y = Math.round(y*100.0)/100.0;
        checkProjectiles(player);
        if(checkPlayer(player)){
            player.damage(0.1);
            return;
        }
        seePlayer(player.getX(), player.getY(), maze);
        move(player.getX(), player.getY(), maze);
    }
}
