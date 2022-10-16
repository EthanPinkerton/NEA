package game;

import javax.swing.*;

public class MainMenu {

    protected JTextField textField = new JTextField();
    protected JButton button = new JButton();
    protected JLabel label = new JLabel("Enter Username:");

    public MainMenu(JFrame jFrame){
        jFrame.add(textField);
        jFrame.add(button);
        jFrame.add(label);
        textField.setBounds(jFrame.getWidth()/2,jFrame.getHeight()/2,200,30);
        button.setBounds(jFrame.getWidth()/2,jFrame.getHeight()/2 + 100,100,20);
        label.setBounds(500,500,50,30);
        button.setText("Submit");
    }

}
