package game;

import javax.swing.JFrame;

public class Game extends JFrame{

    public int width = 864;
    public int height = 896;

    public Game(){
        super.setTitle("Game");
        super.setSize(width,height);
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
