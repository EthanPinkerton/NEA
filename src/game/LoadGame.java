package game;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

public class LoadGame {
    protected JPanel panel;
    private final JButton back;
    private final JScrollBar scrollBar;
    private int lastValue;
    private final JButton[] loadGameButtons;
    private final AdjustmentListener adjustmentListener = new AdjustmentListener() {
        @Override
        public void adjustmentValueChanged(AdjustmentEvent e) {
            for (JButton button : loadGameButtons) {
                button.setLocation(button.getX(), button.getY() + (lastValue - scrollBar.getValue()) * 10);
            }
            back.setLocation(back.getX(), back.getY() + (lastValue - scrollBar.getValue()) * 10);
            panel.repaint();
            lastValue = scrollBar.getValue();
        }
    };

    public LoadGame(JFrame jFrame, String username, ActionListener loadGameButton, ActionListener backButton) {
        panel = new JPanel();
        panel.setLayout(null);
        //panel.setBounds(0, 0, jFrame.getWidth(), jFrame.getHeight());
        panel.setBounds(0,0,500,500);
        panel.setVisible(true);
        String[] games = Database.loadGames(username);
        loadGameButtons = new JButton[games.length];
        for (int i = 0; i < loadGameButtons.length; i++) {
            String[] content = games[i].split("-");
            loadGameButtons[i] = new JButton(i + ". ID:" + content[0] + " Seed:" + content[1] + " Score:" + content[2]);
            loadGameButtons[i].setBounds(panel.getWidth() / 2 - 200, 30 + 50 * i, 400, 40);
            panel.add(loadGameButtons[i]);
            loadGameButtons[i].addActionListener(loadGameButton);
        }

        int max = Math.max(loadGameButtons[loadGameButtons.length - 1].getY() + 60 - 464, 10) / 10;

        scrollBar = new JScrollBar(JScrollBar.VERTICAL, 0, 1, 0, max);
        //scrollBar.setBounds(jFrame.getWidth() - 15, 0, 18, jFrame.getHeight() - 36);
        scrollBar.setBounds(480,0,18,500 - 36);
        scrollBar.addAdjustmentListener(adjustmentListener);
        panel.add(scrollBar);
        scrollBar.setVisible(true);
        scrollBar.repaint();
        lastValue = 0;

        back = new JButton("Back");
        back.setBounds(50, 5, 100, 20);
        back.addActionListener(backButton);
        panel.add(back);

        jFrame.add(panel);
    }


    public void removePanel(JFrame jFrame) {
        jFrame.remove(panel);
    }
}
