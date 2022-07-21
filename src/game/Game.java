package game;

import javax.swing.JFrame;

public class Game extends JFrame{
    public Game(){
        super.setTitle("Game");
        super.setSize(864,896);
        super.setLocation(100,100);
        super.add(new Contents());
        //super.setResizable(false);
        super.setDefaultCloseOperation(EXIT_ON_CLOSE);
        super.setVisible(true);
    }

    public static void main(String[] args){
        new Game();
    }
}
