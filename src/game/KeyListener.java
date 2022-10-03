package game;

import java.awt.event.KeyEvent;

public class KeyListener implements java.awt.event.KeyListener {

    protected boolean keyW,keyS,keyA,keyD,keyUp,keyDown,keyLeft,keyRight,keySpace;

    public boolean isKeyW() {
        return keyW;
    }

    public boolean isKeyS() {
        return keyS;
    }

    public boolean isKeyA() {
        return keyA;
    }

    public boolean isKeyD() {
        return keyD;
    }

    public boolean isKeyUp(){
        return keyUp;
    }

    public boolean isKeyDown(){
        return keyDown;
    }

    public boolean isKeyLeft(){
        return keyLeft;
    }

    public boolean isKeyRight(){
        return keyRight;
    }

    public boolean isKeySpace() {
        return keySpace;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_W){
            keyW = true;
            return;
        }
        if(e.getKeyCode() == KeyEvent.VK_S){
            keyS = true;
            return;
        }
        if(e.getKeyCode() == KeyEvent.VK_A){
            keyA = true;
            return;
        }
        if(e.getKeyCode() == KeyEvent.VK_D){
            keyD = true;
            return;
        }
        if(e.getKeyCode() == KeyEvent.VK_UP){
            keyUp = true;
            return;
        }
        if(e.getKeyCode() == KeyEvent.VK_DOWN){
            keyDown = true;
            return;
        }
        if(e.getKeyCode() == KeyEvent.VK_LEFT){
            keyLeft = true;
            return;
        }
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            keyRight = true;
            return;
        }
        if(e.getKeyCode() == KeyEvent.VK_SPACE){
            keySpace = true;
            return;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_W){
            keyW = false;
            return;
        }
        if(e.getKeyCode() == KeyEvent.VK_S){
            keyS = false;
            return;
        }
        if(e.getKeyCode() == KeyEvent.VK_A){
            keyA = false;
            return;
        }
        if(e.getKeyCode() == KeyEvent.VK_D){
            keyD = false;
            return;
        }
        if(e.getKeyCode() == KeyEvent.VK_UP){
            keyUp = false;
            return;
        }
        if(e.getKeyCode() == KeyEvent.VK_DOWN){
            keyDown = false;
            return;
        }
        if(e.getKeyCode() == KeyEvent.VK_LEFT){
            keyLeft = false;
            return;
        }
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            keyRight = false;
            return;
        }
        if(e.getKeyCode() == KeyEvent.VK_SPACE){
            keySpace = false;
            return;
        }
    }
}
