package view.game;

import controller.GameController;
import model.Direction;
import model.MapMatrix;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * It is the subclass of ListenerPanel, so that it should implement those four methods: do move left, up, down ,right.
 * The class contains a grids, which is the corresponding GUI view of the matrix variable in MapMatrix.
 */
public class GamePanel extends ListenerPanel {

    private GridComponent[][] grids;
    private MapMatrix mapMatrix;
    private GameController gameController;
    private JLabel stepLabel;
    private int steps;
    private final int GRID_SIZE = 50;
    private Image image;
    private Hero hero;
    private String direction;

    public GamePanel(MapMatrix mapMatrix) {
        this.setVisible(true);
        this.setFocusable(true);
        this.setLayout(null);
        this.setSize(mapMatrix.getWidth() * GRID_SIZE + 4, mapMatrix.getHeight() * GRID_SIZE + 4);
        this.mapMatrix = mapMatrix;
        this.grids = new GridComponent[mapMatrix.getHeight()][mapMatrix.getWidth()];
        initialGame();

    }

    public void initialGame() {
        this.steps = 0;
        for (int i = 0; i < grids.length; i++) {
            for (int j = 0; j < grids[i].length; j++) {
                //Units digit maps to id attribute in GridComponent. (The no change value)
                grids[i][j] = new GridComponent(i, j, mapMatrix.getId(i, j) % 10, this.GRID_SIZE);
                grids[i][j].setLocation(j * GRID_SIZE + 2, i * GRID_SIZE + 2);
                //Ten digit maps to Box or Hero in corresponding location in the GridComponent. (Changed value)
                switch (mapMatrix.getId(i, j) / 10) {
                    case 1:
                        grids[i][j].setBoxInGrid(new Box(GRID_SIZE - 10, GRID_SIZE - 10));
                        break;
                    case 2:
                        this.hero = new Hero(GRID_SIZE - 16, GRID_SIZE - 16, i, j);
                        grids[i][j].setHeroInGrid(hero);
                        break;
                }
                this.add(grids[i][j]);
            }
        }
        this.repaint();
    }

    public void restart(){
        //从零开始，计量步数
        this.steps = -1;
        afterMove();
        //从原来的图中移除箱子和英雄
        RemoveBoxAndHero();
        this.repaint();
        mapMatrix.copy();//把地图矩阵恢复到初始状态
        //把地图矩阵更新后，重新放置好箱子和英雄
        ResetBoxAndHero();
    }

    public void RemoveBoxAndHero(){
        for (int i = 0; i < grids.length; i++) {
            for (int j = 0; j < grids[i].length; j++) {
                switch (mapMatrix.getId(i, j) / 10) {
                    case 1:
                        Box b = grids[i][j].removeBoxFromGrid();
                        break;
                    case 2:
                        hero = grids[i][j].removeHeroFromGrid();
                        break;
                }
            }
        }
    }

    public void ResetBoxAndHero(){
        for (int i = 0; i < grids.length; i++) {
            for (int j = 0; j < grids[i].length; j++) {
                switch (mapMatrix.getId(i, j) / 10) {
                    case 1:
                        grids[i][j].setBoxInGrid(new Box(GRID_SIZE - 10, GRID_SIZE - 10));
                        break;
                    case 2:
                        grids[i][j].setHeroInGrid(hero);
                        hero.setRow(i);
                        hero.setCol(j);
                        break;
                }
                this.add(grids[i][j]);
            }
        }
    }





    @Override
    public void doMoveRight() {
        System.out.println("Click VK_RIGHT");
        if (gameController.doMove(hero.getRow(), hero.getCol(), Direction.RIGHT)) {
            this.afterMove();
        }
    }

    @Override
    public void doMoveLeft() {
        System.out.println("Click VK_LEFT");
        if(gameController.doMove(hero.getRow(), hero.getCol(), Direction.LEFT)){
            this.afterMove();
        }
    }

    @Override
    public void doMoveUp() {
        System.out.println("Click VK_Up");
       if( gameController.doMove(hero.getRow(), hero.getCol(), Direction.UP)){
           this.afterMove();
       }
    }

    @Override
    public void doMoveDown() {
        System.out.println("Click VK_DOWN");
        if(gameController.doMove(hero.getRow(), hero.getCol(), Direction.DOWN)){
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
        this.gameController = controller;
    }

    public GridComponent getGridComponent(int row, int col) {
        return grids[row][col];
    }

    public int getSteps() {
        return steps;
    }

    public void setSteps(int steps) {
        this.steps = steps;
    }
}
