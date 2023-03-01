package Main.Game.Maze;

import Main.Game.GUI.Grid;
import Main.Game.Player.Player;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.Random;

public class Items {
    protected ArrayList<Item> items;

    public Items() {
        this.items = new ArrayList<>();
    }

    public void fillChunk(int chunkX, int chunkY) {
        Random random = new Random();
        for (int i = 0; i < random.nextInt(4) + 1; i++) {
            char type = randType();
            items.add(new Item(chunkX * 10 + random.nextInt(5) * 2 + 0.3, chunkY * 10 + random.nextInt(5) * 2 + 0.3, type, getFile(type)));
        }
    }

    private char randType() {
        char[] chars = new char[]{'b', 't', 's', 'p'};
        Random random = new Random();
        return chars[random.nextInt(3)];
    }

    private String getFile(char ch) {
        switch (ch) {
            case 'b':
                return "bomb.jpg";
            case 't':
                return "teleport.jpeg";
            case 's':
                return "speed.jpg";
            case 'p':
                return "bullet.jpg";
        }
        return "bomb.jpg";
    }

    public void draw(Graphics2D g2d, ImageObserver IO, Grid grid) {
        for (Item item : items) {
            item.draw(g2d, IO, grid);
        }
    }

    public void update(Player player) {
        int i = 0;
        while (i < items.size()) {
            if (items.get(i).update(player)) {
                items.remove(i);
                i--;
            }
            i++;
        }
    }
}
