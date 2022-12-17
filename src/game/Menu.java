package game;

import javax.swing.*;
import java.awt.event.ActionListener;

public class Menu {

    protected JButton newGame = new JButton("New Game");
    protected JButton loadGame = new JButton("Load game");
    protected JButton leaderboardButton = new JButton("Leaderboard");
    protected JButton[] loadGameButtons;
    protected Leaderboard leaderboard;

    public Menu(JFrame jFrame, ActionListener ngAl, ActionListener lgAl, ActionListener lAl) {
        jFrame.setTitle("Main menu");
        jFrame.add(newGame);
        jFrame.add(loadGame);
        jFrame.add(leaderboardButton);
        format(jFrame);
        newGame.addActionListener(ngAl);
        loadGame.addActionListener(lgAl);
        leaderboardButton.addActionListener(lAl);
    }

    private void format(JFrame jFrame) {
        newGame.setBounds(jFrame.getWidth() / 2 - 100, jFrame.getHeight() / 2 - 125, 200, 50);
        loadGame.setBounds(jFrame.getWidth() / 2 - 100, jFrame.getHeight() / 2 - 25, 200, 50);
        leaderboardButton.setBounds(jFrame.getWidth() / 2 - 100, jFrame.getHeight() / 2 + 75, 200, 50);
    }

    public void loadGame(JFrame jFrame, String username, ActionListener al) {
        String[] games = Database.loadGames(username);
        loadGameButtons = new JButton[games.length];
        for (int i = 0; i < loadGameButtons.length; i++) {
            String[] content = games[i].split("-");
            loadGameButtons[i] = new JButton(i + ". ID '" + content[0] + "' Seed '" + content[1] + "' Score '" + content[2] + "'");
            loadGameButtons[i].setBounds(jFrame.getWidth() / 2 - 200, 10 + 50 * i, 400, 40);
            jFrame.add(loadGameButtons[i]);
            loadGameButtons[i].addActionListener(al);
        }
    }

    public JButton getButton() {
        for (JButton button : loadGameButtons) {
            if (button.isSelected()) {
                return button;
            }
        }
        return null;
    }

    public void removeLeaderboardComponents(JFrame jFrame) {
        for (JButton button : loadGameButtons) {
            jFrame.remove(button);
        }
    }

    public void loadLeaderboard(JFrame jFrame) {
        leaderboard = new Leaderboard(jFrame);
    }

    public void removeComponents(JFrame jFrame) {
        jFrame.remove(newGame);
        jFrame.remove(loadGame);
        jFrame.remove(leaderboardButton);
    }
}
