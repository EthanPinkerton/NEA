package Main.Game.Player;

import Main.Game.Maze.Maze;
import Main.Game.GetResource;

import java.awt.*;
import java.awt.image.ImageObserver;

public class Inventory {

    private byte keyPress;
    protected int bombs, teleport, speed, bullets;
    private int sCount, bCount;
    private final Image imageB, imageT, imageS, imageP;

    public Inventory() {
        bombs = 1;
        teleport = 1;
        speed = 1;
        bullets = 1;
        imageB = GetResource.getImage("bomb.jpg");
        imageT = GetResource.getImage("teleport.jpeg");
        imageS = GetResource.getImage("speed.jpg");
        imageP = GetResource.getImage("bullet.jpg");
        keyPress = 0;
    }

    public void update(KeyListener kl, Maze maze, Player player) {
        if (keyPress > 0) {
            keyPress -= 1;
        }
        if (sCount > 0) {
            sCount -= 1;
            if (sCount == 0) {
                UseItem.sReturn(player);
            }
        }
        if (bCount > 0) {
            bCount -= 1;
            if (bCount == 0) {
                UseItem.pReturn(player);
            }
        }
        keyPress(kl, maze, player);
    }

    public void keyPress(KeyListener kl, Maze maze, Player player) {
        if (kl.isKey1() && bombs > 0 && keyPress == 0) {
            bombs -= 1;
            UseItem.bomb(player, maze);
            keyPress = 10;
        }
        if (kl.isKey2() && teleport > 0 && keyPress == 0) {
            teleport -= 1;
            UseItem.teleport(player, maze);
            keyPress = 10;
        }
        if (kl.isKey3() && speed > 0 && sCount == 0) {
            speed -= 1;
            UseItem.speed(player);
            sCount = 100;
        }
        if (kl.isKey4() && bullets > 0 && bCount == 0) {
            bullets -= 1;
            UseItem.projectile(player);
            bCount = 100;
        }
    }

    public void draw(Graphics2D g2d, ImageObserver IO) {
        g2d.drawImage(imageB, 10, 90, 80, 80, IO);
        g2d.drawString(Integer.toString(bombs), 10, 160);
        g2d.drawImage(imageT, 10, 170, 80, 80, IO);
        g2d.drawString(Integer.toString(teleport), 10, 240);
        g2d.drawImage(imageS, 10, 250, 80, 80, IO);
        g2d.drawString(Integer.toString(speed), 10, 320);
        g2d.drawImage(imageP, 10, 330, 80, 80, IO);
        g2d.drawString(Integer.toString(bullets), 10, 400);
    }

    public void addItem(char type) {
        switch (type) {
            case 'b':
                bombs++;
                break;
            case 't':
                teleport++;
                break;
            case 's':
                speed++;
                break;
            case 'p':
                bullets++;
                break;
        }
    }
}
