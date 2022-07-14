package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Contents extends JPanel implements ActionListener {

    private Player gustafo = new Player(100,100,"gustafo fring.jpeg");
    private Timer t = new Timer(10,this);
    private KeyListener kl = new KeyListener();

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
//        g2d.drawImage(gustafo.getImage(),gustafo.getX(),gustafo.getY(),this);
        gustafo.draw(g2d,this);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(kl.isKeyW()){gustafo.addY(-1);}
        if(kl.isKeyS()){gustafo.addY(1);}
        if(kl.isKeyA()){gustafo.addX(-1);}
        if(kl.isKeyD()){gustafo.addX(1);}
        repaint();
    }

//    @Override
//    public void keyTyped(KeyEvent e) {
//
//    }
//
//    @Override
//    public void keyPressed(KeyEvent e) {
//        switch (e.getKeyCode()){
//            case KeyEvent.VK_W:
//                gustafo.addY(-5);
//                break;
//            case KeyEvent.VK_S:
//                gustafo.addY(5);
//                break;
//            case KeyEvent.VK_D:
//                gustafo.addX(5);
//                break;
//            case KeyEvent.VK_A:
//                gustafo.addX(-5);
//                break;
//        }
//    }
//
//    @Override
//    public void keyReleased(KeyEvent e) {
//
//    }
}
