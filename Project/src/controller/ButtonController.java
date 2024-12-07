package controller;

import view.FrameUtil;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ButtonController extends FrameUtil {
    public ButtonController() {

    }
    public static JButton createButton(JFrame frame, String text, Point point, int width, int height , String path) {
        BufferedImage bufferedImage = null;
        if (path ==null || path.isEmpty()) {
            path = "PictureResource/button.png";
        }
        try {
            bufferedImage = ImageIO.read(new File(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        BufferedImage newBufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = newBufferedImage.createGraphics();
        g2d.drawImage(bufferedImage, 0,0, width,height, null);
        g2d.dispose();
        ImageIcon newIcon = new ImageIcon(newBufferedImage);
        JButton button;
        button = FrameUtil.createButton(frame, text, point, width, height);
        button.setIcon(newIcon);
        // 设置按钮文本和图标的位置
        button.setHorizontalTextPosition(SwingConstants.CENTER);  // 文本水平居中
        button.setVerticalTextPosition(SwingConstants.CENTER);  // 文本垂直居中
        button.setIconTextGap(10);  // 图标和文本之间的间距
        return button;
    }
}
