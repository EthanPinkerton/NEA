package game;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class GetImage {
    public static Image get(String file){
        try {
            return new ImageIcon(Objects.requireNonNull(GetImage.class.getResource("res/"+file))).getImage();
        }catch (NullPointerException e){
            return new ImageIcon().getImage();
        }
    }
}
