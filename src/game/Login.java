package game;

import javax.swing.*;
import java.awt.event.ActionListener;

public class Login {
    protected JTextField username = new JTextField();
    protected JPasswordField password = new JPasswordField();
    protected JButton login = new JButton("Submit");
    protected JButton register = new JButton("Register");
    protected JLabel ULabel = new JLabel("Enter Username:");
    protected JLabel PLabel = new JLabel("Enter Password:");

    public Login(JFrame jFrame, ActionListener lAl, ActionListener rAl){
        jFrame.setTitle("Login");
        jFrame.add(username);
        jFrame.add(login);
        jFrame.add(ULabel);
        jFrame.add(password);
        jFrame.add(PLabel);
        jFrame.add(register);
        format(jFrame);
        login.addActionListener(lAl);
        register.addActionListener(rAl);
    }

    private void format(JFrame jFrame){
        username.setBounds(jFrame.getWidth()/2 - 100,jFrame.getHeight()/2 - 30,200,30);
        password.setBounds(jFrame.getWidth()/2 - 100,jFrame.getHeight()/2,200,30);
        login.setBounds(jFrame.getWidth()/2 + 5,jFrame.getHeight()/2 + 40,90,20);
        register.setBounds(jFrame.getWidth()/2 - 95,jFrame.getHeight()/2 + 40,90,20);
        ULabel.setBounds(jFrame.getWidth()/2 - 200,jFrame.getHeight()/2 - 30,100,30);
        PLabel.setBounds(jFrame.getWidth()/2 - 200,jFrame.getHeight()/2,100,30);
    }

    public void removeComponents(JFrame jFrame){
        jFrame.remove(username);
        jFrame.remove(password);
        jFrame.remove(login);
        jFrame.remove(register);
        jFrame.remove(ULabel);
        jFrame.remove(PLabel);
    }

    public String getUsername(){
        return username.getText();
    }

    public String getPassword(){
        return String.valueOf(password.getPassword());
    }
}
