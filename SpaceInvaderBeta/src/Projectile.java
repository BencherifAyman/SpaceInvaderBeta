import com.jogamp.opengl.GL2;

public class Projectile extends Cube3D {
    private static final double PROJECTILE_SPEED = 0.05;

    public Projectile(double x, double y, double z, double size) {
        super(x, y, z, size);
    }

    public void move() {
        this.y += PROJECTILE_SPEED;
    }

    @Override
    public void display(GL2 gl) {
        // Logique d'affichage du projectile
        super.display(gl);
        move();
    }
}
