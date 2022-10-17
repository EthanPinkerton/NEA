package game;

import javax.swing.*;
import java.awt.event.ActionListener;

public class Register {

    protected JTextField username = new JTextField();
    protected JPasswordField password = new JPasswordField();
    protected JButton back = new JButton("Back");
    protected JButton register = new JButton("Submit");
    protected JLabel ULabel = new JLabel("Enter Username:");
    protected JLabel PLabel = new JLabel("Enter Password:");

    public Register(JFrame jFrame, ActionListener bAl, ActionListener rAl){
        jFrame.setTitle("Register");
        jFrame.add(username);
        jFrame.add(back);
        jFrame.add(ULabel);
        jFrame.add(password);
        jFrame.add(PLabel);
        jFrame.add(register);
        format(jFrame);
        back.addActionListener(bAl);
        register.addActionListener(rAl);
    }

    private void format(JFrame jFrame){
        username.setBounds(jFrame.getWidth()/2 - 100,jFrame.getHeight()/2 - 30,200,30);
        password.setBounds(jFrame.getWidth()/2 - 100,jFrame.getHeight()/2,200,30);
        back.setBounds(jFrame.getWidth()/2 - 95,jFrame.getHeight()/2 + 40,90,20);
        register.setBounds(jFrame.getWidth()/2 + 5,jFrame.getHeight()/2 + 40,90,20);
        ULabel.setBounds(jFrame.getWidth()/2 - 200,jFrame.getHeight()/2 - 30,100,30);
        PLabel.setBounds(jFrame.getWidth()/2 - 200,jFrame.getHeight()/2,100,30);
    }

    public void removeComponents(JFrame jFrame){
        jFrame.remove(username);
        jFrame.remove(password);
        jFrame.remove(back);
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
