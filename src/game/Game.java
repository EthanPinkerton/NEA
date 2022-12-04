package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Game {

    public JFrame jFrame;
    public Contents contents = new Contents();

    public Game(JFrame jFrame, String user) {
        this.jFrame = jFrame;
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setLayout(null);
        jFrame.setTitle("Game");
        jFrame.setSize(Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height);
        jFrame.setLocation(0, 0);
        jFrame.setMinimumSize(new Dimension(800,600));
        jFrame.add(contents);
        contents.setBounds(0, 0, Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height);
        jFrame.setVisible(true);

        //Database.newGame(user, contents.getSeed());

        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                contents.update(jFrame.getHeight(), jFrame.getWidth());
                contents.repaint();
            }
        };
        Timer t = new Timer(17, actionListener);
        t.start();
    }

    public static void main(String[] args) {
        new Game(new JFrame(), "test");
    }
}
