package game;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.Random;

public class Items {
    protected ArrayList<Item> items;

    public Items() {
        this.items = new ArrayList<>();
    }

    public void fillChunk(int chunkX, int chunkY){
        Random random = new Random();
        for (int i = 0; i < random.nextInt(3)+1; i++) {
            items.add(new Item(chunkX*10 + random.nextInt(5)*2 + 0.3, chunkY*10 + random.nextInt(5)*2 + 0.3,'b',"item.jpg"));
        }
    }

    public void draw(Graphics2D g2d, ImageObserver IO, Grid grid){
        for (Item item : items) {
            item.draw(g2d, IO, grid);
        }
    }

    public void update(Player player){
        int i = 0;
        while(i < items.size()){
            items.get(i).update(player);
            if(items.get(i).isCollected()){
                items.remove(i);
                i--;
            }
            i++;
        }
    }
}
