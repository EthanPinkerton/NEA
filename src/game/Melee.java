package game;

import java.awt.*;
import java.awt.image.ImageObserver;

public class Melee extends Enemy {


    public Melee(double x, double y, double health, String file) {
        super(x, y, health, file, 0.04);
    }

    @Override
    protected void drawAttack(Graphics2D g2d, ImageObserver IO, Grid grid) {

    }

    @Override
    protected boolean checkPlayer(Player player, Maze maze) {
        Rectangle rec = new Rectangle((int) (x * 100), (int) (y * 100), (int) (scale * 100), (int) (scale * 100));
        if (player.intersect(rec)) {
            attack(player);
            return true;
        }
        return false;
    }

    @Override
    protected void attack(Player player) {
        if (hitTimer == 0) {
            player.damage(0.7);
            hitTimer = 16;
        }
    }
}
