import com.jogamp.opengl.GL2;

public class Cube3D extends Shape3D {

    public Cube3D(double x, double y, double z, double size) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.size = size;
        this.hasDescended = false;
    }

    public void translateXRight() {
        this.x += 0.01;
    }

    public void translateXLeft() {
        this.x -= 0.01;
    }

    public void adjustPositionAfterSideChange() {

            // Descend de 10% de la hauteur de l'écran après chaque changement de côté
            this.y -= 0.1;
        this.hasDescended=true;

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
        gl.glColor3d(1, 1, 8);
        gl.glVertex3d(-1, -1, -1);
        gl.glVertex3d(1, -1, -1);
        gl.glVertex3d(1, 1, -1);
        gl.glVertex3d(-1, 1, -1);
        // Left
        gl.glColor3d(3, 1, 2);
        gl.glVertex3d(-1, -1, -1);
        gl.glVertex3d(-1, -1, 1);
        gl.glVertex3d(-1, 1,  1);
        gl.glVertex3d(-1, 1, -1);
        // Right
        gl.glColor3d(1, 1, 1);
        gl.glVertex3d(1, -1, -1);
        gl.glVertex3d(1, -1, 1);
        gl.glVertex3d(1, 1,  1);
        gl.glVertex3d(1, 1, -1);
        // Bottom
        gl.glColor3d(0, 2, 1);
        gl.glVertex3d(-1, -1, 1);
        gl.glVertex3d(1, -1, 1);
        gl.glVertex3d(1, -1, -1);
        gl.glVertex3d(-1, -1, -1);
        // Up
        gl.glColor3d(1, 1, 1);
        gl.glVertex3d(-1, 1, 1);
        gl.glVertex3d(1, 1, 1);
        gl.glVertex3d(1, 1, -1);
        gl.glVertex3d(-1, 1, -1);
        gl.glEnd();
        gl.glPopMatrix();
    }
    @Override
    public void move() {

    }

    public boolean checkCollision(Cube3D other) {
        double obj1X = this.x;
        double obj1Y = this.y;
        double obj1Z = this.z;
        double obj1Size = this.size;

        double obj2X = other.x;
        double obj2Y = other.y;
        double obj2Z = other.z;
        double obj2Size = other.size;

        return obj1X < obj2X + obj2Size && obj1X + obj1Size > obj2X &&
                obj1Y < obj2Y + obj2Size && obj1Y + obj1Size > obj2Y &&
                obj1Z < obj2Z + obj2Size && obj1Z + obj1Size > obj2Z;
    }


}