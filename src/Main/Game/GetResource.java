package Main.Game;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public final class GetResource {
    public static Image getImage(String file) {
        try {
            return new ImageIcon(getFile(file)).getImage();
        } catch (NullPointerException e) {
            return new ImageIcon().getImage();
        }
    }

    public static String getFile(String file) {
        try {
            return Objects.requireNonNull(GetResource.class.getResource("res/" + file)).getPath().replaceAll("%20", " ");
        } catch (NullPointerException e) {
            return "";
        }
    }

    public static void displayError(JFrame jFrame, String error) {
        JOptionPane.showMessageDialog(jFrame, error);
    }

    public static JFrame formatJFrame(JFrame jFrame) {
        jFrame.setVisible(false);
        jFrame.setLayout(null);
        jFrame.setMinimumSize(new Dimension(500, 500));
        jFrame.setSize(500, 500);
        //jFrame.setLocation(Toolkit.getDefaultToolkit().getScreenSize().width / 2 - 250, Toolkit.getDefaultToolkit().getScreenSize().height / 2 - 250);
        jFrame.setLocation(200, 200);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setResizable(false);
        jFrame.setVisible(true);
        return jFrame;
    }
}
