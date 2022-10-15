package game;

import javax.swing.*;
import java.awt.*;

public class Contents extends JPanel{

    private final Grid grid = new Grid(96);
    private final Player player = new Player(4.2,4.2,10,"player.png");
    private final KeyListener kl = new KeyListener();
    private final Maze maze = new Maze(5,5);

    public Contents(){
        super.setDoubleBuffered(true);
        super.addKeyListener(kl);
        super.setFocusable(true);
    }

    @Override
    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2d = (Graphics2D)g;
        if(player.getHealth() <= 0){
            g2d.drawImage(GetImage.get("Message.png"),700,500,this);
            return;
        }
        maze.draw(g2d,this,grid);
        player.draw(g2d,this,grid);
    }

    public void update(int gameHeight, int gameWidth) {
        maze.update(player);
        grid.update(gameHeight,gameWidth,player);
        player.update(kl,maze);
    }
}
