package view;//创建 JLabel（标签）、JTextField文本框、JButton按钮。用于窗口显示和互动界面

import javax.swing.*;
import java.awt.*;

/**
 * This class is to create basic JComponent.
 */
public class FrameUtil {
    //创建一个标签，并设置其位置，大小和显示的文本
    public static JLabel createJLabel(JFrame frame, Point location, int width, int height, String text) {
        JLabel jLabel = new JLabel(text);
        jLabel.setSize(width, height);
        jLabel.setLocation(location);//表示标签的显示位置（左上角坐标）
        frame.add(jLabel);//需要将标签添加到JFrame窗口
        return jLabel;
    }
//font标签文本字体
    public static JLabel createJLabel(JFrame frame, String name, Font font, Point location, int width, int height) {
        JLabel label = new JLabel(name);//name标签的显示文本
        label.setFont(font);
        label.setLocation(location);
        label.setSize(width, height);
        frame.add(label);
        return label;
    }
//创建文本框
    public static JTextField createJTextField(JFrame frame, Point location, int width, int height) {
        JTextField jTextField = new JTextField();
        jTextField.setSize(width, height);
        jTextField.setLocation(location);
        frame.add(jTextField);//文本框添加到JFrame窗口
        return jTextField;
    }
//创建按钮
    public static JButton createButton(JFrame frame, String name, Point location, int width, int height) {
        JButton button = new JButton(name);
        button.setLocation(location);
        button.setSize(width, height);
        frame.add(button);//添加到JFrame窗口
        return button;
    }

}
