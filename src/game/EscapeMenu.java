package game;

import javax.swing.*;
import java.awt.*;

public class EscapeMenu {
    private final JPanel panel;
    protected boolean paused;
    private int counter;
    private final JButton backButton;
    private final JLabel label;

    public EscapeMenu(JPanel mainPanel) {
        panel = new JPanel();
        panel.setVisible(false);
        panel.setBackground(Color.GRAY);
        mainPanel.add(panel);
        backButton = new JButton("Quit");
        panel.add(backButton);
        label = new JLabel("Paused");
        panel.add(label);

        paused = false;
        counter = 0;
    }

    public void draw(Grid grid) {
//        g2d.setColor(Color.GRAY);
//        g2d.fillRect(grid.getWidth() / 3, grid.getHeight() / 4, grid.getWidth() / 3, grid.getHeight() / 2);
//        g2d.setColor(Color.BLACK);
//        g2d.scale(2, 2);
//        g2d.drawRect(grid.getWidth() / 6, grid.getHeight() / 8, grid.getWidth() / 6, grid.getHeight() / 4);
//        g2d.scale(2.5, 2.5);
//        g2d.drawString("PAUSED", grid.getWidth() / 10 - 22, grid.getHeight() / 10 + 5);
        panel.setBounds(grid.getWidth() / 3, grid.getHeight() / 4, grid.getWidth() / 3, grid.getHeight() / 2);
        label.setBounds(panel.getWidth() / 2 - 50, panel.getHeight() / 2, 100, 20);
        backButton.setBounds(panel.getWidth() / 2 - 20, panel.getHeight() / 2 + 80, panel.getWidth() / 10, panel.getHeight() / 10);
    }

    public boolean isPaused() {
        return paused;
    }

    public void update(boolean keyEsc) {
        panel.setVisible(paused);
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
