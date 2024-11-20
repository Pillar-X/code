package controller;

import model.Direction;
import model.MapMatrix;
import view.game.Box;
import view.game.GamePanel;
import view.game.GridComponent;
import view.game.Hero;

/**
 * It is a bridge to combine GamePanel(view) and MapMatrix(model) in one game.
 * You can design several methods about the game logic in this class.
 */
public class GameController {
    private final GamePanel view;
    private final MapMatrix model;

    public GameController(GamePanel view, MapMatrix model) {
        this.view = view;
        this.model = model;
        view.setController(this);
    }

    public void restartGame() {
        System.out.println("Do restart game here");
    }

    public boolean doMove(int row, int col, Direction direction) {
        GridComponent currentGrid = view.getGridComponent(row, col);
        //target row can column.
        int tRow = row + direction.getRow();
        int tCol = col + direction.getCol();
        int ttRow = tRow + direction.getRow();
        int ttCol = tCol + direction.getCol();
        GridComponent targetGrid = view.getGridComponent(tRow, tCol);
        GridComponent ttargetGrid = view.getGridComponent(ttRow,ttCol);
        int[][] map = model.getMatrix();
        if (map[tRow][tCol] == 0 || map[tRow][tCol] == 2) {
            //update hero in MapMatrix
            model.getMatrix()[row][col] -= 20;
            model.getMatrix()[tRow][tCol] += 20;
            //Update hero in GamePanel
            Hero h = currentGrid.removeHeroFromGrid();
            targetGrid.setHeroInGrid(h);
            //Update the row and column attribute in hero
            h.setRow(tRow);
            h.setCol(tCol);
            return true;
        }
        if ((map[tRow][tCol]==10||map[tRow][tCol]==12) && (map[ttRow][ttCol]==0 ||map[ttRow][ttCol]==2)){
            model.getMatrix()[row][col] -= 20;
            model.getMatrix()[tRow][tCol] += (20-10);
            model.getMatrix()[ttRow][ttCol] +=10;
            Hero h = currentGrid.removeHeroFromGrid();//从当前格子中移除玩家（将当前图形换成白底）
            Box b = targetGrid.removeBoxFromGrid();
            targetGrid.setHeroInGrid(h);//在目标单元格图形改为“英雄”图形
            ttargetGrid.setBoxInGrid(b);
            h.setRow(tRow);
            h.setCol(tCol);
            return true;

        }
        return false;
    }

    //todo: add other methods such as loadGame, saveGame...

}
