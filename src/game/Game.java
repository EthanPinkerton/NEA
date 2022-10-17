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
        super.setLayout(null);
        super.setSize(500,500);
        super.setLocation(Toolkit.getDefaultToolkit().getScreenSize().width/2 -250,Toolkit.getDefaultToolkit().getScreenSize().height/2 - 250);
        super.setMinimumSize(new Dimension(500,500));
        super.setDefaultCloseOperation(EXIT_ON_CLOSE);
        super.setVisible(true);
        MainMenu mainMenu = new MainMenu(this);

        //super.setSize(Toolkit.getDefaultToolkit().getScreenSize().width,Toolkit.getDefaultToolkit().getScreenSize().height);
        //super.add(contents);
        //super.setLocation(0,0);
        //t.start();
    }

    public static void main(String[] args){
        new Game();
    }
}
