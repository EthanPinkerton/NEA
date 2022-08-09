package game;

import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;

public class Maze {

    protected int h,w;
    protected Chunk[][] maze;

    public Maze(int height, int width) {
        this.h = height;
        this.w = width;
        this.maze = new Chunk[h][w];

        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                this.maze[j][i] = new Chunk(i-2,j-2);
            }
        }
    }

    public String[][] getChunk(int x, int y){
        return maze[y-maze[0][0].y][x-maze[0][0].x].getChunk();
    }

    @Override
    public String toString(){
        String line = "";
        for (int i = 0; i < (h*10); i++) {
            for (int j = 0; j < (w*10); j++) {
                line += maze[i/10][j/10].getCoord(i%10,j%10);
            }
            line += "\n";
        }
        return line;
    }

    public void draw(Graphics2D g2d,ImageObserver IO, Grid grid){
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                maze[i][j].draw(g2d,IO,grid,j-1,i-1);
            }
        }
    }

    public void chunkLoader(double playerX, double playerY){
        double x = (playerX/10)-maze[0][0].y;
        double y = (playerY/10)-maze[0][0].x;
        if(x < 1.5){
            for (int i = h-1; i >= 0; i--) {
                if(i == 0){
                    for (int j = 0; j < w; j++) {
                        maze[i][j] = new Chunk(maze[i+1][j].getX(),maze[i+1][j].getY()-1);
                    }
                }else {
                    maze[i] = maze[i-1];
                }
            }
        }else if(x > 3.5){
            for (int i = 0; i < h; i++) {
                if(i == h-1){
                    for (int j = 0; j < w; j++) {
                        maze[i][j] = new Chunk(maze[i-1][j].getX(),maze[i-1][j].getY()-1);
                    }
                }else {
                    maze[i] = maze[i+1];
                }
            }
        }
    }
}
