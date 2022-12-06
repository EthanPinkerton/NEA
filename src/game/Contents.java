package game;

import javax.swing.*;
import java.awt.*;

public class Contents extends JPanel {

    private final Grid grid = new Grid(128);
    private final Player player = new Player(4.2, 4.2, 10, "player.png");
    private final KeyListener kl = new KeyListener();
    private final Maze maze = new Maze(5, 5);
    private final EscapeMenu escapeMenu = new EscapeMenu();

    public Contents() {
        super.setDoubleBuffered(true);
        super.addKeyListener(kl);
        super.setFocusable(true);
    }

    public String getSeed() {
        return maze.getSeed();
    }

    public int getScore() {
        return player.getScore();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        if (player.getHealth() <= 0) {
            g2d.drawImage(GetResource.get("Message.png"), 700, 500, this);
            return;
        }
        maze.draw(g2d, this, grid);
        player.draw(g2d, this, grid);
        if (escapeMenu.isPaused()) {
            escapeMenu.draw(g2d, grid);
        }

    }

    public boolean update(int gameHeight, int gameWidth) {
        escapeMenu.update(kl.isKeyEsc());
        grid.update(gameHeight, gameWidth, player);
        if (!escapeMenu.isPaused()) {
            maze.update(player);
            player.update(kl, maze);
        }
        return (player.getHealth() <= 0);
    }
}
