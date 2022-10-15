package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Game extends JFrame{

    public Contents contents = new Contents();
    private final ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            contents.update(Game.super.getHeight(),Game.super.getWidth());
            contents.repaint();
        }
    };
    private final Timer t = new Timer(17,actionListener);

    public Game(){
        super.setTitle("Game");
        super.setSize(Toolkit.getDefaultToolkit().getScreenSize().width,Toolkit.getDefaultToolkit().getScreenSize().height);
        super.setLocation(0,0);
        super.setMinimumSize(new Dimension(500,500));
        super.add(contents);
        //super.setResizable(false);
        super.setDefaultCloseOperation(EXIT_ON_CLOSE);
        super.setVisible(true);
        t.start();
//        draw();
    }

//    public void draw(){
//        while(true) {
//            contents.repaint();
//        }
//    }

    public static void main(String[] args){
        new Game();
    }
}
