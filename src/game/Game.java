package game;

import javax.swing.JFrame;

public class Game extends JFrame{
    public Game(){
        super.setTitle("Game");
        super.setSize(1280,720);
        super.setLocation(300,200);
        super.add(new Contents());
        //super.setResizable(false);
        super.setDefaultCloseOperation(EXIT_ON_CLOSE);
        super.setVisible(true);
    }

    public static void update(){

    }

    public static void draw(){

    }

    public static void main(String[] args){
        new Game();
        while(true){
            update();
            draw();
        }
    }
}
