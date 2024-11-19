package view.game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
//JPanelJPanel 是 Java Swing 库中的一个类，主要用于在 GUI 窗口中显示和组织组件。JPanel 可以作为其他组件的容器，能够添加按钮、标签、文本框等各种 Swing 组件，并且可以在其中自定义布局和绘制图形。
/**
 * This class is only to enable key events.
 */
public abstract class ListenerPanel extends JPanel {
    public ListenerPanel() {
        enableEvents(AWTEvent.KEY_EVENT_MASK);//启用键盘事件监听
        this.setFocusable(true);//使得特定被焦点的组件才能接受键盘事件
    }

    @Override
    protected void processKeyEvent(KeyEvent e) {
        super.processKeyEvent(e);
        if (e.getID() == KeyEvent.KEY_PRESSED) {//检测事件类型是否为按键按下
            switch (e.getKeyCode()) {//获得按下的键码
                case KeyEvent.VK_RIGHT -> doMoveRight();
                case KeyEvent.VK_LEFT -> doMoveLeft();
                case KeyEvent.VK_UP -> doMoveUp();
                case KeyEvent.VK_DOWN -> doMoveDown();
            }
        }
    }

//abstract表示本类不提供具体实现，留给继承子类实现
    public abstract void doMoveRight();
    public abstract void doMoveLeft();
    public abstract void doMoveUp();
    public abstract void doMoveDown();

}
