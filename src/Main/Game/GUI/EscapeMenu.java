package Main.Game.GUI;

import Main.Game.GUI.Grid;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EscapeMenu {
    private final JPanel panel;
    protected boolean paused;
    private int counter;
    private final JButton backButton;
    private final JButton resumeButton;
    private final JLabel label;
    private final ActionListener resume = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            paused = false;
            counter = 0;
        }
    };

    public EscapeMenu(JFrame jFrame, ActionListener quitButton) {
        panel = new JPanel();
        panel.setVisible(false);
        panel.setLayout(null);
        panel.setBackground(Color.GRAY);
        jFrame.add(panel);

        backButton = new JButton("Save and Exit");
        backButton.addActionListener(quitButton);
        panel.add(backButton);

        resumeButton = new JButton("Resume");
        resumeButton.addActionListener(resume);
        panel.add(resumeButton);

        label = new JLabel("Paused");
        label.setFont(new Font("", Font.PLAIN, 30));
        panel.add(label);

        paused = false;
        counter = 0;
    }

    public void draw(Grid grid) {
        panel.setBounds(2 * grid.getWidth() / 5, grid.getHeight() / 3, grid.getWidth() / 5, grid.getHeight() / 3);
        label.setBounds(panel.getWidth() / 2 - 60, 10, 200, 50);
        resumeButton.setBounds(panel.getWidth() / 4, 2 * panel.getHeight() / 7, panel.getWidth() / 2, panel.getHeight() / 5);
        backButton.setBounds(panel.getWidth() / 4, 3 * panel.getHeight() / 5, panel.getWidth() / 2, panel.getHeight() / 5);
    }

    public void deathMenu(Grid grid, ActionListener menuButton) {
        label.setText("You died");
        backButton.setVisible(false);
        resumeButton.setText("Exit");
        resumeButton.removeActionListener(resume);
        resumeButton.addActionListener(menuButton);
        panel.repaint();
        panel.setBounds(2 * grid.getWidth() / 5, grid.getHeight() / 3, grid.getWidth() / 5, grid.getHeight() / 3);
        label.setBounds(panel.getWidth() / 2 - 60, 10, 200, 50);
        resumeButton.setBounds(panel.getWidth() / 4, panel.getHeight() / 3, panel.getWidth() / 2, panel.getHeight() / 5);
        panel.setVisible(true);
    }

    public void removePanel(JFrame jFrame) {
        jFrame.remove(panel);
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
