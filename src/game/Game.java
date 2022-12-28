package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Game {

    public JFrame jFrame;
    public String username;
    public Contents contents;
    private final int GameID;
    private final ActionListener quitButton = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            t.stop();
            Database.updateGame(GameID, contents.getScore(), "TRUE", contents.getHealth());
            contents.removeEscapeMenu(jFrame);
            jFrame.remove(contents);
            jFrame.repaint();
            new Menu(GetResource.formatJFrame(jFrame), username);
        }
    };
    private final ActionListener menuButton = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            contents.removeEscapeMenu(jFrame);
            jFrame.remove(contents);
            jFrame.repaint();
            new Menu(GetResource.formatJFrame(jFrame), username);
        }
    };
    private final ActionListener timerListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (contents.update(jFrame.getHeight(), jFrame.getWidth())) {
                contents.repaint();
                t.stop();
                contents.deathScreen(menuButton);
                Database.updateGame(GameID, contents.getScore(), "FALSE", 0);
            }
            contents.repaint();
        }
    };
    private final Timer t = new Timer(17, timerListener);

    public Game(JFrame frame, String user, int GameID) {
        this.jFrame = frame;
        this.username = user;
        jFrame.setMinimumSize(new Dimension(800, 600));
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setLayout(null);
        jFrame.setTitle("Game");
        jFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        jFrame.setLocation(0, 0);
        jFrame.setResizable(true);

        contents = new Contents(jFrame, quitButton);
        if (GameID == -1) {
            contents.createVariables();
            jFrame.add(contents);
            contents.setBounds(0, 0, Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height);

            this.GameID = Database.newGame(user, contents.getSeed());
        } else {
            contents.setVariables("newseed123", 100, 100, 5.5);// code for load game goes here
            jFrame.add(contents);
            contents.setBounds(0, 0, Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height);

            this.GameID = GameID;
        }

        t.start();
        jFrame.setVisible(true);
    }

    public static void main(String[] args) {
        new Game(new JFrame(), "test", -1);
    }
}
