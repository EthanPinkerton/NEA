package Main.Game.Maze;

import Main.Game.GetResource;
import Main.Game.GUI.Grid;
import Main.Game.Player.Player;

import java.awt.*;
import java.awt.image.ImageObserver;

public class Item {

    protected char type;
    protected double x, y;
    protected Image image;
    protected double scale;

    public Item(double x, double y, char type, String file) {
        this.x = x;
        this.y = y;
        this.scale = 0.25;
        this.type = type;
        this.image = GetResource.getImage(file);
    }

    public void draw(Graphics2D g2d, ImageObserver IO, Grid grid) {
        if (grid.onScreen(x, y)) {
            g2d.drawImage(image, grid.getX(x), grid.getY(y), (int) (grid.getScale() * scale), (int) (grid.getScale() * scale), IO);
        }
    }

    public boolean update(Player player) {
        Rectangle rec = new Rectangle((int) (x * 100), (int) (y * 100), (int) (scale * 100), (int) (scale * 100));
        if (player.intersect(rec)) {
            player.addItem(type);
            return true;
        }
        return false;
    }
}
