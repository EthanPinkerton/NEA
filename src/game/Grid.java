package game;

import java.awt.*;

public class Grid {

    protected int scale;
    private final Camera camera = new Camera(4.2,4.2);

    public Grid(int scale){
        this.scale = scale;
    }

    public int getScale(){
        return scale;
    }

    public void update(double x, double y, double speed){
        camera.update(x,y,speed,scale);
    }

    public boolean onScreen(double x, double y){
        if(getX(x) < -scale || getX(x) > Toolkit.getDefaultToolkit().getScreenSize().width){
            return false;
        }else if(getY(y) < -scale || getY(y) > Toolkit.getDefaultToolkit().getScreenSize().height){
            return false;
        }else{
            return true;
        }
    }

    public int getX(double x){
        return (int) Math.round(Toolkit.getDefaultToolkit().getScreenSize().width/2.0+(x*scale)-(camera.getX()*scale));
    }

    public int getY(double y){
        return (int) Math.round(Toolkit.getDefaultToolkit().getScreenSize().height/2.0+(y*scale)-(camera.getY()*scale));
    }
}
