package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class Game {

    public JFrame jFrame;
    public String username;
    public Contents contents;
    private final int GameID;
    private final ActionListener quitButton = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            t.stop();
            Database.updateGame(GameID, contents.getScore(), "TRUE", contents.getHealth(), contents.getPlayerX(), contents.getPlayerY());
            contents.removeEscapeMenu(jFrame);
            jFrame.remove(contents);
            jFrame.repaint();
            jFrame.setVisible(false);
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
                Database.updateGame(GameID, contents.getScore(), "FALSE", 0, contents.getPlayerX(), contents.getPlayerY());
            }
            contents.repaint();
        }
    };
    private final Timer t = new Timer(17, timerListener);

    public Game(JFrame frame, String user, int GameID) {
        this.jFrame = frame;
        this.username = user;
        //jFrame.setMinimumSize(new Dimension(800, 600));
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
            this.GameID = GameID;
            String[] gameStuffs = Database.getGameStuffs(this.GameID);
            System.out.println(Arrays.toString(gameStuffs));
            contents.setVariables(gameStuffs[0], Double.parseDouble(gameStuffs[3]), Double.parseDouble(gameStuffs[4]), Integer.parseInt(gameStuffs[1]), Double.parseDouble(gameStuffs[2]));
            jFrame.add(contents);
            contents.setBounds(0, 0, Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height);
        }

        t.start();
        jFrame.setVisible(true);
    }

    public static void main(String[] args) {
        new Game(new JFrame(), "test", -1);
    }
}
