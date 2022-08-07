package game;

public class Camera {
    protected double x,y;

    public Camera(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void moveX(double x){
        this.x += x;
    }

    public void moveY(double y){
        this.y += y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void update(double x, double y, int scale){
        if(x*scale > getX()*scale+scale*3){
            moveX(0.1);
        }else if(x*scale < getX()*scale-scale*3){
            moveX(-0.1);
        }
        if(y*scale > getY()*scale+scale*3){
            moveY(0.1);
        }else if(y*scale < getY()*scale-scale*3){
            moveY(-0.1);
        }
    }
}
