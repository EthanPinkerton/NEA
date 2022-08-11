package game;

import java.awt.*;
import java.awt.image.ImageObserver;

public class Maze {

    protected int h,w;
    protected int xOffset, yOffset;
    protected Chunk[][] maze;

    public Maze(int height, int width) {
        this.h = height;
        this.w = width;
        this.xOffset = -2;
        this.yOffset = -2;
        this.maze = new Chunk[h][w];

        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                this.maze[j][i] = new Chunk(i-2,j-2);
            }
        }
    }

    public String[][] getChunk(int x, int y){
        return maze[y-maze[0][0].getY()][x-maze[0][0].getX()].getChunk();
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
    }

    public void chunkLoader(double playerX, double playerY){
        double x = (playerX/10)-maze[0][0].getX();
        double y = (playerY/10)-maze[0][0].getY();
        if(x < 1.5){
            for (int i = w-1; i >= 0; i--) {
                if(i == 0){
                    for (int j = 0; j < h; j++) {
                        maze[j][i] = new Chunk(maze[j][i+1].getX()-1,maze[j][i+1].getY());
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
                        maze[j][i] = new Chunk(maze[j][i-1].getX()+1,maze[j][i-1].getY());
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
                        temp[j] = new Chunk(maze[i][j].getX(),maze[i][j].getY()-1);
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
                        temp[j] = new Chunk(maze[i][j].getX(),maze[i][j].getY()+1);
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
