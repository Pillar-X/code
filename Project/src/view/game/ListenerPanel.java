package view.game;

import controller.FrameController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;


/**
 * This class is only to enable key events.
 */
public abstract class ListenerPanel extends JPanel {
    public ListenerPanel() {
        enableEvents(AWTEvent.KEY_EVENT_MASK);
        this.setFocusable(true);
    }

    @Override
    protected void processKeyEvent(KeyEvent e) {
        super.processKeyEvent(e);
        if (e.getID() == KeyEvent.KEY_PRESSED) {
            if(FrameController.getGameFrame().getLevelNumber()!=7){
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_RIGHT -> doMoveRight();
                    case KeyEvent.VK_LEFT -> doMoveLeft();
                    case KeyEvent.VK_UP -> doMoveUp();
                    case KeyEvent.VK_DOWN -> doMoveDown();
                    case KeyEvent.VK_F -> doInteract();
                }
            }
            else{
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_RIGHT -> doMoveRight();
                    case KeyEvent.VK_LEFT -> doMoveLeft();
                    case KeyEvent.VK_UP -> doMoveUp();
                    case KeyEvent.VK_DOWN -> doMoveDown();
                    case KeyEvent.VK_F -> doInteract();
                    case KeyEvent.VK_W -> secondDoMoveUp();
                    case KeyEvent.VK_S -> secondDoMoveDown();
                    case KeyEvent.VK_A -> secondDoMoveLeft();
                    case KeyEvent.VK_D -> secondDoMoveRight();
                    case KeyEvent.VK_G -> dealPortal2();
                    case KeyEvent.VK_H -> dealPortal1();
                }

            }
        }
    }




    public abstract void doMoveRight();
    public abstract void doMoveLeft();
    public abstract void doMoveUp();
    public abstract void doMoveDown();
    public abstract void doInteract();
    public abstract void secondDoMoveUp();
    public abstract void secondDoMoveDown();
    public abstract void secondDoMoveRight();
    public abstract void secondDoMoveLeft();
    public abstract void dealPortal2();
    public abstract void dealPortal1();

}
