package game;

public class Vector {

    protected double i;
    protected double j;

    public Vector(double i, double j) {
        this.i = i;
        this.j = j;
    }

    public double getMod() {
        return Math.sqrt(i * i + j * j);
    }

    public double iDirection() {
        if (i > 0) {
            return 1;
        } else if (i == 0) {
            return 0;
        } else {
            return -1;
        }
    }

    public double jDirection() {
        if (j > 0) {
            return 1;
        } else if (j == 0) {
            return 0;
        } else {
            return -1;
        }
    }

    public double getI() {
        return i;
    }

    public double getJ() {
        return j;
    }

    public void setI(double i) {
        this.i = i;
    }

    public void setJ(double j) {
        this.j = j;
    }
}
