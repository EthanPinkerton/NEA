package game;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Contents extends JPanel {
    public Contents(){
        super.setDoubleBuffered(true);
    }

    @Override
    public void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D)g;
        g2d.drawRect(50,200,200,400);
    }

}
