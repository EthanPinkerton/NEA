package game;

import java.awt.*;
import java.awt.image.ImageObserver;

public class EscapeMenu {
    protected boolean paused;
    private int counter;

    public EscapeMenu() {
        paused = false;
        counter = 0;
    }

    public void draw(Graphics2D g2d, Grid grid) {
        g2d.setColor(Color.GRAY);
        g2d.fillRect(grid.getWidth() / 3, grid.getHeight() / 4, grid.getWidth() / 3, grid.getHeight() / 2);
        g2d.setColor(Color.BLACK);
        g2d.scale(2,2);
        g2d.drawRect(grid.getWidth() / 6, grid.getHeight() / 8, grid.getWidth() / 6, grid.getHeight() / 4);
        g2d.scale(2.5, 2.5);
        g2d.drawString("PAUSED", grid.getWidth() / 10 - 22, grid.getHeight() / 10 + 5);
    }

    public boolean isPaused() {
        return paused;
    }

    public void update(boolean keyEsc) {
        if (keyEsc) {
            if (paused && counter == 0) {
                paused = false;
                counter = 15;
            } else if (counter == 0) {
                paused = true;
                counter = 15;
            }
        } else if (counter > 0) {
            counter -= 1;
        }
    }
}
