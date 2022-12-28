package game;

public class Grid {

    protected int scale;
    private final Camera camera;
    protected int height, width;

    public Grid(int scale, double x, double y) {
        this.scale = scale;
        height = 1080;
        width = 1920;
        camera  = new Camera(x, y);
    }

    public int getScale() {
        return scale;
    }

    public void update(int height, int width, Player player) {
        camera.update(player.getX(), player.getY(), player.getSpeed(), scale, width, height);
        this.height = height;
        this.width = width;
    }

    public boolean onScreen(double x, double y) {
        if (getX(x) < -scale || getX(x) > width) {
            return false;
        } else if (getY(y) < -scale || getY(y) > height) {
            return false;
        } else {
            return true;
        }
    }

    public int getHeight(){
        return height;
    }

    public int getWidth(){
        return width;
    }

    public int getX(double x) {
        return (int) Math.round(width / 2.0 + (x * scale) - (camera.getX() * scale));
    }

    public int getY(double y) {
        return (int) Math.round(height / 2.0 + (y * scale) - (camera.getY() * scale));
    }
}
