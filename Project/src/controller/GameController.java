package controller;

import model.Direction;
import model.MapMatrix;
import view.game.Box;
import view.game.GamePanel;
import view.game.GridComponent;
import view.game.Hero;
import view.game.*;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * It is a bridge to combine GamePanel(view) and MapMatrix(model) in one game.
 * You can design several methods about the game logic in this class.
 */
public class GameController {
    private GamePanel gamePanel;
    private MapMatrix mapMatrix ;
    private int counter = 0;
    private int number=0;
    public GameController(GamePanel gamePanel, MapMatrix mapMatrix) {
        this.gamePanel = gamePanel;
        this.mapMatrix = mapMatrix;
        gamePanel.setController(this);
        int[][] map = mapMatrix.getMatrix();
        for(int i=0;i<map.length;i++){
            for(int j=0;j<map[0].length;j++){
                if(map[i][j]/10 ==1){
                    counter++;
                }
            }
        }

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
        if (map[tRow][tCol] == 0 || map[tRow][tCol] == 2 || map[tRow][tCol] == 4 ||map[tRow][tCol]==6) {
            mapMatrix.getMatrix()[row][col] -= 20;
            mapMatrix.getMatrix()[tRow][tCol] += 20;
            Hero h = currentGrid.removeHeroFromGrid();
            targetGrid.setHeroInGrid(h);
            h.setRow(tRow);
            h.setCol(tCol);
            h.loadImage(direction.toString().toLowerCase());//根据移动方向切换英雄朝向
            return true;
        }
        if ((map[tRow][tCol]==10||map[tRow][tCol]==12||map[tRow][tCol]==14||map[tRow][tCol]==16) && (map[ttRow][ttCol]==0 ||map[ttRow][ttCol]==2||map[ttRow][ttCol]==4||map[ttRow][ttCol]==6)) {

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

    public boolean secondDoMove(int row, int col,Direction direction){
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
        if (map[tRow][tCol] == 0 || map[tRow][tCol] == 2|| map[tRow][tCol] == 4||map[tRow][tCol]==6) {
            mapMatrix.getMatrix()[row][col] -= 30;
            mapMatrix.getMatrix()[tRow][tCol] += 30;
            SecondHero sech = currentGrid.removeSecondHeroFromGrid();
            targetGrid.setSecondHeroInGrid(sech);
            sech.setRow(tRow);
            sech.setCol(tCol);
            sech.loadImage(direction.toString().toLowerCase());//根据移动方向切换英雄朝向
            return true;
        }
        if ((map[tRow][tCol]==10||map[tRow][tCol]==12||map[tRow][tCol]==14||map[tRow][tCol]==16) && (map[ttRow][ttCol]==0 ||map[ttRow][ttCol]==2||map[ttRow][ttCol]==4||map[ttRow][ttCol]==6)) {

            mapMatrix.getMatrix()[row][col] -= 30;
            mapMatrix.getMatrix()[tRow][tCol] += (30 - 10);
            mapMatrix.getMatrix()[ttRow][ttCol] += 10;
            SecondHero sech = currentGrid.removeSecondHeroFromGrid();//从当前格子中移除玩家（将当前图形换成白底）
            Box b = targetGrid.removeBoxFromGrid();
            targetGrid.setSecondHeroInGrid(sech);//在目标单元格图形改为“英雄”图形
            ttargetGrid.setBoxInGrid(b);
            sech.setRow(tRow);
            sech.setCol(tCol);
            sech.loadImage(direction.toString().toLowerCase());//根据移动方向切换英雄朝向
            return true;
        }


        return false;
    }

    public boolean isGameWin(){
        if(FrameController.getGameFrame().getLevelNumber()!=7){
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
        }
        else{
            int map[][] = mapMatrix.getMatrix();
            for(int i=0;i<map.length;i++){
                for(int j=0;j<map[0].length;j++){
                    if(map[i][j]==12){
                        return true;
                    }
                }
            }//计算有多少个箱子
            return false;
        }

    }//判断游戏是否胜利
    public boolean isGameFail(){
        int count=0;
        int[][] map = mapMatrix.getMatrix();
        int[] boxList = new int[counter];
        for(int i=0;i<map.length;i++){
            for(int j=0;j<map[0].length;j++){
                if(map[i][j]/10 ==1){
                    number++;
                    boxList[number-1]=i*10+j;
                }
            }
        }
        number=0;

        for(int i=0;i<boxList.length;i++){
            if(isDeadLocked(i)){
                return true;
            }
            if(isLocked(i)){
                count++;
                if(count==boxList.length){
                    System.out.println("游戏失败");
                    return true;
                }
            }
        }
        return false;

    }
    public boolean isLocked(int num){
        int[][] map = mapMatrix.getMatrix();
        int[] boxList = new int[counter];
        for(int i=0;i<map.length;i++){
            for(int j=0;j<map[0].length;j++){
                if(map[i][j]/10 ==1){
                    number++;
                    boxList[number-1]=i*10+j;
                }
            }
        }
        number=0;
        int row = boxList[num] / 10;
        int col = boxList[num] % 10;
//        System.out.println("第"+num+"个箱子位置为"+row+","+col);
        if ((map[row+1][col] ==1||map[row+1][col] ==10||map[row+1][col] ==12) && (map[row][col+1] ==1||map[row][col+1] ==10||map[row][col+1] ==12)
                || (map[row+1][col] ==1||map[row+1][col] ==10||map[row+1][col] ==12) && (map[row][col-1] ==1||map[row][col-1] ==10||map[row][col-1] ==12)
                || (map[row-1][col] ==1||map[row-1][col] ==10||map[row-1][col] ==12) && (map[row][col+1] ==1||map[row][col+1] ==10||map[row][col+1] ==12)
                || (map[row-1][col] ==1||map[row-1][col] ==10||map[row-1][col] ==12) && (map[row][col-1] ==1||map[row][col-1] ==10||map[row][col-1] ==12) ){
            System.out.println("第"+num+"个箱子已锁定");
            return true;
        }
        return false;
    }
    public boolean isDeadLocked(int num){
        int[][] map = mapMatrix.getMatrix();
        int[] boxList = new int[counter];
        for(int i=0;i<map.length;i++){
            for(int j=0;j<map[0].length;j++){
                if(map[i][j]/10 ==1){
                    number++;
                    boxList[number-1]=i*10+j;
                }
            }
        }
        number=0;
        int row = boxList[num] / 10;
        int col = boxList[num] % 10;
//        System.out.println("第"+num+"个箱子位置为"+row+","+col);
        if ((map[row+1][col] ==1 && map[row][col+1] ==1)
                || (map[row+1][col] ==1 && map[row][col-1] ==1)
                || (map[row-1][col] ==1 && map[row][col+1] ==1)
                || (map[row-1][col] ==1 && map[row][col-1] ==1)) {
            if(map[row][col]%10 !=2){
                return true;
            }
        }
        return false;
    }
    public boolean isPressurePlateBePressed(int row, int col){
        int[][] map = mapMatrix.getMatrix();
        if(map[row][col]==14 || map[row][col]==24 || map[row][col]==34){
            return true;
        }
        return false;
    }
    public void dealWithPressurePlate(int row, int col,int X,int Y,int number){
        int[][] map = mapMatrix.getMatrix();
        if(isPressurePlateBePressed(row,col)){
            switch (map[X][Y]/10){
                case 0:
                    map[X][Y] = 0;
                    break;
                case 1:
                    map[X][Y] = 10;
                    break;
                case 2:
                    map[X][Y] = 20;
                    break;
                case 3:
                    map[X][Y] = 30;
                    break;
            }
            mapMatrix.setMatrix(map);
            GridComponent gridComponent = gamePanel.getGridComponent(X,Y);
            gridComponent.setId(0);
            gamePanel.isPressurePlateOpen[number] = 1;
//            System.out.println("["+row+","+col+"]"+"隐藏门已打开");
//            JLabel label = new JLabel();
//            ImageIcon icon = new ImageIcon("PictureResource/wall_broken.gif");
//            label.setIcon(icon);
//            label.setBounds(X*90+2, Y*90+2, 90, 90);
//            FrameController.getGameFrame().add(label);//添加叶落动画
//            MusicController.playMusic("MusicResource/wall_broken.wav");//添加叶落声效
        }else if(!isPressurePlateBePressed(row,col) && map[X][Y]/10 !=1 && map[X][Y]/10 !=2 && map[X][Y]/10 !=3){
            map[X][Y] = 5;//XY指隐藏门的坐标
            mapMatrix.setMatrix(map);
            GridComponent gridComponent = gamePanel.getGridComponent(X,Y);
            gridComponent.setId(5);
            gamePanel.isPressurePlateOpen[number] = 0;
//            System.out.println("["+row+","+col+"]"+"隐藏门关闭");
        }
    }
    //todo: add other methods such as loadGame, saveGame...

}
