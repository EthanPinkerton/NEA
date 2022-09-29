package game;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.Random;

public class Enemies {
    protected ArrayList<Enemy> enemies;

    public Enemies(){
        enemies = new ArrayList<>();
    }

    public void newEnemy(int chunkX, int chunkY){
        Random random = new Random();
        enemies.add(new Enemy(chunkX*10 + random.nextInt(5)*2 + 0.3,chunkY*10 + random.nextInt(5)*2 + 0.3,10,"enemy.png"));
    }

    public void fillChunk(int chunkX, int chunkY){
        Random random = new Random();
        for (int i = 0; i < random.nextInt(5)+2; i++) {
            enemies.add(new Enemy(chunkX*10 + random.nextInt(5)*2 + 0.3, chunkY*10 + random.nextInt(5)*2 + 0.3,10,"enemy.png"));
        }
    }

    public void draw(Graphics2D g2d, ImageObserver IO, Grid grid){
        for (Enemy enemy : enemies) {
            enemy.draw(g2d, IO, grid);
        }
    }

    public void update(Player player,Maze maze){
        int i = 0;
        while(i < enemies.size()){
            enemies.get(i).update(player, maze);
            if(enemies.get(i).getHealth() <= 0){
                enemies.remove(i);
                i--;
            }
            i++;
        }
    }
}
