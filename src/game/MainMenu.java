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

    protected ActionListener newGameListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            menu.removeComponents(jFrame);
            jFrame.repaint();
            game = new Game(jFrame, username, -1);
        }
    };

    protected ActionListener gameButton = new ActionListener() {
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

    protected ActionListener loadGameListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            menu.removeComponents(jFrame);
            jFrame.repaint();
            menu.loadGame(jFrame, username, gameButton);
        }
    };

    protected ActionListener leaderboardListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    };

    protected ActionListener loginListener = new ActionListener() {
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
                    menu = new Menu(jFrame, newGameListener, loadGameListener, leaderboardListener);
                    break;
            }
        }
    };

    protected ActionListener registerListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (register.getUsername().length() < 3) {
                displayError("Username must be more\n than 2 characters");
            } else if (register.getPassword().length() < 5) {
                displayError("Password must be longer\n than 5 characters");
            } else if (Database.addUser(register.getUsername(), register.getPassword()) == 1) {
                username = register.getUsername();
                register.removeComponents(jFrame);
                jFrame.repaint();
                menu = new Menu(jFrame, newGameListener, loadGameListener, leaderboardListener);
            } else {
                displayError("Username is not unique");
            }
        }
    };

    protected ActionListener loginRegisterListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            login.removeComponents(jFrame);
            register = new Register(jFrame, backListener, registerListener);
        }
    };

    protected ActionListener backListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            register.removeComponents(jFrame);
            login = new Login(jFrame, loginListener, loginRegisterListener);
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
        jFrame.setVisible(true);
        login = new Login(jFrame, loginListener, loginRegisterListener);
    }

    public static void main(String[] args) {
        new MainMenu();
    }

}
