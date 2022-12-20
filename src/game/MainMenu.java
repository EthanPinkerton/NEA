package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu {

    protected String username;
    protected JFrame jFrame;
    protected Login login;
    protected Register register;
    protected Menu menu;
    protected Game game;

    protected ActionListener newGameMenuButton = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            menu.removeComponents(jFrame);
            jFrame.repaint();
            jFrame.setVisible(false);
            game = new Game(jFrame, username, -1);
        }
    };

    protected ActionListener loadGameButton = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton button = menu.getButton();
            if (button != null) {
                menu.removeLeaderboardComponents(jFrame);
                jFrame.repaint();
                game = new Game(jFrame, username, Integer.parseInt(button.getName().split("'")[1]));
            }
        }
    };

    protected ActionListener loadGameMenuButton = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            menu.removeComponents(jFrame);
            jFrame.repaint();
            menu.loadGame(jFrame, username, loadGameButton);
        }
    };

    protected ActionListener leaderboardMenuButton = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            menu.removeComponents(jFrame);
            jFrame.repaint();
            menu.loadLeaderboard(jFrame, leaderboardBackButton);
        }
    };

    protected ActionListener leaderboardBackButton = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            menu.removeLeaderboardPanel(jFrame);
            jFrame.repaint();
            menu = new Menu(jFrame, newGameMenuButton, loadGameMenuButton, leaderboardMenuButton);
        }
    };

    protected ActionListener loginButton = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            switch (Database.loginUser(login.getUsername(), login.getPassword())) {
                case 0:
                    displayError("Username not found");
                    break;
                case 1:
                    displayError("Password doesn't match");
                    break;
                case 2:
                    username = login.getUsername();
                    login.removeComponents(jFrame);
                    jFrame.repaint();
                    menu = new Menu(jFrame, newGameMenuButton, loadGameMenuButton, leaderboardMenuButton);
                    break;
            }
        }
    };

    protected ActionListener registerButton = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (register.getUsername().length() < 3 || register.getUsername().length() > 20) {
                displayError("Invalid username length");
            } else if (register.getPassword().length() < 5) {
                displayError("Password must be longer\n than 5 characters");
            } else if (Database.addUser(register.getUsername(), register.getPassword()) == 1) {
                username = register.getUsername();
                register.removeComponents(jFrame);
                jFrame.repaint();
                menu = new Menu(jFrame, newGameMenuButton, loadGameMenuButton, leaderboardMenuButton);
            } else {
                displayError("Username is not unique");
            }
        }
    };

    protected ActionListener loginMenuRegisterButton = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            login.removeComponents(jFrame);
            register = new Register(jFrame, registerMenuBackButton, registerButton);
        }
    };

    protected ActionListener registerMenuBackButton = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            register.removeComponents(jFrame);
            login = new Login(jFrame, loginButton, loginMenuRegisterButton);
        }
    };

    public void displayError(String error) {
        JOptionPane.showMessageDialog(jFrame,error);
    }

    public MainMenu() {
        jFrame = new JFrame();
        jFrame.setLayout(null);
        jFrame.setSize(500, 500);
        jFrame.setLocation(Toolkit.getDefaultToolkit().getScreenSize().width / 2 - 250, Toolkit.getDefaultToolkit().getScreenSize().height / 2 - 250);
        jFrame.setMinimumSize(new Dimension(500, 500));
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setResizable(false);
        jFrame.setVisible(true);
        Database.connect();
        login = new Login(jFrame, loginButton, loginMenuRegisterButton);
    }

    public static void main(String[] args) {
        new MainMenu();
    }

}
