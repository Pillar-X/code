package controller;

import model.Direction;
import model.MapMatrix;
import view.game.Box;
import view.game.GamePanel;
import view.game.GridComponent;
import view.game.Hero;

import java.io.*;

/**
 * It is a bridge to combine GamePanel(view) and MapMatrix(model) in one game.
 * You can design several methods about the game logic in this class.
 */
public class GameController {
    private final GamePanel gamePanel;
    private final MapMatrix mapMatrix;

    public GameController(GamePanel gamePanel, MapMatrix mapMatrix) {
        this.gamePanel = gamePanel;
        this.mapMatrix = mapMatrix;
        gamePanel.setController(this);
    }

    public void restartGame() {
        System.out.println("Restart Game");
        gamePanel.restart();
    }

    public boolean doMove(int row, int col, Direction direction) {
        GridComponent currentGrid = gamePanel.getGridComponent(row, col);
        int tRow = row + direction.getRow();
        int tCol = col + direction.getCol();
        int ttRow = tRow + direction.getRow();
        int ttCol = tCol + direction.getCol();
        GridComponent targetGrid = gamePanel.getGridComponent(tRow, tCol);
        if(ttRow<0 || ttRow>= mapMatrix.getHeight() || ttCol<0 || ttCol>= mapMatrix.getWidth()) {
            return false;//如果ttRow 或者ttCol越界说明箱子已经靠到了外围墙面，直接返回false，避免数组越界
        }
        GridComponent ttargetGrid = gamePanel.getGridComponent(ttRow,ttCol);
        int[][] map = mapMatrix.getMatrix();
        if (map[tRow][tCol] == 0 || map[tRow][tCol] == 2) {
            mapMatrix.getMatrix()[row][col] -= 20;
            mapMatrix.getMatrix()[tRow][tCol] += 20;
            Hero h = currentGrid.removeHeroFromGrid();
            targetGrid.setHeroInGrid(h);
            h.setRow(tRow);
            h.setCol(tCol);
            h.loadImage(direction.toString().toLowerCase());//根据移动方向切换英雄朝向
            return true;
        }
        if ((map[tRow][tCol]==10||map[tRow][tCol]==12) && (map[ttRow][ttCol]==0 ||map[ttRow][ttCol]==2)) {

            mapMatrix.getMatrix()[row][col] -= 20;
            mapMatrix.getMatrix()[tRow][tCol] += (20 - 10);
            mapMatrix.getMatrix()[ttRow][ttCol] += 10;
            Hero h = currentGrid.removeHeroFromGrid();//从当前格子中移除玩家（将当前图形换成白底）
            Box b = targetGrid.removeBoxFromGrid();
            targetGrid.setHeroInGrid(h);//在目标单元格图形改为“英雄”图形
            ttargetGrid.setBoxInGrid(b);
            h.setRow(tRow);
            h.setCol(tCol);
            h.loadImage(direction.toString().toLowerCase());//根据移动方向切换英雄朝向
            return true;
        }


        return false;
    }
    public boolean isGameWin(){
        int[][] map = mapMatrix.getMatrix();
        int count = 0;
        int total = 0;
        for(int i=0;i<map.length;i++){
            for(int j=0;j<map[0].length;j++){
                if(map[i][j]/10 ==1){
                    total++;
                }
            }
        }//计算有多少个箱子
        for(int i=0;i<map.length;i++){
            for(int j=0;j<map[0].length;j++){
                if(map[i][j]==12){
                    count++;
                }
            }
        }//计算有多少个箱子在目标位置
        if(count==total){
            System.out.println("游戏胜利");
            return true;
        }
        return false;
    }//判断游戏是否胜利

    //todo: add other methods such as loadGame, saveGame...

}
