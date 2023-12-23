import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.glu.GLU;

public class GLHandler implements GLEventListener{

    private final GLU glu;
    private final ArrayList<Cube3D> items3D;
    private boolean hasReachedLimite = false;
    private double limite = 0;
    private final Spaceship spaceship;
    private Cube3D shout;

    public GLHandler()
    {
        this.glu = new GLU();
        this.items3D = new ArrayList<>();
        for (double i = -2; i < 2.5; i+=0.5){
            for (double j = 0; j < 3.5 ; j+=0.5) {
                this.items3D.add(new Cube3D(i, j, -4, 0.1f));
            }
        }
        spaceship = new Spaceship(0, -1.4, -4, 0.1f);
    }

    @Override
    public void init(GLAutoDrawable draw) {
        GL2 gl = draw.getGL().getGL2();
        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        gl.glEnable(GL2.GL_DEPTH_TEST);

        //      Code précédent d’initialisation
        gl.glTexParameteri(gl.GL_TEXTURE_2D, gl.GL_TEXTURE_WRAP_S, gl.GL_REPEAT);
        gl.glTexParameteri(gl.GL_TEXTURE_2D, gl.GL_TEXTURE_WRAP_T, gl.GL_REPEAT);
        gl.glTexParameteri(gl.GL_TEXTURE_2D, gl.GL_TEXTURE_MAG_FILTER, gl.GL_NEAREST);
        gl.glTexParameteri(gl.GL_TEXTURE_2D, gl.GL_TEXTURE_MIN_FILTER, gl.GL_NEAREST);
        gl.glTexEnvf(gl.GL_TEXTURE_ENV, gl.GL_TEXTURE_ENV_MODE, gl.GL_REPLACE);
        gl.glEnable(gl.GL_TEXTURE_2D);
    }

    @Override
    public void reshape(GLAutoDrawable draw, int x, int y, int width, int height) {
        GL2 gl = draw.getGL().getGL2();
        // -- SCREEN
        float aspect = (float)width / height;
        gl.glViewport(0, 0, width, height);
        // -- CAMERA
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
        // FOCAL, ASPECT, Zmin, Zmax
        this.glu.gluPerspective(45.0, aspect, 0.1, 100);
        // -- OBJECTS ?
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();
    }

    @Override
    public void display(GLAutoDrawable draw) {
        try {
            GL2 gl = draw.getGL().getGL2();
            gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
            gl.glLoadIdentity();

            // Affiche le vaisseau spatial
            this.spaceship.display(gl);

            // Vérifie si le vaisseau spatial a atteint la limite droite ou gauche
            if (this.spaceship.getX() >= 2.8 || this.spaceship.getX() <= -2.8) {
                // Ajuste la position des cubes après chaque changement de côté du vaisseau spatial

            }

            // Affiche et déplace les projectiles
            if (this.shout != null) {
                this.shout.display(gl);
                this.shout.move();

                // Vérifie les collisions avec les cubes
                Iterator<Cube3D> iterator = this.items3D.iterator();
                while (iterator.hasNext()) {
                    Cube3D cube = iterator.next();
                    if (this.shout.checkCollision(cube)) {
                        iterator.remove();
                        this.shout = null;
                    }
                }

                // Vérifie si le projectile est hors des limites
                if (this.shout != null && this.shout.getY() > 1.8) {
                    this.shout = null;
                }
            }

            // Affiche et déplace les cubes
            for (Cube3D cube : this.items3D) {
                cube.display(gl);



                // Déplace les cubes en fonction de la direction
                if (!hasReachedLimite) {
                    cube.translateXRight();
                } else {
                    // Déplace le cube vers la gauche
                    cube.translateXLeft();
                    // Vérifie si le cube a déjà descendu
                    if (!cube.hasDescended()) {
                        cube.setDescended(false);
                        cube.adjustPositionAfterSideChange();
                    }
                }
            }


            // Met à jour hasReachedLimite et limite
            if (!hasReachedLimite) {
                this.limite += 0.01;
            } else {
                this.limite -= 0.01;
            }

            // Met à jour hasReachedLimite en fonction de la limite
            if (this.limite >= 0.8 && this.limite <= 0.9) {
                hasReachedLimite = true;
            } else if (this.limite <= -0.8 && this.limite >= -0.9) {
                hasReachedLimite = false;
            }
        } catch (NullPointerException e) {
            // Gère les exceptions
        }
    }










    @Override
    public void dispose(GLAutoDrawable arg0) {

    }
    public void translateLeftSpaceShipX (){
        System.out.println(this.spaceship.getX());
        if (this.spaceship.getX() >= -2.8){
            this.spaceship.translateXLeft();

        }

    }
    public void translateRightSpaceShipX (){
        if (this.spaceship.getX() <= 2.8) {
            this.spaceship.translateXRight();
        }
    }



    public void shootProjectile() {
        if (this.shout == null) {
            this.shout = new Projectile(this.spaceship.getX(), this.spaceship.getY(), -4, 0.05f);
        }
    }
}