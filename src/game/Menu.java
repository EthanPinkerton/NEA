package game;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu {

    protected JFrame jFrame;
    protected String username;
    protected JButton newGame = new JButton("New Game");
    protected JButton loadGame = new JButton("Load game");
    protected JButton leaderboardButton = new JButton("Leaderboard");
    protected JButton[] loadGameButtons;
    protected Leaderboard leaderboard;

    protected ActionListener newGameButton = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            removeComponents();
            jFrame.repaint();
            jFrame.setVisible(false);
            new Game(jFrame, username, -1);
        }
    };

    protected ActionListener loadGameMenuButton = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            removeComponents();
            jFrame.repaint();
            loadGame();
        }
    };

    protected ActionListener loadGameButton = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton button = getButton();
            if (button != null) {
                removeLeaderboardComponents();
                jFrame.repaint();
                new Game(jFrame, username, Integer.parseInt(button.getName().split("'")[1]));
            }
        }
    };

    protected ActionListener leaderboardMenuButton = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            removeComponents();
            jFrame.repaint();
            loadLeaderboard();
        }
    };

    protected ActionListener leaderboardBackButton = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            removeLeaderboardPanel();
            jFrame.repaint();
            formatComponents();
        }
    };

    public Menu(JFrame jFrame, String username) {
        this.username = username;
        this.jFrame = jFrame;
        formatComponents();
    }

    private void formatComponents() {
        jFrame.setTitle("Main menu");
        jFrame.add(newGame);
        jFrame.add(loadGame);
        jFrame.add(leaderboardButton);
        newGame.setBounds(jFrame.getWidth() / 2 - 100, jFrame.getHeight() / 2 - 125, 200, 50);
        loadGame.setBounds(jFrame.getWidth() / 2 - 100, jFrame.getHeight() / 2 - 25, 200, 50);
        leaderboardButton.setBounds(jFrame.getWidth() / 2 - 100, jFrame.getHeight() / 2 + 75, 200, 50);
        newGame.addActionListener(newGameButton);
        loadGame.addActionListener(loadGameMenuButton);
        leaderboardButton.addActionListener(leaderboardMenuButton);
    }

    public void loadGame() {
        String[] games = Database.loadGames(username);
        loadGameButtons = new JButton[games.length];
        for (int i = 0; i < loadGameButtons.length; i++) {
            String[] content = games[i].split("-");
            loadGameButtons[i] = new JButton(i + ". ID '" + content[0] + "' Seed '" + content[1] + "' Score '" + content[2] + "'");
            loadGameButtons[i].setBounds(jFrame.getWidth() / 2 - 200, 10 + 50 * i, 400, 40);
            jFrame.add(loadGameButtons[i]);
            loadGameButtons[i].addActionListener(loadGameButton);
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

    public void removeLeaderboardComponents() {
        for (JButton button : loadGameButtons) {
            jFrame.remove(button);
        }
    }

    public void removeLeaderboardPanel() {
        leaderboard.removePanel(jFrame);
    }

    public void loadLeaderboard() {
        leaderboard = new Leaderboard(jFrame, leaderboardBackButton);
    }

    public void removeComponents() {
        jFrame.remove(newGame);
        jFrame.remove(loadGame);
        jFrame.remove(leaderboardButton);
    }
}
