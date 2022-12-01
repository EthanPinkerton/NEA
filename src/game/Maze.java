package game;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.util.Random;

public class Maze {

    private final Seeder seed;
    protected int h,w;
    protected int xOffset, yOffset;
    protected Chunk[][] maze;
    protected Enemies enemies;
    protected Items items;

    public Maze(int height, int width) {
        this.seed = new Seeder();
        this.h = height;
        this.w = width;
        this.xOffset = -2;
        this.yOffset = -2;
        this.maze = new Chunk[h][w];
        this.enemies = new Enemies();
        this.items = new Items();

        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                this.maze[j][i] = new Chunk(seed,i-2,j-2);
                if(!(i-2==0 && j-2==0)){
                    enemies.fillChunk(i-2,j-2);
                    items.fillChunk(i-2,j-2);
                }
            }
        }
        seed.printStats();
    }

    public String getSeed(){
        return seed.getSeed();
    }

    public String[][] getChunk(int x, int y){
        return maze[y-maze[0][0].getY()][x-maze[0][0].getX()].getChunk();
    }

    public String getTile(double x, double y){
        try{
            return maze[(int)Math.floor(y/10.0) - maze[0][0].getY()][(int)Math.floor(x/10.0) - maze[0][0].getX()].getTile(Math.floorMod((int)x,10),Math.floorMod((int)y,10));
        }catch (ArrayIndexOutOfBoundsException e){
            return "x";
        }
    }

    public void removeTile(double x, double y){
        maze[(int)Math.floor(y/10.0) - maze[0][0].getY()][(int)Math.floor(x/10.0) - maze[0][0].getX()].removeTile(Math.floorMod((int)x,10),Math.floorMod((int)y,10));
    }

    public boolean collision(Rectangle rectangle){
        for (int i = 0; i < rectangle.getHeight(); i++) {
            if(maze[(int)Math.floor((rectangle.getY()+i)/10.0) - maze[0][0].getY()][(int)Math.floor(rectangle.getX()/10.0) - maze[0][0].getX()].getTile(Math.floorMod((int)rectangle.getX(),10),Math.floorMod((int)rectangle.getY()+i,10)).equals("x")){
                return true;
            }
        }

        for (int i = 1; i < rectangle.getWidth(); i++) {
            if(maze[(int)Math.floor(rectangle.getY()/10.0) - maze[0][0].getY()][(int)Math.floor((rectangle.getX()+i)/10.0) - maze[0][0].getX()].getTile(Math.floorMod((int)(rectangle.getX()+i),10),Math.floorMod((int)rectangle.getY(),10)).equals("x")){
                return true;
            }
        }

        return false;
    }

    public boolean collision(char direction, double x, double y){
        x = Math.round(x*10)/10.0;
        y = Math.round(y*10)/10.0;
        String[][] chunk;
        switch (direction){
            case 'w':
                chunk = getChunk((int) Math.floor(x/10),(int) Math.floor((y-1)/10));
                if(y%1 == 0 && chunk[Math.floorMod((int) Math.floor(y-1),10)][Math.floorMod((int) Math.floor(x),10)].equals("x")){
                    return true;
                }else if(y%1 == 0 && chunk[Math.floorMod((int) Math.floor(y-1),10)][Math.floorMod((int) Math.floor(x+0.4),10)].equals("x")){
                    return true;
                }else{y -= 0.1; return false;}
            case 's':
                chunk = getChunk((int) Math.floor(x/10),(int) Math.floor((y+1)/10));
                if(Math.abs(y%1) == 0.5 && chunk[Math.floorMod((int) Math.floor(y+1),10)][Math.floorMod((int) Math.floor(x),10)].equals("x")){
                    return true;
                }else if(Math.abs(y%1) == 0.5 && chunk[Math.floorMod((int) Math.floor(y+1),10)][Math.floorMod((int) Math.floor(x+0.4),10)].equals("x")){
                    return true;
                }else{y += 0.1; return false;}
            case 'a':
                chunk = getChunk((int) Math.floor((x-1)/10),(int) Math.floor(y/10));
                if(x%1 == 0 && chunk[Math.floorMod((int) Math.floor(y),10)][Math.floorMod((int) Math.floor(x-1),10)].equals("x")){
                    return true;
                }else if(x%1 == 0 && chunk[Math.floorMod((int) Math.floor(y+0.4),10)][Math.floorMod((int) Math.floor(x-1),10)].equals("x")){
                    return true;
                }else{x -= 0.1; return false;}
            case 'd':
                chunk = getChunk((int) Math.floor((x+1)/10),(int) Math.floor(y/10));
                if(Math.abs(x%1) == 0.5 && chunk[Math.floorMod((int) Math.floor(y),10)][Math.floorMod((int) Math.floor(x+1),10)].equals("x")){
                    return true;
                }else if(Math.abs(x%1) == 0.5 && chunk[Math.floorMod((int) Math.floor(y+0.4),10)][Math.floorMod((int) Math.floor(x+1),10)].equals("x")){
                    return true;
                }else{x += 0.1; return false;}
        }
        return false;
    }

