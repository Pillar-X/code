package view.game;

import javax.swing.*;
import java.awt.*;
//继承自JComponent使其能够在图形界面显示
public class Hero extends JComponent {
    private int row;
    private int col;

    private final int value = 20;//英雄的属性值
    private static Color color = new Color(87, 171, 220);

    public Hero(int width, int height, int row, int col) {
        this.row = row;
        this.col = col;
        this.setSize(width, height);//设置大小
        this.setLocation(8, 8);//在图形单元格内的初始位置为8，8（即相对单元格居中）
    }
//绘制代表英雄的图形
    public void paintComponent(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillOval(0, 0, getWidth(), getHeight());
        g.setColor(color);
        g.fillOval(1,1,getWidth()-2,getHeight()-2);
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
