package game;

import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;

public class Maze {

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
                this.maze[j][i] = new Chunk(i,j);
            }
        }
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

    public void draw(Graphics2D g2d, ImageObserver IO, Grid grid){
        String m = toString();
        double x = -grid.getScale()/2+2.5;
        double y = -grid.getScale()/2+2;
        for (int i = 0; i < m.length(); i++) {
            if(m.charAt(i) == 'x'){
                g2d.drawImage(block,(int) grid.getX(x),(int) grid.getY(y),grid.getScale(),grid.getScale(),IO);
                x += 1;
            }else if(m.charAt(i) == 'o'){
                g2d.drawImage(space,(int) grid.getX(x),(int) grid.getY(y),grid.getScale(),grid.getScale(),IO);
                x += 1;
            }else{
                x = -grid.getScale()/2+2.5;
                y += 1;
            }
        }
    }
}
