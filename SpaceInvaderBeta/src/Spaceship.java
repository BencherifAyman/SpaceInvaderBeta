import com.jogamp.opengl.GL2;

public class Spaceship extends Shape3D{

    public Spaceship (double x, double y, double z, double size)
    {
        this.x = x; this.y = y; this.z = z;
        this.size = size;
    }

    public void translateXRight(){
        super.x+=0.06;
    }
    public void translateXLeft(){
        super.x-=0.06;
    }

    @Override
    public void display(GL2 gl) {
        gl.glPushMatrix();
        gl.glTranslated(x, y, z);
        gl.glScaled(size, size, size);
        gl.glBegin(GL2.GL_QUADS);
        // Front
        gl.glColor3d(1, 0, 2);
        gl.glVertex3d(-1, -1, 1);
        gl.glVertex3d(1, -1, 1);
        gl.glVertex3d(1, 1, 1);
        gl.glVertex3d(-1, 1, 1);
        // Rear

        gl.glEnd();
        gl.glPopMatrix();

    }

    @Override
    public void move() {

    }
}
