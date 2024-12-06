package view;

import model.MapMatrix;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * This class is to create basic JComponent.
 */
public class FrameUtil {
    public static JLabel createJLabel(JFrame frame, Point location, int width, int height, String text) {
        JLabel jLabel = new JLabel(text);
        jLabel.setSize(width, height);
        jLabel.setLocation(location);
        frame.add(jLabel);
        return jLabel;
    }


    public static JLabel createJLabel(JFrame frame, String name, Font font, Point location, int width, int height) {
        JLabel label = new JLabel(name);
        label.setFont(font);
        label.setLocation(location);
        label.setSize(width, height);
        frame.add(label);
        return label;
    }

    public static JTextField createJTextField(JFrame frame, Point location, int width, int height) {
        JTextField jTextField = new JTextField();
        jTextField.setSize(width, height);
        jTextField.setLocation(location);
        frame.add(jTextField);
        return jTextField;
    }

    public static JButton createButton(JFrame frame, String name, Point location, int width, int height) {
        JButton button = new JButton(name);
        button.setLocation(location);
        button.setSize(width, height);
        frame.add(button);
        return button;
    }

    public static JComboBox<MapMatrix> createComboBox(JFrame frame,Point location,int width,int height,ArrayList<MapMatrix> mapMatrixList){
        DefaultComboBoxModel<MapMatrix> model = new DefaultComboBoxModel<>(mapMatrixList.toArray(new MapMatrix[0]));
        JComboBox<MapMatrix> jComboBox = new JComboBox<>(model);

        jComboBox.setSize(width,height);
        jComboBox.setLocation(location);
        frame.add(jComboBox);
        return jComboBox;
    }

}
