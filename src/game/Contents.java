package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Contents extends JPanel implements ActionListener {

    private final Grid grid = new Grid(32);
    private final Player player = new Player(0,0,"player.png");
    private final Timer t = new Timer(17,this);
    private final KeyListener kl = new KeyListener();
    private final Maze maze = new Maze(3,3);

    public Contents(){
        super.setDoubleBuffered(true);
        super.addKeyListener(kl);
        super.setFocusable(true);
        t.start();
    }

    @Override
    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2d = (Graphics2D)g;
        maze.draw(g2d,this,grid);
        player.draw(g2d,this,grid);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        grid.update(player.getX(), player.getY());
        player.keyPress(kl);
        repaint();
    }
}
