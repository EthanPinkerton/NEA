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
        jFrame.setTitle("Game");
        jFrame.setSize(Toolkit.getDefaultToolkit().getScreenSize().width,Toolkit.getDefaultToolkit().getScreenSize().height);
        jFrame.setLocation(0,0);
        jFrame.add(contents);
        this.jFrame = jFrame;
        t.start();
    }

    public static void main(String[] args){
        new Game(new JFrame());
    }
}
