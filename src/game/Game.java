package game;

import javax.swing.JFrame;
import java.awt.Toolkit;

public class Game extends JFrame{

    public Game(){
        super.setTitle("Game");
        super.setSize(Toolkit.getDefaultToolkit().getScreenSize().width,Toolkit.getDefaultToolkit().getScreenSize().height);
        super.setLocation(0,0);
        super.add(new Contents());
        //super.setResizable(false);
        super.setDefaultCloseOperation(EXIT_ON_CLOSE);
        super.setVisible(true);
    }

    public static void main(String[] args){
        new Game();
    }
}
