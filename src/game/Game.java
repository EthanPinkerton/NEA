package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Game{

    public JFrame jFrame;
    public Contents contents = new Contents();
    private final ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            contents.update(jFrame.getHeight(),jFrame.getWidth());
            contents.repaint();
        }
    };
    private final Timer t = new Timer(17,actionListener);

    public Game(JFrame jFrame){
        this.jFrame = jFrame;

//        jFrame.setLayout(null);
//        jFrame.setSize(500,500);
//        jFrame.setLocation(Toolkit.getDefaultToolkit().getScreenSize().width/2 -250,Toolkit.getDefaultToolkit().getScreenSize().height/2 - 250);
//        jFrame.setMinimumSize(new Dimension(500,500));
//        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        jFrame.setVisible(true);

        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setLayout(null);
        jFrame.setTitle("Game");
        jFrame.setSize(Toolkit.getDefaultToolkit().getScreenSize().width,Toolkit.getDefaultToolkit().getScreenSize().height);
        jFrame.setLocation(0,0);
        jFrame.add(contents);
        contents.setBounds(0,0,Toolkit.getDefaultToolkit().getScreenSize().width,Toolkit.getDefaultToolkit().getScreenSize().height);
        jFrame.setVisible(true);
        t.start();
    }

    public static void main(String[] args){
        new Game(new JFrame());
    }
}
