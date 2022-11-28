package game;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class GetResource {
    public static Image get(String file){
        try {
            return new ImageIcon(Objects.requireNonNull(GetResource.class.getResource("res/"+file))).getImage();
        }catch (NullPointerException e){
            return new ImageIcon().getImage();
        }
    }

    public static String getFile(String file){
        try{
            return Objects.requireNonNull(GetResource.class.getResource(file)).getPath();
        } catch (NullPointerException e){
            return "";
        }
    }
}
