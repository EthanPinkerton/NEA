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
        if(x*scale > getX()*scale+scale*2){
            moveX(0.05);
        }else if(x*scale < getX()*scale-scale*2){
            moveX(-0.05);
        }
        if(y*scale > getY()*scale+scale*2){
            moveY(0.05);
        }else if(y*scale < getY()*scale-scale*2){
            moveY(-0.05);
        }
    }
}
