package game;

import java.awt.event.KeyEvent;

public class KeyListener implements java.awt.event.KeyListener {

    protected boolean keyW, keyS, keyA, keyD, keyUp, keyDown, keyLeft, keyRight, keySpace, Key1, Key2, Key3, Key4;

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

    public boolean isKeyUp() {
        return keyUp;
    }

    public boolean isKeyDown() {
        return keyDown;
    }

    public boolean isKeyLeft() {
        return keyLeft;
    }

    public boolean isKeyRight() {
        return keyRight;
    }

    public boolean isKeySpace() {
        return keySpace;
    }

    public boolean isKey1() {
        return Key1;
    }

    public boolean isKey2() {
        return Key2;
    }

    public boolean isKey3() {
        return Key3;
    }

    public boolean isKey4() {
        return Key4;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) {
            keyW = true;
            return;
        }
        if (e.getKeyCode() == KeyEvent.VK_S) {
            keyS = true;
            return;
        }
        if (e.getKeyCode() == KeyEvent.VK_A) {
            keyA = true;
            return;
        }
        if (e.getKeyCode() == KeyEvent.VK_D) {
            keyD = true;
            return;
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            keyUp = true;
            return;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            keyDown = true;
            return;
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            keyLeft = true;
            return;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            keyRight = true;
            return;
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            keySpace = true;
            return;
        }
        if (e.getKeyCode() == KeyEvent.VK_1) {
            Key1 = true;
            return;
        }
        if (e.getKeyCode() == KeyEvent.VK_2) {
            Key2 = true;
            return;
        }
        if (e.getKeyCode() == KeyEvent.VK_3) {
            Key3 = true;
            return;
        }
        if (e.getKeyCode() == KeyEvent.VK_4) {
            Key4 = true;
            return;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) {
            keyW = false;
            return;
        }
        if (e.getKeyCode() == KeyEvent.VK_S) {
            keyS = false;
            return;
        }
        if (e.getKeyCode() == KeyEvent.VK_A) {
            keyA = false;
            return;
        }
        if (e.getKeyCode() == KeyEvent.VK_D) {
            keyD = false;
            return;
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            keyUp = false;
            return;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            keyDown = false;
            return;
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            keyLeft = false;
            return;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            keyRight = false;
            return;
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            keySpace = false;
            return;
        }
        if (e.getKeyCode() == KeyEvent.VK_1) {
            Key1 = false;
            return;
        }
        if (e.getKeyCode() == KeyEvent.VK_2) {
            Key2 = false;
            return;
        }
        if (e.getKeyCode() == KeyEvent.VK_3) {
            Key3 = false;
            return;
        }
        if (e.getKeyCode() == KeyEvent.VK_4) {
            Key4 = false;
            return;
        }
    }
}
