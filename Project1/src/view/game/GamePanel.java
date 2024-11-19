package view.game;//游戏界面

import controller.GameController;
import model.Direction;
import model.MapMatrix;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/**
 * It is the subclass of ListenerPanel, so that it should implement those four methods: do move left, up, down ,right.
 * The class contains a grids, which is the corresponding GUI view of the matrix variable in MapMatrix.
 */
public class GamePanel extends ListenerPanel {

    private GridComponent[][] grids;    // 创建名为grids的二维数组，其储存的变量为GridComponent类
    private MapMatrix model;            // 存储地图数据
    private GameController controller;// 游戏控制器
    private JLabel stepLabel;// 显示步数的标签
    private int steps;// 步数计数器
    private final int GRID_SIZE = 50;// 每个网格的尺寸

    private Hero hero;// 玩家角色


    public GamePanel(MapMatrix model) {//接受游戏地图矩阵
        this.setVisible(true);//这里this指的是当前GamePanel的对象
        this.setFocusable(true);
        this.setLayout(null);
        this.setSize(model.getWidth() * GRID_SIZE +4, model.getHeight() * GRID_SIZE + 4);
        //设置面板大小
        this.model = model;
        this.grids = new GridComponent[model.getHeight()][model.getWidth()];
        //数组大小根据地图高度和跨度进行初始化
        initialGame();//初始化游戏界面

    }
    //显示游戏的初始化位置
    public void initialGame() {
        this.steps = 0;
        for (int i = 0; i < grids.length; i++) {
            for (int j = 0; j < grids[i].length; j++) {
                //Units digit maps to id attribute in GridComponent. (The no change value)
                //根据地图模型填充GridComponent
                grids[i][j] = new GridComponent(i, j, model.getId(i, j) % 10, this.GRID_SIZE);//填充非移动部件
                grids[i][j].setLocation(j * GRID_SIZE + 2, i * GRID_SIZE + 2);//设置每个单元格位置
                //把十位数字映射到箱子或英雄的对应位置 (Changed value)
                //放置英雄和箱子
                switch (model.getId(i, j) / 10) {
                    case 1:
                        grids[i][j].setBoxInGrid(new Box(GRID_SIZE - 10, GRID_SIZE - 10));
                        break;
                    case 2:
                        this.hero = new Hero(GRID_SIZE - 16, GRID_SIZE - 16, i, j);
                        grids[i][j].setHeroInGrid(hero);
                        break;
                }
                this.add(grids[i][j]);//把每一个GridComponent 的实例上传
            }
        }
        this.repaint();
    }

    @Override
    public void doMoveRight() {
        System.out.println("Click VK_RIGHT");
        if (controller.doMove(hero.getRow(), hero.getCol(), Direction.RIGHT)) {
            this.afterMove();
        }
    }

    @Override
    public void doMoveLeft() {
        System.out.println("Click VK_LEFT");
        if(controller.doMove(hero.getRow(), hero.getCol(), Direction.LEFT)){
            this.afterMove();
        }
    }

    @Override
    public void doMoveUp() {
        System.out.println("Click VK_Up");
       if( controller.doMove(hero.getRow(), hero.getCol(), Direction.UP)){
           this.afterMove();
       }
    }

    @Override
    public void doMoveDown() {
        System.out.println("Click VK_DOWN");
        if(controller.doMove(hero.getRow(), hero.getCol(), Direction.DOWN)){
            this.afterMove();
        }
    }

    public void afterMove() {
        this.steps++;
        this.stepLabel.setText(String.format("Step: %d", this.steps));
    }

    public void setStepLabel(JLabel stepLabel) {
        this.stepLabel = stepLabel;
    }



    public void setController(GameController controller) {
        this.controller = controller;
    }

    public GridComponent getGridComponent(int row, int col) {
        return grids[row][col];
    }
}
