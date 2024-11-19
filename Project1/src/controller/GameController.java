package controller;

import model.Direction;
import model.MapMatrix;
import view.game.GamePanel;
import view.game.GridComponent;
import view.game.Hero;

/**
 * 它是 MVC 模式中的控制器部分，主要作用是连接 GamePanel（视图） 和 MapMatrix（模型），并处理游戏的主要逻辑。具体来说，这个控制器类负责管理玩家的移动、重启游戏等功能。
 * You can design several methods about the game logic in this class.
 */
public class GameController {
    private final GamePanel view;//储存视图对象，负责显示游戏界面
    private final MapMatrix model;//储存模型对象，处理游戏数据（如地图信息）
//GamePanel和MapMatrix为类名，view和model为其实例。
    public GameController(GamePanel view, MapMatrix model) {
        this.view = view;
        this.model = model;
        view.setController(this);//将当前的控制器（this）传递给视图（GamePanel），这样视图就能调用控制器的方法来处理游戏逻辑。
    }

    public void restartGame() {
        System.out.println("Do restart game here");
        //在这里补全重置角色状态，地图等操作
    }
     //核心逻辑，处理玩家移动
    public boolean doMove(int row, int col, Direction direction) {//传入希望移动哪个位置的东西，往哪个方向移动
        GridComponent currentGrid = view.getGridComponent(row, col);//获取当前格子组件对象
        //target row can column.
        int tRow = row + direction.getRow();
        int tCol = col + direction.getCol();
        //目标位置的行列坐标
        GridComponent targetGrid = view.getGridComponent(tRow, tCol);//读取要移动到的位置的图形
        int[][] map = model.getMatrix();//把矩阵传到map里
        if (map[tRow][tCol] == 0 || map[tRow][tCol] == 2) {//判断目标格子是否为空或者目标格子，如果是，允许玩家移动到这
            //修改矩阵的值
            model.getMatrix()[row][col] -= 20;//玩家当前位置的值减去20
            model.getMatrix()[tRow][tCol] += 20;//目标位置的值加上20，表示玩家移动到目标位置（因为玩家表示数字为20）
            //在GamePanel中上传玩家
            Hero h = currentGrid.removeHeroFromGrid();//从当前格子中移除玩家（将当前图形换成白底）
            targetGrid.setHeroInGrid(h);//在目标单元格图形改为“英雄”图形
            //更新Hero对象的行列属性
            h.setRow(tRow);
            h.setCol(tCol);
            return true;
        }
        return false;//如果玩家成功移动，返回true，否则返回false
    }

    //todo: add other methods such as loadGame, saveGame...

}
