package game;

public class Grid {

    protected int scale;
    private final Camera camera = new Camera(0,0);

    public Grid(int scale){
        this.scale = scale;
    }

    public int getScale(){
        return scale;
    }

    public void update(double x, double y){
        camera.update(x,y);
    }

    public double getX(double x){
        return 432+(x*scale)-(camera.getX()*scale);
    }

    public double getY(double y){
        return 448+(y*scale)-(camera.getY()*scale);
    }
}
