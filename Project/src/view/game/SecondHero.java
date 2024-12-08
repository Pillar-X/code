package view.game;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

public class SecondHero extends JComponent {
    private int row;
    private int col;
    private Image image;
    private final int value = 30;
    private static Color color = new Color(87, 171, 220);
    private String direction;
    public SecondHero(int width, int height, int row, int col) {
        this.row = row;
        this.col = col;
        this.setSize(width, height);
        this.setLocation(8, 8);
        loadImage("down");

    }

    public void loadImage(String direction) {
        try {
            // 根据方向加载图片文件
            image = ImageIO.read(new File("PictureResource/hero_" + direction + ".png"));
            this.direction = direction;
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    @Override
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

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }
}