//    @Override
//    public String toString(){
//        String line = "";
//        for (int i = 0; i < (h*10); i++) {
//            for (int j = 0; j < (w*10); j++) {
//                line += maze[i/10][j/10].getCoord(i%10,j%10);
//            }
//            line += "\n";
//        }
//        return line;
//    }

    public void draw(Graphics2D g2d,ImageObserver IO, Grid grid){
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                maze[i][j].draw(g2d,IO,grid,j+xOffset+1,i+yOffset+1);
            }
        }
        enemies.draw(g2d, IO, grid);
        items.draw(g2d, IO, grid);
    }

    public void update(Player player){
        chunkLoader(player.getX(), player.getY());
        enemies.update(player,this);
        items.update(player);
    }

    public void chunkLoader(double playerX, double playerY){
        double x = (playerX/10)-maze[0][0].getX();
        double y = (playerY/10)-maze[0][0].getY();
        if(x < 1.5){
            for (int i = w-1; i >= 0; i--) {
                if(i == 0){
                    for (int j = 0; j < h; j++) {
                        maze[j][i] = new Chunk(seed,maze[j][i+1].getX()-1,maze[j][i+1].getY());
                        enemies.fillChunk(maze[j][i+1].getX()-1,maze[j][i+1].getY());
                        items.fillChunk(maze[j][i+1].getX()-1,maze[j][i+1].getY());
                    }
                }else {
                    for (int j = 0; j < h; j++) {
                        maze[j][i] = maze[j][i-1];
                    }
                }
            }
            xOffset -= 1;
        }else if(x > 3.5){
            for (int i = 0; i < w; i++) {
                if(i == w-1){
                    for (int j = 0; j < h; j++) {
                        maze[j][i] = new Chunk(seed,maze[j][i-1].getX()+1,maze[j][i-1].getY());
                        enemies.fillChunk(maze[j][i-1].getX()+1,maze[j][i-1].getY());
                        items.fillChunk(maze[j][i-1].getX()+1,maze[j][i-1].getY());
                    }
                }else {
                    for (int j = 0; j < h; j++) {
                        maze[j][i] = maze[j][i+1];
                    }
                }
            }
            xOffset += 1;
        }

        if(y < 1.5){
            for (int i = h-1; i >= 0; i--) {
                if(i == 0){
                    Chunk[] temp = new Chunk[w];
                    for (int j = 0; j < w; j++) {
                        temp[j] = new Chunk(seed,maze[i][j].getX(),maze[i][j].getY()-1);
                        enemies.fillChunk(maze[i][j].getX(),maze[i][j].getY()-1);
                    }
                    maze[i] = temp;
                }else {
                    maze[i] = maze[i-1];
                }
            }
            yOffset -= 1;
        }else if(y > 3.5){
            for (int i = 0; i < h; i++) {
                if(i == h-1){
                    Chunk[] temp = new Chunk[w];
                    for (int j = 0; j < w; j++) {
                        temp[j] = new Chunk(seed,maze[i][j].getX(),maze[i][j].getY()+1);
                        enemies.fillChunk(maze[i][j].getX(),maze[i][j].getY()+1);
                    }
                    maze[i] = temp;
                }else {
                    maze[i] = maze[i+1];
                }
            }
            yOffset += 1;
        }
    }
}
