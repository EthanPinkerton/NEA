package game;

import javax.swing.*;
import java.awt.event.ActionListener;

public class Menu {

    protected JButton newGame = new JButton("New Game");
    protected JButton loadGame = new JButton("Load game");
    protected JButton leaderboard = new JButton("Leaderboard");

    public Menu(JFrame jFrame, ActionListener ngAl, ActionListener lgAl, ActionListener lAl) {
        jFrame.setTitle("Main menu");
        jFrame.add(newGame);
        jFrame.add(loadGame);
        jFrame.add(leaderboard);
        format(jFrame);
        newGame.addActionListener(ngAl);
        loadGame.addActionListener(lgAl);
        leaderboard.addActionListener(lAl);
    }

    private void format(JFrame jFrame) {
        newGame.setBounds(jFrame.getWidth() / 2 - 100, jFrame.getHeight() / 2 - 125, 200, 50);
        loadGame.setBounds(jFrame.getWidth() / 2 - 100, jFrame.getHeight() / 2 - 25, 200, 50);
        leaderboard.setBounds(jFrame.getWidth() / 2 - 100, jFrame.getHeight() / 2 + 75, 200, 50);
    }

    public void removeComponents(JFrame jFrame) {
        jFrame.remove(newGame);
        jFrame.remove(loadGame);
        jFrame.remove(leaderboard);
    }
}
