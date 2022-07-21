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

    public void update(double x, double y){
        if(x > getX()+10){
            moveX(0.2);
        }else if(x < getX()-10){
            moveX(-0.2);
        }
        if(y > getY()+10){
            moveY(0.2);
        }else if(y < getY()-10){
            moveY(-0.2);
        }
    }
}
