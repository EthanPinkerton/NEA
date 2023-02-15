package game;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.util.ArrayList;

public class Ranged extends Enemy {

    private final ArrayList<Projectile> arrows;

    public Ranged(double x, double y, double health, String file) {
        super(x, y, health, file, 0.03);
        arrows = new ArrayList<>();
    }

    @Override
    protected void drawAttack(Graphics2D g2d, ImageObserver IO, Grid grid) {
        for (Projectile arrow : arrows) {
            arrow.draw(g2d, IO, grid);
        }
    }

    @Override
    protected boolean checkPlayer(Player player, Maze maze) {
        if (hitTimer != 0) {
            hitTimer -= 1;
        }
        if (vector.getMod() < 4 && following) {
            attack(player);
        }
        updateProjectiles(player, maze);
        return false;
    }

    private void updateProjectiles(Player player, Maze maze) {
        int i = 0;
        while (i < arrows.size()) {
            try {
                if (arrows.get(i).collision(maze)) {
                    arrows.remove(i);
                    i--;
                } else if (player.intersect(arrows.get(i).getRectangle())) {
                    player.damage(0.2);
                    arrows.remove(i);
                    i--;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                arrows.remove(i);
                i--;
            }
            i++;
        }
    }

    @Override
    protected void attack(Player player) {
        if (hitTimer == 0) {
            char direction = vector.direction();
            if (direction != 'n') {
                arrows.add(new Projectile(x, y, "bullet.png", direction, scale / 1.5));
                hitTimer = 22;
            }
        }

    }
}
