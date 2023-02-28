package Main.Menus;

import Main.Game.GUI.RunGame;
import Main.Menus.Leaderboard;
import Main.Menus.LoadGame;
import Main.Menus.Login;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu {

    protected JFrame jFrame;
    protected String username;
    protected JButton newGame = new JButton("New Game");
    protected JButton loadGame = new JButton("Load game");
    protected JButton leaderboardButton = new JButton("Leaderboard");
    protected JButton logout = new JButton("Logout");
    protected LoadGame gameLoader;
    protected Leaderboard leaderboard;

    protected ActionListener logoutButton = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            removeComponents();
            jFrame.repaint();
            new Login(jFrame);
        }
    };

    protected ActionListener newGameButton = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            removeComponents();
            jFrame.repaint();
            jFrame.setVisible(false);
            new RunGame(jFrame, username, -1);
        }
    };

    protected ActionListener loadGameMenuButton = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            removeComponents();
            jFrame.repaint();
            gameLoader = new LoadGame(jFrame, username, loadGameButton, loadGameBackButton);
        }
    };

    protected ActionListener loadGameButton = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton button = (JButton) e.getSource();
            gameLoader.removePanel(jFrame);
            jFrame.repaint();
            jFrame.setVisible(false);
            int gameID = Integer.parseInt(button.getText().split(":")[1].split(" ")[0]);
            new RunGame(jFrame, username, gameID);
        }
    };

    protected ActionListener loadGameBackButton = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            gameLoader.removePanel(jFrame);
            jFrame.repaint();
            addComponents();
        }
    };

    protected ActionListener leaderboardMenuButton = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            removeComponents();
            jFrame.repaint();
            leaderboard = new Leaderboard(jFrame, leaderboardBackButton);
        }
    };

    protected ActionListener leaderboardBackButton = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            leaderboard.removePanel(jFrame);
            jFrame.repaint();
            addComponents();
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
        jFrame.add(logout);
        newGame.setBounds(jFrame.getWidth() / 2 - 100, jFrame.getHeight() / 2 - 125, 200, 50);
        loadGame.setBounds(jFrame.getWidth() / 2 - 100, jFrame.getHeight() / 2 - 25, 200, 50);
        leaderboardButton.setBounds(jFrame.getWidth() / 2 - 100, jFrame.getHeight() / 2 + 75, 200, 50);
        logout.setBounds(jFrame.getWidth() / 2 - 50, jFrame.getHeight() - 57, 100, 20);
        newGame.addActionListener(newGameButton);
        loadGame.addActionListener(loadGameMenuButton);
        leaderboardButton.addActionListener(leaderboardMenuButton);
        logout.addActionListener(logoutButton);
    }

    public void addComponents() {
        jFrame.add(newGame);
        jFrame.add(loadGame);
        jFrame.add(leaderboardButton);
        jFrame.add(logout);
    }

    public void removeComponents() {
        jFrame.remove(newGame);
        jFrame.remove(loadGame);
        jFrame.remove(leaderboardButton);
        jFrame.remove(logout);
    }
}
