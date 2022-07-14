package game;

import java.awt.event.KeyEvent;

public class KeyListener implements java.awt.event.KeyListener {
    protected boolean KeyW,KeyS,KeyA,KeyD;

    public boolean isKeyW() {
        return KeyW;
    }

    public boolean isKeyS() {
        return KeyS;
    }

    public boolean isKeyA() {
        return KeyA;
    }

    public boolean isKeyD() {
        return KeyD;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_W){
            KeyW = true;
        }
        if(e.getKeyCode() == KeyEvent.VK_S){
            KeyS = true;
        }
        if(e.getKeyCode() == KeyEvent.VK_A){
            KeyA = true;
        }
        if(e.getKeyCode() == KeyEvent.VK_D){
            KeyD = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_W){
            KeyW = false;
        }
        if(e.getKeyCode() == KeyEvent.VK_S){
            KeyS = false;
        }
        if(e.getKeyCode() == KeyEvent.VK_A){
            KeyA = false;
        }
        if(e.getKeyCode() == KeyEvent.VK_D){
            KeyD = false;
        }
    }
}
