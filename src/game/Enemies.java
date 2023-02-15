package game;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.Random;

public class Enemies {
    protected ArrayList<Enemy> enemies;

    public Enemies() {
        enemies = new ArrayList<>();
    }

    public void fillChunk(int chunkX, int chunkY, int time) {
        Random random = new Random();
        int numb = random.nextInt(3 + time / 5000) + 2;
        for (int i = 0; i < numb; i++) {
            if (random.nextInt(3) < 2) {
                enemies.add(new Melee(chunkX * 10 + random.nextInt(5) * 2 + 0.3, chunkY * 10 + random.nextInt(5) * 2 + 0.3, 10, "enemy.png"));
            } else {
                enemies.add(new Ranged(chunkX * 10 + random.nextInt(5) * 2 + 0.3, chunkY * 10 + random.nextInt(5) * 2 + 0.3, 10, "ranged.png"));
            }
        }
    }

    public void draw(Graphics2D g2d, ImageObserver IO, Grid grid) {
        for (Enemy enemy : enemies) {
            enemy.draw(g2d, IO, grid);
        }
    }

    public void update(Player player, Maze maze) {
        int i = 0;
        while (i < enemies.size()) {
            if (enemies.get(i).inView(maze)) {
                enemies.remove(i);
                i--;
            } else {
                enemies.get(i).update(player, maze);
                if (enemies.get(i).getHealth() <= 0) {
                    if (enemies.get(i).getType() == 'm') {
                        player.addScore(10);
                    } else if (enemies.get(i).getType() == 'r') {
                        player.addScore(20);
                    }
                    enemies.remove(i);
                    i--;
                }
            }
            i++;
        }
    }
}
