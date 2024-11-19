package view.game;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class GridComponent extends JComponent {
    private int row;
    private int col;
    private final int id; // 表示该格子的单位数值，在整个游戏中不可更改
    private Hero hero;
    private Box box;
    static Color color = new Color(246, 246, 229);
    //一个GridComponent表示一个单元格的颜色等图像性质
    public GridComponent(int row, int col, int id, int gridSize) {//gridSize为一个单元格大小
        this.setSize(gridSize, gridSize);
        this.row = row;//记录r和c才知道这个实例描述的是哪个格子
        this.col = col;
        this.id = id;//各自的标识符，它的各位数字表示格子类型
    }

    @Override//Graphics对象用来绘制颜色、矩形和多边形
    //BorderFactory设置边框颜色
    public void paintComponent(Graphics g) {
        super.printComponents(g);
        Color borderColor = color;
        switch (id % 10) {//根据格子id绘制成不同颜色
            case 1:
                g.setColor(Color.LIGHT_GRAY);
                g.fillRect(0, 0, getWidth(), getHeight());
                borderColor = Color.DARK_GRAY;
                break;
            case 0:
                g.setColor(Color.WHITE);
                g.fillRect(0, 0, getWidth(), getHeight());
                break;
            case 2:
                g.setColor(Color.WHITE);
                g.fillRect(0, 0, getWidth(), getHeight());
                g.setColor(Color.GREEN);
                int[] xPoints = {getWidth() / 2, getWidth(), getWidth() / 2, 0};
                int[] yPoints = {0, getHeight() / 2, getHeight(), getHeight() / 2};
                g.fillPolygon(xPoints, yPoints, 4);
                g.setColor(Color.BLACK);
                g.drawPolygon(xPoints, yPoints, 4);
                break;
        }
        Border border = BorderFactory.createLineBorder(borderColor, 1);
        this.setBorder(border);
    }
//访问和修改行列位置和id
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

    public int getId() {
        return id;
    }

    //当格子中添加玩家角色时，调用此方法
    public void setHeroInGrid(Hero hero) {
        this.hero = hero;
        this.add(hero);//调用add方法，把hero中定义的图像添加到GridComponent中显示
    }

    //当格子中添加箱子时，调用此方法
    public void setBoxInGrid(Box box) {
        this.box = box;
        this.add(box);
    }
    //当从格子中移除玩家角色时，调用此方法
    public Hero removeHeroFromGrid() {
        this.remove(this.hero); // 从格子中移除玩家角色组件
        Hero h = this.hero;
        this.hero = null; // 将玩家角色属性设置为null
        this.revalidate(); // 更新组件以便重新绘制
        this.repaint(); // 重新绘制组件
        return h;
    }
    // 当从格子中移除箱子时，调用此方法
    public Box removeBoxFromGrid() {
        this.remove(this.box); // 从格子中移除箱子组件
        Box b = this.box;
        this.box = null; // 将箱子属性设置为null
        this.revalidate(); // 更新组件以便重新绘制
        this.repaint(); // 重新绘制组件
        return b;
    }

}
