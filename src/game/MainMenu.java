package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu {

    protected Game game;

    public static void displayError(JFrame jFrame, String error) {
        JOptionPane.showMessageDialog(jFrame, error);
    }

    public MainMenu() {
        JFrame jFrame = new JFrame();
        jFrame.setLayout(null);
        jFrame.setSize(500, 500);
        jFrame.setLocation(Toolkit.getDefaultToolkit().getScreenSize().width / 2 - 250, Toolkit.getDefaultToolkit().getScreenSize().height / 2 - 250);
        jFrame.setMinimumSize(new Dimension(500, 500));
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setResizable(false);
        jFrame.setVisible(true);
        Database.connect();
        new Login(jFrame);
    }

    public static void main(String[] args) {
        new MainMenu();
    }

}
