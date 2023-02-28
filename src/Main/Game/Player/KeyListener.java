package Main.Game.Player;

import java.awt.event.KeyEvent;

public class KeyListener implements java.awt.event.KeyListener {

    protected boolean keyW, keyS, keyA, keyD, keyUp, keyDown, keyLeft, keyRight, keySpace, key1, key2, key3, key4, keyEsc;

    public boolean isKeyEsc() {
        return keyEsc;
    }

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
        return key1;
    }

    public boolean isKey2() {
        return key2;
    }

    public boolean isKey3() {
        return key3;
    }

    public boolean isKey4() {
        return key4;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            keyEsc = true;
            return;
        }
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
            key1 = true;
            return;
        }
        if (e.getKeyCode() == KeyEvent.VK_2) {
            key2 = true;
            return;
        }
        if (e.getKeyCode() == KeyEvent.VK_3) {
            key3 = true;
            return;
        }
        if (e.getKeyCode() == KeyEvent.VK_4) {
            key4 = true;
            return;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            keyEsc = false;
            return;
        }
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
            key1 = false;
            return;
        }
        if (e.getKeyCode() == KeyEvent.VK_2) {
            key2 = false;
            return;
        }
        if (e.getKeyCode() == KeyEvent.VK_3) {
            key3 = false;
            return;
        }
        if (e.getKeyCode() == KeyEvent.VK_4) {
            key4 = false;
            return;
        }
    }
}
