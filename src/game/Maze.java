package game;

import java.awt.*;
import java.awt.image.ImageObserver;

public class Maze {

    private final Seeder seed;
    protected final int h, w;
    protected int xOffset, yOffset;
    protected Chunk[][] maze;
    protected Enemies enemies;
    protected Items items;

    public Maze(double x, double y, String seed) {
        this.seed = new Seeder(seed);
        this.h = 5;
        this.w = 5;
        this.xOffset = -2 + (int) (x / 10);
        this.yOffset = -2 + (int) (y / 10);
        this.maze = new Chunk[h][w];
        this.enemies = new Enemies();
        this.items = new Items();

        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                this.maze[j][i] = new Chunk(this.seed, (int) (x / 10) + i - 2, (int) (y / 10) + j - 2);
                if (!(i - 2 == 0 && j - 2 == 0)) {
                    enemies.fillChunk(i - 2, j - 2);
                    items.fillChunk(i - 2, j - 2);
                }
            }
        }
    }

    public String getSeed() {
        return seed.getSeed();
    }

    public String[][] getChunkArr(int x, int y) {
        return maze[y - maze[0][0].getY()][x - maze[0][0].getX()].getChunk();
    }

    public String getTile(double x, double y) {
        try {
            return maze[(int) Math.floor(y / 10.0) - maze[0][0].getY()][(int) Math.floor(x / 10.0) - maze[0][0].getX()].getTile(Math.floorMod((int) x, 10), Math.floorMod((int) y, 10));
        } catch (ArrayIndexOutOfBoundsException e) {
            return "x";
        }
    }

    public void removeTile(double x, double y) {
        maze[(int) Math.floor(y / 10.0) - maze[0][0].getY()][(int) Math.floor(x / 10.0) - maze[0][0].getX()].removeTile(Math.floorMod((int) x, 10), Math.floorMod((int) y, 10));
    }

    public Chunk getChunk(int x, int y) {
        return maze[(int) Math.floor(y / 10.0) - maze[0][0].getY()][(int) Math.floor(x / 10.0) - maze[0][0].getX()];
    }

    public boolean inBounds(int x, int y) {
        try {
            getChunk(x, y);
            return true;
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
    }

    public boolean collision(double dx, double dy, int length, boolean ud) {
        int x = (int) Math.floor(dx);
        int y = (int) Math.floor(dy);

        if (ud && length < 0) {
            for (int i = 0; i > length; i--) {
                if (getChunk(x, (y + i)).getTile(Math.floorMod(x, 10), Math.floorMod(y + i, 10)).equals("x")) {
                    return true;
                }
            }
        } else if (ud && length > 0) {
            for (int i = 0; i < length; i++) {
                if (getChunk(x, (y + i)).getTile(Math.floorMod(x, 10), Math.floorMod(y + i, 10)).equals("x")) {
                    return true;
                }
            }
        } else if (!ud && length < 0) {
            for (int i = 0; i > length; i--) {
                if (getChunk((x + i), y).getTile(Math.floorMod(x + i, 10), Math.floorMod(y, 10)).equals("x")) {
                    return true;
                }
            }
        } else if (!ud && length > 0) {
            for (int i = 0; i < length; i++) {
                if (getChunk((x + i), y).getTile(Math.floorMod(x + i, 10), Math.floorMod(y, 10)).equals("x")) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean collision(double dx, double dy, double dWidth, double dHeight) {
        int x = (int) Math.floor(dx);
        int y = (int) Math.floor(dy);
        int width = (int) Math.floor(dWidth);
        int height = (int) Math.floor(dHeight);

        if (getChunk(x, y).getTile(Math.floorMod(x, 10), Math.floorMod(y, 10)).equals("x")) {
            return true;
        }
        if (getChunk(x, height).getTile(Math.floorMod(x, 10), Math.floorMod(height, 10)).equals("x")) {
            return true;
        }
        if (getChunk(width, y).getTile(Math.floorMod(width, 10), Math.floorMod(y, 10)).equals("x")) {
            return true;
        }
        if (getChunk(width, height).getTile(Math.floorMod(width, 10), Math.floorMod(height, 10)).equals("x")) {
            return true;
        }
        return false;
    }

    public void draw(Graphics2D g2d, ImageObserver IO, Grid grid) {
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                maze[i][j].draw(g2d, IO, grid, j + xOffset + 1, i + yOffset + 1);
            }
        }
        enemies.draw(g2d, IO, grid);
        items.draw(g2d, IO, grid);
    }

    public void update(Player player) {
        chunkLoader(player.getX(), player.getY());
        enemies.update(player, this);
        items.update(player);
    }

    public void chunkLoader(double playerX, double playerY) {
        double x = (playerX / 10) - maze[0][0].getX();
        double y = (playerY / 10) - maze[0][0].getY();
        if (x < 1.5) {
            for (int i = w - 1; i >= 0; i--) {
                if (i == 0) {
                    for (int j = 0; j < h; j++) {
                        maze[j][i] = new Chunk(seed, maze[j][i + 1].getX() - 1, maze[j][i + 1].getY());
                        enemies.fillChunk(maze[j][i + 1].getX() - 1, maze[j][i + 1].getY());
                        items.fillChunk(maze[j][i + 1].getX() - 1, maze[j][i + 1].getY());
                    }
                } else {
                    for (int j = 0; j < h; j++) {
                        maze[j][i] = maze[j][i - 1];
                    }
                }
            }
            xOffset -= 1;
        } else if (x > 3.5) {
            for (int i = 0; i < w; i++) {
                if (i == w - 1) {
                    for (int j = 0; j < h; j++) {
                        maze[j][i] = new Chunk(seed, maze[j][i - 1].getX() + 1, maze[j][i - 1].getY());
                        enemies.fillChunk(maze[j][i - 1].getX() + 1, maze[j][i - 1].getY());
                        items.fillChunk(maze[j][i - 1].getX() + 1, maze[j][i - 1].getY());
                    }
                } else {
                    for (int j = 0; j < h; j++) {
                        maze[j][i] = maze[j][i + 1];
                    }
                }
            }
            xOffset += 1;
        }

        if (y < 1.5) {
            for (int i = h - 1; i >= 0; i--) {
                if (i == 0) {
                    Chunk[] temp = new Chunk[w];
                    for (int j = 0; j < w; j++) {
                        temp[j] = new Chunk(seed, maze[i][j].getX(), maze[i][j].getY() - 1);
                        enemies.fillChunk(maze[i][j].getX(), maze[i][j].getY() - 1);
                    }
                    maze[i] = temp;
                } else {
                    maze[i] = maze[i - 1];
                }
            }
            yOffset -= 1;
        } else if (y > 3.5) {
            for (int i = 0; i < h; i++) {
                if (i == h - 1) {
                    Chunk[] temp = new Chunk[w];
                    for (int j = 0; j < w; j++) {
                        temp[j] = new Chunk(seed, maze[i][j].getX(), maze[i][j].getY() + 1);
                        enemies.fillChunk(maze[i][j].getX(), maze[i][j].getY() + 1);
                    }
                    maze[i] = temp;
                } else {
                    maze[i] = maze[i + 1];
                }
            }
            yOffset += 1;
        }
    }
}
