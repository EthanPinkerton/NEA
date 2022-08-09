package game;

import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.util.Arrays;
import java.util.Objects;
import java.util.Random;

public class Chunk {

    protected String[][] chunk;
    protected int x,y;
    private final Image block = new ImageIcon(this.getClass().getResource("placholder1.png")).getImage();
    private final Image space = new ImageIcon(this.getClass().getResource("placholder2.png")).getImage();

    public Chunk(int x, int y) {
        this.x = x;
        this.y = y;
        this.chunk = generate();
    }

    public void draw(Graphics2D g2d, ImageObserver IO, Grid grid, int mX, int mY){
        int offset = 10;
        for (int i = 0; i < chunk.length; i++) {
            for (int j = 0; j < chunk.length; j++) {
                if(chunk[i][j].equals("x")){
                    g2d.drawImage(block,grid.getX(mX*10+j-offset), grid.getY(mY*10+i-offset),grid.getScale(), grid.getScale(), IO);
                }else if(chunk[i][j].equals("o")){
                    g2d.drawImage(space,grid.getX(mX*10+j-offset), grid.getY(mY*10+i-offset),grid.getScale(), grid.getScale(), IO);
                }
            }
        }
    }

//    private String[][] first(){
//        String[] m = {"o","o","o","o","o","o","o","o","o"}; //columns <-- this is a lie they're rows now
//        return addSides(new String[][]{m,m,m,m,m,m,m,m,m});
//    }

    private String[][] generate(){
        Random random = new Random();

        String[][] maze = new String[9][9];
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze.length; j++) {
                if(i%2 == 0 && j%2 == 0){
                    maze[i][j] = "z";
                }else {
                    maze[i][j] = "x";
                }
            }
        }

        int x = random.nextInt(3)*2 + 2;
        int y = random.nextInt(3)*2 + 2;
        int orgX = x;
        int orgY = y;
        maze[y][x] = "e";
        boolean[] da = {false,false,false,false};
        int d;

        while(true) {
            do {
                d = random.nextInt(4);
            } while (da[d] && Arrays.equals(da, new boolean[]{true, true, true, true}));
            switch (d) {
                case 0:
                    if (y != 0 && Objects.equals(maze[y - 1][x], "x") && Objects.equals(maze[y - 2][x], "z")) {
                        maze[y - 1][x] = "e";
                        maze[y][x] = "e";
                        y -= 2;
                        da = new boolean[]{false, false, false, false};
                    } else {
                        da[d] = true;
                    }
                    break;
                case 1:
                    if (y != 8 && Objects.equals(maze[y + 1][x], "x") && Objects.equals(maze[y + 2][x], "z")) {
                        maze[y + 1][x] = "e";
                        maze[y][x] = "e";
                        y += 2;
                        da = new boolean[]{false, false, false, false};
                    } else {
                        da[d] = true;
                    }
                    break;
                case 2:
                    if (x != 0 && Objects.equals(maze[y][x - 1], "x") && Objects.equals(maze[y][x - 2], "z")) {
                        maze[y][x - 1] = "e";
                        maze[y][x] = "e";
                        x -= 2;
                        da = new boolean[]{false, false, false, false};
                    } else {
                        da[d] = true;
                    }
                    break;
                case 3:
                    if (x != 8 && Objects.equals(maze[y][x + 1], "x") && Objects.equals(maze[y][x + 2], "z")) {
                        maze[y][x + 1] = "e";
                        maze[y][x] = "e";
                        x += 2;
                        da = new boolean[]{false, false, false, false};
                    } else {
                        da[d] = true;
                    }
                    break;
            }
            if (Arrays.equals(da, new boolean[]{true, true, true, true})) {
                if (y != 0 && Objects.equals(maze[y - 1][x], "e")) {
                    maze[y - 1][x] = "o";
                    maze[y][x] = "o";
                    y -= 2;
                    da = new boolean[]{false, false, false, false};
                } else if (y != 8 && Objects.equals(maze[y + 1][x], "e")) {
                    maze[y + 1][x] = "o";
                    maze[y][x] = "o";
                    y += 2;
                    da = new boolean[]{false, false, false, false};
                } else if (x != 0 && Objects.equals(maze[y][x - 1], "e")) {
                    maze[y][x - 1] = "o";
                    maze[y][x] = "o";
                    x -= 2;
                    da = new boolean[]{false, false, false, false};
                } else if (x != 8 && Objects.equals(maze[y][x + 1], "e")) {
                    maze[y][x + 1] = "o";
                    maze[y][x] = "o";
                    x += 2;
                    da = new boolean[]{false, false, false, false};
                }
            }
            if (orgX == x && orgY == y && Objects.equals(maze[0][0], "o")) {
                maze[y][x] = "o";
                break;
            }

        }

        return addSides(maze);
    }

    private String[][] addSides(String[][] maze){
        Random random = new Random();
        String[][] newArr = new String[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                try{
                    newArr[i][j] = maze[i][j];
                }catch (ArrayIndexOutOfBoundsException e){
                    newArr[i][j] = "x";
                }
            }
        }

        for (int i = 0; i < 4; i++) {
            if(random.nextBoolean()){
                newArr[random.nextInt(4)*2][9] = "o";
            }else {
                newArr[9][random.nextInt(4)*2] = "o";
            }
        }

        return newArr;
    }

    public String[][] getChunk(){
        return chunk;
    }

    public String getCoord(int x,int y){
        return chunk[y][x];
    }
}
