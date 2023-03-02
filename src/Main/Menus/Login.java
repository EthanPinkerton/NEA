package Main.Menus;

import Main.Database;
import Main.Game.GetResource;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login {

    protected JFrame jFrame;
    protected JTextField username = new JTextField();
    protected JPasswordField password = new JPasswordField();
    protected JButton login = new JButton("Submit");
    protected JButton register = new JButton("Register");
    protected JLabel ULabel = new JLabel("Enter Username:");
    protected JLabel PLabel = new JLabel("Enter Password:");
    private JLabel loginLabel = new JLabel("Login");

    protected ActionListener loginButton = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            switch (Database.loginUser(getUsername(), getPassword())) {
                case 0:
                    GetResource.displayError(jFrame, "Username not found");
                    break;
                case 1:
                    GetResource.displayError(jFrame, "Password doesn't match");
                    break;
                case 2:
                    String u = getUsername();
                    removeComponents();
                    jFrame.repaint();
                    new Menu(jFrame, u);
                    break;
            }
        }
    };

    protected ActionListener registerButton = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            removeComponents();
            jFrame.repaint();
            new Register(jFrame);
        }
    };

    public Login(JFrame jFrame) {
        this.jFrame = jFrame;
        jFrame.setTitle("Login");
        jFrame.add(username);
        jFrame.add(login);
        jFrame.add(ULabel);
        jFrame.add(password);
        jFrame.add(PLabel);
        jFrame.add(register);
        jFrame.add(loginLabel);
        formatComponents();
        loginLabel.setFont(new Font("",Font.PLAIN,30));
        login.addActionListener(loginButton);
        register.addActionListener(registerButton);
        password.addActionListener(loginButton);
    }

    private void formatComponents() {
        username.setBounds(jFrame.getWidth() / 2 - 70, jFrame.getHeight() / 2 - 30, 200, 30);
        password.setBounds(jFrame.getWidth() / 2 - 70, jFrame.getHeight() / 2, 200, 30);
        login.setBounds(jFrame.getWidth() / 2 + 5, jFrame.getHeight() / 2 + 40, 100, 20);
        register.setBounds(jFrame.getWidth() / 2 - 105, jFrame.getHeight() / 2 + 40, 100, 20);
        ULabel.setBounds(jFrame.getWidth() / 2 - 200, jFrame.getHeight() / 2 - 30, 130, 30);
        PLabel.setBounds(jFrame.getWidth() / 2 - 200, jFrame.getHeight() / 2, 130, 30);
        loginLabel.setBounds(jFrame.getWidth() / 2 - 50,jFrame.getHeight() / 2 - 150,100,35);
    }

    public void removeComponents() {
        jFrame.remove(username);
        jFrame.remove(password);
        jFrame.remove(login);
        jFrame.remove(register);
        jFrame.remove(ULabel);
        jFrame.remove(PLabel);
        jFrame.remove(loginLabel);
    }

    public String getUsername() {
        return username.getText();
    }

    public String getPassword() {
        return String.valueOf(password.getPassword());
    }

    public static void main(String[] args) {
        new Login(GetResource.formatJFrame(new JFrame()));
        Database.connect();
    }
}
