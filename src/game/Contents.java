package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Contents extends JPanel implements ActionListener {

    private final Grid grid = new Grid(96);
    private final Player player = new Player(4.2,4.2,10,"player.png");
    private final Timer t = new Timer(17,this);
    private final KeyListener kl = new KeyListener();
    private final Maze maze = new Maze(5,5);

    public Contents(){
        super.setDoubleBuffered(true);
        super.addKeyListener(kl);
        super.setFocusable(true);
        t.start();
    }

    @Override
    public void paint(Graphics g){
        //super.paint(g);
        Graphics2D g2d = (Graphics2D)g;
        if(player.getHealth() <= 0){
            g2d.drawImage(GetImage.get("Message.png"),700,500,this);
            t.stop();
            return;
        }
        maze.draw(g2d,this,grid);
        player.draw(g2d,this,grid);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        maze.update(player, maze);
        grid.update(player.getX(), player.getY());
        player.update(kl,maze);
        repaint();
    }
}
