package view.game;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Box extends JComponent {
    private final int value = 10;
    private Image image;
    public Box(int width, int height) {
        this.setSize(width, height);
        this.setLocation(5, 5);
        loadImage();
    }
    private void loadImage() {
        try {
            // 加载墙壁图片文件
            image = ImageIO.read(new File("PictureResource/box.png"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
//    public void paintComponent(Graphics g) {
//        g.setColor(Color.ORANGE);
//        g.fillRect(0, 0, getWidth(), getHeight());
//        Border border = BorderFactory.createLineBorder(Color.black, 1);
//        this.setBorder(border);
//    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image!= null) {
        // 绘制图片
            g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
        } else {
        // 如果图片未加载，绘制一个默认的矩形
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, getWidth(), getHeight());
        }
    }

    public int getValue() {
        return value;
    }
}
