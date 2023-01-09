package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Contents extends JPanel {

    private Grid grid;
    private Player player;
    private final KeyListener kl;
    private Maze maze;
    private final EscapeMenu escapeMenu;

    public Contents(JFrame jFrame, ActionListener quitButton) {
        kl = new KeyListener();
        escapeMenu = new EscapeMenu(jFrame, quitButton);
        super.setDoubleBuffered(true);
        super.addKeyListener(kl);
        super.setFocusable(true);
    }

    public void createVariables() {
        player = new Player(4.2, 4.2, 10, 0, "player.png");
        maze = new Maze(0, 0, "new");
        grid = new Grid(128, 4.2, 4.2);
    }

    public void setVariables(String seed, double x, double y, int score, double health) {
        player = new Player(x, y, health, score, "player.png");
        maze = new Maze(x, y, seed);
        grid = new Grid(128, x, y);
    }

    public String getSeed() {
        return maze.getSeed();
    }

    public double getPlayerX(){
        return player.getX();
    }

    public double getPlayerY(){
        return player.getY();
    }

    public int getScore() {
        return player.getScore();
    }

    public double getHealth() {
        return player.getHealth();
    }

    public void removeEscapeMenu(JFrame jFrame) {
        escapeMenu.removePanel(jFrame);
    }

    public void deathScreen(ActionListener menuButton) {
        escapeMenu.deathMenu(grid, menuButton);
    }

    @Override
    public void paint(Graphics g) {
        if (escapeMenu.isPaused()) {
            escapeMenu.draw(grid);
        } else {
            super.paint(g);
            Graphics2D g2d = (Graphics2D) g;
            maze.draw(g2d, this, grid);
            player.draw(g2d, this, grid);
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
