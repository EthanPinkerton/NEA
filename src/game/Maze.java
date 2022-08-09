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

//    public void draw(Graphics2D g2d, ImageObserver IO, Grid grid){
//        String m = toString();
//        double x = 0;
//        double y = 0;
////        double x = -grid.getScale()/4.0+1.25;
////        double y = -grid.getScale()/4.0+1;
//        for (int i = 0; i < m.length(); i++) {
//            if(m.charAt(i) == 'x'){
//                g2d.drawImage(block,grid.getX(x),grid.getY(y),grid.getScale(),grid.getScale(),IO);
//                x += 1;
//            }else if(m.charAt(i) == 'o'){
//                g2d.drawImage(space,grid.getX(x),grid.getY(y),grid.getScale(),grid.getScale(),IO);
//                x += 1;
//            }else{
////                x = -grid.getScale()/4.0+1.25;
//                x = 0;
//                y += 1;
//            }
//        }
//    }
}
