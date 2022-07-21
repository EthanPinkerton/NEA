package game;

import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;

public class Maze {

    private final int scale = 32;
    private final Image block = new ImageIcon(this.getClass().getResource("placholder1.png")).getImage();
    private final Image space = new ImageIcon(this.getClass().getResource("placholder2.png")).getImage();
    protected int h,w;
    protected Chunk[][] maze;

    public Maze(int height, int width) {
        this.h = height;
        this.w = width;
        this.maze = new Chunk[h][w];

        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                this.maze[j][i] = new Chunk(j,i);
            }
        }
    }

    @Override
    public String toString(){
        String line = "";
        for (int i = 0; i < (h*9); i++) {
            for (int j = 0; j < (w*9); j++) {
                line += maze[i%h][j%w].getCoord(i%9,j%9);
            }
            line += "\n";
        }
        return line;
    }

    public void draw(Graphics2D g2d, ImageObserver IO){
        String m = toString();
        int x = 0;
        int y = 0;
        for (int i = 0; i < m.length(); i++) {
            if(m.charAt(i) == 'x'){
                g2d.drawImage(block,x,y,scale,scale,IO);
                x += scale;
            }else if(m.charAt(i) == 'o'){
                g2d.drawImage(space,x,y,scale,scale,IO);
                x += scale;
            }else{
                x = 0;
                y += scale;
            }
        }
    }
}
