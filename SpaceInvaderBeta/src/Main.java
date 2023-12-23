import java.awt.*;

import javax.swing.JFrame;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.FPSAnimator;

public class Main
{

    public static void main(String[] args)
    {
        JFrame frame = new JFrame();
        GLCanvas canvas = new GLCanvas();
        GLHandler events = new GLHandler();
        canvas.addKeyListener(new UserKeyboard(events));
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        canvas.setPreferredSize(new Dimension((int) screenSize.getWidth(),(int) screenSize.getHeight()));
        canvas.addGLEventListener(events);
        frame.getContentPane().add(canvas);
        frame.setTitle("OpenGL test");
        frame.pack();
        frame.setVisible(true);
        FPSAnimator anim = new FPSAnimator(canvas, 60);
        anim.start();

    }

}