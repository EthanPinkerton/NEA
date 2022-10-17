package game;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class MainMenu {

    protected JFrame jFrame;
    protected Login login;
    protected Register register;

    protected ActionListener loginListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    };

    protected ActionListener registerListener2 = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

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

    public MainMenu(JFrame jFrame){
        this.jFrame = jFrame;
        login = new Login(jFrame,loginListener,registerListener);
    }

}
