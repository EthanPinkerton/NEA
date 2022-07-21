package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Contents extends JPanel implements ActionListener {

    private Player player = new Player(100,100,"player.png");
    private Timer t = new Timer(16,this);
    private KeyListener kl = new KeyListener();
    private Maze maze = new Maze(3,3);

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
        maze.draw(g2d,this);
        player.draw(g2d,this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        player.collide();
        player.keyPress(kl);
        repaint();
    }
}
