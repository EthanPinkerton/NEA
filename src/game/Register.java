package game;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Register {

    private final JFrame jFrame;
    protected JTextField username = new JTextField();
    protected JPasswordField password = new JPasswordField();
    protected JButton back = new JButton("Back");
    protected JButton register = new JButton("Submit");
    protected JLabel ULabel = new JLabel("Enter Username:");
    protected JLabel PLabel = new JLabel("Enter Password:");

    protected ActionListener registerButton = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (getUsername().length() < 3 || getUsername().length() > 20) {
                GetResource.displayError(jFrame, "Invalid username length");
            } else if (getPassword().length() < 5) {
                GetResource.displayError(jFrame, "Password must be longer\n than 5 characters");
            } else if (Database.addUser(getUsername(), getPassword()) == 1) {
                String u = getUsername();
                removeComponents();
                jFrame.repaint();
                new Menu(jFrame, u);
            } else {
                GetResource.displayError(jFrame, "Username is not unique");
            }
        }
    };

    protected ActionListener backButton = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            removeComponents();
            new Login(jFrame);
        }
    };

    public Register(JFrame jFrame) {
        this.jFrame = jFrame;
        formatComponents();
    }

    private void formatComponents() {
        jFrame.setTitle("Register");
        jFrame.add(username);
        jFrame.add(back);
        jFrame.add(ULabel);
        jFrame.add(password);
        jFrame.add(PLabel);
        jFrame.add(register);
        username.setBounds(jFrame.getWidth() / 2 - 100, jFrame.getHeight() / 2 - 30, 200, 30);
        password.setBounds(jFrame.getWidth() / 2 - 100, jFrame.getHeight() / 2, 200, 30);
        back.setBounds(jFrame.getWidth() / 2 - 95, jFrame.getHeight() / 2 + 40, 90, 20);
        register.setBounds(jFrame.getWidth() / 2 + 5, jFrame.getHeight() / 2 + 40, 90, 20);
        ULabel.setBounds(jFrame.getWidth() / 2 - 200, jFrame.getHeight() / 2 - 30, 100, 30);
        PLabel.setBounds(jFrame.getWidth() / 2 - 200, jFrame.getHeight() / 2, 100, 30);
        back.addActionListener(backButton);
        register.addActionListener(registerButton);
        password.addActionListener(registerButton);
    }

    public void removeComponents() {
        jFrame.remove(username);
        jFrame.remove(password);
        jFrame.remove(back);
        jFrame.remove(register);
        jFrame.remove(ULabel);
        jFrame.remove(PLabel);
    }

    public String getUsername() {
        return username.getText();
    }

    public String getPassword() {
        return String.valueOf(password.getPassword());
    }

}
