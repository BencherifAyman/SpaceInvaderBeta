import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class UserKeyboard implements KeyListener {

    private final GLHandler events;

    public UserKeyboard(GLHandler events) {
        this.events = events;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Not used for arrow keys
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        // Handle arrow key events
        switch (keyCode) {
            case KeyEvent.VK_LEFT:
                this.events.translateLeftSpaceShipX();
                break;
            case KeyEvent.VK_RIGHT:
                this.events.translateRightSpaceShipX();
                break;
            case KeyEvent.VK_SPACE:
                this.events.shootProjectile();
                break;
            default:
                // Ignore other keys
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Not used for arrow keys
    }
}
