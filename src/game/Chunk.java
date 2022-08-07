package game;

import java.util.Arrays;
import java.util.Objects;
import java.util.Random;

public class Chunk {

    protected String[][] chunk;
    protected int x,y;

    public Chunk(int x, int y) {
        this.x = x;
        this.y = y;
        if(x == 0 && y == 0){
            this.chunk = first();
        }else {
            this.chunk = generate();
        }
    }

    private String[][] first(){
        String[] e = {"x","x","x","x","o","x","x","x","x","x"}; //columns
        String[] m = {"o","o","o","o","o","o","o","o","o","x"};
        return new String[][]{m,m,m,m,m,m,m,m,m,e};
    }

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

        int x = random.nextInt(4)*2;
        int y = random.nextInt(4)*2;
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
