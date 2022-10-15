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

    public void update(double x, double y, double speed, int scale){
        if(x*scale > getX()*scale+scale*2){
            moveX(speed);
        }else if(x*scale < getX()*scale-scale*2){
            moveX(-speed);
        }
        if(y*scale > getY()*scale+scale*2){
            moveY(speed);
        }else if(y*scale < getY()*scale-scale*2){
            moveY(-speed);
        }
    }
}
