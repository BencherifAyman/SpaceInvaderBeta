import com.jogamp.opengl.GL2;

public abstract class Shape3D {
    protected double x, y, z;
    protected double size;
    protected boolean hasDescended;

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public boolean hasDescended() {
        return hasDescended;
    }

    public void setDescended(boolean hasDescended) {
        this.hasDescended = hasDescended;
    }

    public abstract void display(GL2 gl);

    public abstract void move();
}
