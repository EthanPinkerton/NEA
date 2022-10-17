package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu {

    protected JFrame jFrame;
    protected Login login;
    protected Register register;
    protected Menu menu;
    protected Game game;

    protected ActionListener newGameListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            menu.removeComponents(jFrame);
            jFrame.repaint();
            game = new Game(jFrame);

        }
    };

    protected ActionListener loadGameListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    };

    protected ActionListener leaderboardListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    };

    protected ActionListener loginListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            login.removeComponents(jFrame);
            jFrame.repaint();
            menu = new Menu(jFrame,newGameListener,loadGameListener,leaderboardListener);
        }
    };

    protected ActionListener registerListener2 = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            register.removeComponents(jFrame);
            jFrame.repaint();
            menu = new Menu(jFrame,newGameListener,loadGameListener,leaderboardListener);
        }
    };

    protected ActionListener registerListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            login.removeComponents(jFrame);
            register = new Register(jFrame,backListener,registerListener2);
        }
    };

    protected ActionListener backListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            register.removeComponents(jFrame);
            login = new Login(jFrame,loginListener,registerListener);
        }
    };

    public MainMenu(){
        jFrame = new JFrame();
        jFrame.setLayout(null);
        jFrame.setSize(500,500);
        jFrame.setLocation(Toolkit.getDefaultToolkit().getScreenSize().width/2 -250,Toolkit.getDefaultToolkit().getScreenSize().height/2 - 250);
        jFrame.setMinimumSize(new Dimension(500,500));
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
        login = new Login(jFrame,loginListener,registerListener);
    }

    public static void main(String[] args){
        new MainMenu();
    }

}
