package game;

import java.util.ArrayList;

public class Archer extends Enemy{

    private final ArrayList<Projectile> arrows;
    private int fire;

    public Archer(double x, double y, double health, String file) {
        super(x, y, health, file);
        fire = 0;
        arrows = new ArrayList<>();
    }

    @Override
    protected boolean checkPlayer(Player player) {
        if (fire != 0) {
            fire -= 1;
        }
        if (vector.getMod() < 4) {
            attack(player);
        }
        for (Projectile arrow : arrows) {
            if (player.intersect(arrow.getRectangle())) {
                player.damage(0.7);
                arrows.remove(arrow);
            }
        }
        return false;
    }

    @Override
    protected void attack(Player player) {
        if (fire == 0) {
            char direction = vector.direction();
            if (direction != 'n') {
                arrows.add(new Projectile(x,y,"bullet.png",direction,scale));
                fire = 20;
            }
        }

    }
}
