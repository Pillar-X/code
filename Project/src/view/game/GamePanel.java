package view.game;

import controller.FrameController;
import controller.GameController;
import controller.MusicController;
import model.Direction;
import model.MapMatrix;
import view.FrameUtil;
import view.ending.LoseFrame;
import view.ending.WinFrame;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import Data.GameArchive.*;
import SetUp.SetUpFrame;

/**
 * It is the subclass of ListenerPanel, so that it should implement those four methods: do move left, up, down ,right.
 * The class contains a grids, which is the corresponding GUI view of the matrix variable in MapMatrix.
 */
public class GamePanel extends ListenerPanel {

    private GridComponent[][] grids;
    private WinFrame winFrame;
    private LoseFrame loseFrame;
    private MapMatrix mapMatrix;
    private GameController gameController;
    private JLabel stepLabel;
    private int steps;
    private final int GRID_SIZE = 90;//单位格子边长
    private Image image;
    private Hero hero;
    private SecondHero secondHero;
    private String direction;
    private ArrayList<MapMatrix> moveBackList;
    private int basicTime=0;
    private Timer timer;
    private boolean letTimerStart = false;
    private TimerTask task;
    public boolean isWin=false;
    private MapMatrix BestMapMatrix;
    boolean letThisMapMatrixBest;
    private GameFrame gameFrame = FrameController.getGameFrame();
    public GamePanel(MapMatrix mapMatrix) {
        if (gameFrame.getLevelNumber() != 6 && gameFrame.getLevelNumber() !=7) {
            this.winFrame = new WinFrame(1000, 500);
            winFrame.setVisible(false);
            this.loseFrame = new LoseFrame(1000, 500);
            loseFrame.setVisible(false);
            this.setVisible(true);
            this.setFocusable(true);
            this.setLayout(null);
            this.setSize(mapMatrix.getWidth() * GRID_SIZE + 4, mapMatrix.getHeight() * GRID_SIZE + 4);
            this.mapMatrix = mapMatrix;
            this.grids = new GridComponent[mapMatrix.getHeight()][mapMatrix.getWidth()];
            this.moveBackList = new ArrayList<>();
            initialGame();

        } else if(gameFrame.getLevelNumber()==6){
            int tmp_GridSize = 35;
            this.setVisible(true);
            this.setFocusable(true);
            this.setLayout(null);
            this.setSize(mapMatrix.getWidth() * tmp_GridSize + 3, mapMatrix.getHeight() * tmp_GridSize + 3);
            this.mapMatrix = mapMatrix;
            this.grids = new GridComponent[mapMatrix.getHeight()][mapMatrix.getWidth()];
            initialLevel6Game();

        }
        else{
            this.winFrame = new WinFrame(1000, 500);
            winFrame.setVisible(false);
            this.setVisible(true);
            this.setFocusable(true);
            this.setLayout(null);
            this.setSize(mapMatrix.getWidth() * GRID_SIZE + 4, mapMatrix.getHeight() * GRID_SIZE + 4);
            this.mapMatrix = mapMatrix;
            this.grids = new GridComponent[mapMatrix.getHeight()][mapMatrix.getWidth()];
            initialLevel7Game();

        }
    }


    public void initialGame() {
        if(mapMatrix.getFinalStep() ==0) this.steps = 0;
        else this.steps = mapMatrix.getFinalStep();
        System.out.println("初始化步数为"+steps);


        //必须对mapMatrix进行深拷贝再传入ArrayList
        mapMatrix.setFinalStep(steps);
        moveBackList.add(mapMatrix.clone());

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
    public void initialLevel6Game(){
        int tmpGrid_size = 35;
        for (int i = 0; i < grids.length; i++) {
            for (int j = 0; j < grids[i].length; j++) {
                //Units digit maps to id attribute in GridComponent. (The no change value)
                grids[i][j] = new GridComponent(i, j, mapMatrix.getId(i, j) % 10, tmpGrid_size);
                System.out.println("大小为"+tmpGrid_size);
                grids[i][j].setLocation(j * tmpGrid_size + 1, i * tmpGrid_size + 1);
                //Ten digit maps to Box or Hero in corresponding location in the GridComponent. (Changed value)
                switch (mapMatrix.getId(i, j) / 10) {
                    case 1:
                        grids[i][j].setBoxInGrid(new Box(tmpGrid_size - 10, tmpGrid_size - 10));
                        break;
                    case 2:
                        this.hero = new Hero(tmpGrid_size - 14, tmpGrid_size - 14, i, j);
                        grids[i][j].setHeroInGrid(hero);
                        break;
                }
                this.add(grids[i][j]);
            }
        }
        this.repaint();


    }
    public void initialLevel7Game(){


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

                    case 3:
                        this.secondHero = new SecondHero(GRID_SIZE -16, GRID_SIZE -16,i,j);
                        grids[i][j].setSecondHeroInGrid(secondHero);
                        break;
                }
                this.add(grids[i][j]);
            }
        }
        this.repaint();


    }

    public void restart(){
        //从零开始，计量步数
        moveBackList.clear();
        this.steps = 0;
        renewStepsLabel();
        //从原来的图中移除箱子和英雄
        RemoveBoxAndHero();
        this.repaint();
        mapMatrix.copy();//把地图矩阵恢复到初始状态

        //把地图矩阵更新后，重新放置好箱子和英雄
        ResetBoxAndHero();
        mapMatrix.setFinalStep(steps);
        moveBackList.add(mapMatrix.clone());
    }

    public void MoveBack(){
        if(moveBackList.size()>1){
            moveBackList.removeLast();//先把当前记录的MapMatrix数据删除
            this.steps = moveBackList.getLast().getFinalStep();//设置标签为数组中最后一个MapMatrix的lastStep值
            renewStepsLabel();
            RemoveBoxAndHero();
            this.mapMatrix.setMatrix(moveBackList.getLast().clone().getMatrix());//设置mapMatrix中的地图矩阵Matrix为数组中最后一个元素的Matrix值
            ResetBoxAndHero();
            this.repaint();
            System.out.println("The moveBackList have "+moveBackList.size());
        } else{
            System.out.println("The moveBackList is empty ");
        }
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

    public ArrayList<MapMatrix> getMoveBackList() {
        return moveBackList;
    }

    public void setMoveBackList(ArrayList<MapMatrix> moveBackList) {
        this.moveBackList = moveBackList;
    }

    @Override
    public void doMoveRight() {
        System.out.println("Click VK_RIGHT");
        MusicController.playMoveSound();//播放移动声音
        if (gameController.doMove(hero.getRow(), hero.getCol(), Direction.RIGHT)) {
            this.afterMove();
        }
    }

    @Override
    public void doMoveLeft() {
        System.out.println("Click VK_LEFT");
        MusicController.playMoveSound();//播放移动声音
        if(gameController.doMove(hero.getRow(), hero.getCol(), Direction.LEFT)){
            this.afterMove();
        }
    }

    @Override
    public void doMoveUp() {
        System.out.println("Click VK_Up");
        MusicController.playMoveSound();//播放移动声音
       if( gameController.doMove(hero.getRow(), hero.getCol(), Direction.UP)){
           this.afterMove();
       }
    }

    @Override
    public void doMoveDown() {
        System.out.println("Click VK_DOWN");
        MusicController.playMoveSound();//播放移动声音
        if(gameController.doMove(hero.getRow(), hero.getCol(), Direction.DOWN)){
            this.afterMove();
        }
    }
    public void doInteract(){
        System.out.println("Click VK_F");

        if(FrameController.getGameFrame().getLevelNumber()==6 && hero.getRow() ==1 && hero.getCol()==3){
            this.gameFrame.setVisible(false);
            FrameController.getLevelFrame().openLevel7();

        }
    }
    public void secondDoMoveUp(){
        System.out.println("Click VK_W");
        MusicController.playMoveSound();//播放移动声音
        if(gameController.secondDoMove(secondHero.getRow(),secondHero.getCol(),Direction.UP)){
            this.afterMove();
        }
    }
    public void secondDoMoveDown(){
        System.out.println("Click VK_S");
        MusicController.playMoveSound();//播放移动声音
        if(gameController.secondDoMove(secondHero.getRow(),secondHero.getCol(),Direction.DOWN)){
            this.afterMove();
        }
    }
    public void secondDoMoveLeft(){
        System.out.println("Click VK_A");
        MusicController.playMoveSound();//播放移动声音
        if(gameController.secondDoMove(secondHero.getRow(),secondHero.getCol(),Direction.LEFT)){
            this.afterMove();
        }
    }
    public void secondDoMoveRight(){
        System.out.println("Click VK_D");
        MusicController.playMoveSound();//播放移动声音
        if(gameController.secondDoMove(secondHero.getRow(),secondHero.getCol(),Direction.RIGHT)){
            this.afterMove();
        }
    }
    public void StopTimer(){
        mapMatrix.setBasicTime(basicTime);
        if(letTimerStart){
            task.cancel();
            timer.cancel();
            letTimerStart = false;

        }
    }

    public int getBasicTime() {
        return basicTime;
    }

    public void RestartTimer(){
        if(letTimerStart){
            task.cancel();
            timer.cancel();
            letTimerStart = false;
            basicTime = 0;
            FrameController.getGameFrame().SetSeconds(basicTime);
            System.out.println("计时任务停止");
        }
        else{
            basicTime = 0;
            FrameController.getGameFrame().SetSeconds(basicTime);
        }
    }

    public void afterMove() {
        if(FrameController.getGameFrame().getLevelNumber()!=6 && FrameController.getGameFrame().getLevelNumber()!=7) {
            if (!letTimerStart) {
                timer = new Timer();
                task = new TimerTask() {
                    @Override
                    public void run() {
                        basicTime++;
                        mapMatrix.setBasicTime(basicTime);
                        FrameController.getGameFrame().SetSeconds(basicTime);
                        //System.out.printf("%d ",basicTime);
                    }
                };
                timer.scheduleAtFixedRate(task, 0, 100);
                letTimerStart = true;

            }

            this.steps++;
            mapMatrix.setFinalStep(steps);
            moveBackList.add(mapMatrix.clone());
            this.stepLabel.setText(String.format("Step: %d", this.steps));
//        System.out.println(gameController.isGameWin());
//        System.out.println(gameController.isGameFail());
//        System.out.println(gameController.isDeadLocked(0));
//        System.out.println(gameController.isDeadLocked(1));
            if (gameController.isGameWin()) {
                StopTimer();
                this.getMapMatrix();
                try {
                    BestMapMatrix = DeserializeRecord.deserializeRecord(SetUpFrame.getRecordPath() + "level" + FrameController.getGameFrame().getLevelNumber() + ".ser");

                    if (BestMapMatrix == null) {
                        letThisMapMatrixBest = true;
                    } else {
                        if (BestMapMatrix.getFinalStep() > this.getMapMatrix().getFinalStep()) {
                            letThisMapMatrixBest = true;
                        } else if (BestMapMatrix.getFinalStep() == this.getMapMatrix().getFinalStep() && BestMapMatrix.getBasicTime() > this.getMapMatrix().getBasicTime()) {
                            letThisMapMatrixBest = true;
                        } else {
                            letThisMapMatrixBest = false;
                        }
                    }
                    if (letThisMapMatrixBest) {
                        SerializeRecord.serializeRecord(SetUpFrame.getRecordPath() + "level" + FrameController.getGameFrame().getLevelNumber() + ".ser", this.getMapMatrix());
                    }


                } catch (IOException | ClassNotFoundException e) {
                    try {
                        letThisMapMatrixBest = true;
                        SerializeRecord.serializeRecord(SetUpFrame.getRecordPath() + "level" + FrameController.getGameFrame().getLevelNumber() + ".ser", this.getMapMatrix());
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }

                if (letThisMapMatrixBest) {
                    FrameController.getGameFrame().getBestStepLabel().setText(this.getMapMatrix().getFinalStep() + " Steps");
                    FrameController.getGameFrame().getBestTimeLabel().setText(this.getMapMatrix().getBasicTime() / 600 + "min " + (this.getMapMatrix().getBasicTime() % 600) / 10 + "." + this.getMapMatrix().getBasicTime() % 10 + "seconds");
                    FrameController.getGameFrame().repaint();
                    System.out.println("当前为最好记录，最佳步数：" + this.getMapMatrix().getFinalStep() + "最快时间：" + this.getMapMatrix().getBasicTime());
                    this.winFrame.setThisRecordLabel(FrameUtil.createJLabel(winFrame, new Point(400, 800), 200, 60, "It is the best record!   Steps: " + this.getMapMatrix().getFinalStep() + "  Time：" + this.getMapMatrix().getBasicTime() / 600 + "min " + (this.getMapMatrix().getBasicTime() % 600) / 10 + "." + this.getMapMatrix().getBasicTime() % 10 + "seconds"));

                } else {
                    FrameController.getGameFrame().getBestStepLabel().setText(BestMapMatrix.getFinalStep() + " Steps");
                    FrameController.getGameFrame().getBestTimeLabel().setText(BestMapMatrix.getBasicTime() / 600 + "min " + (BestMapMatrix.getBasicTime() % 600) / 10 + "." + BestMapMatrix.getBasicTime() % 10 + "seconds");
                    FrameController.getGameFrame().repaint();
                    this.winFrame.setBestRecordLabel(FrameUtil.createJLabel(winFrame, new Point(400, 800), 200, 60, "The best record:    Steps: " + BestMapMatrix.getFinalStep() + "  Time：" + BestMapMatrix.getBasicTime() / 600 + "min " + (BestMapMatrix.getBasicTime() % 600) / 10 + "." + BestMapMatrix.getBasicTime() % 10 + "seconds"));
                    this.winFrame.setThisRecordLabel(FrameUtil.createJLabel(winFrame, new Point(400, 900), 200, 60, "This record:        Steps: " + this.getMapMatrix().getFinalStep() + "  Time：" + this.getMapMatrix().getBasicTime() / 600 + "min " + (this.getMapMatrix().getBasicTime() % 600) / 10 + "." + this.getMapMatrix().getBasicTime() % 10 + "seconds"));

                    System.out.println("最佳步数：" + BestMapMatrix.getFinalStep() + "最快时间:" + BestMapMatrix.getBasicTime());
                }

                this.getMapMatrix().copy();
                this.getMapMatrix().setBasicTime(0);
                steps = 0;
                this.getMapMatrix().setFinalStep(0);
                System.out.println("胜利后矩阵步数重置为" + this.getMapMatrix().getFinalStep());
                try {
                    AutoSerialize.autoserialize(SetUpFrame.getAutoSavePath() + "level" + FrameController.getGameFrame().getLevelNumber() + ".ser", this.getMapMatrix().clone());
                    System.out.println("胜利后进行Auto序列化成功");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                winFrame.setVisible(true);
                FrameController.getGameFrame().setVisible(false);
                try {
                    MusicController.stopMusic();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                MusicController.playWinSound();//播放胜利声音
            }
            if (gameController.isGameFail()) {
                loseFrame.setVisible(true);
                try {
                    MusicController.stopMusic();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                MusicController.playLoseSound();//播放失败声音
            }
        }

    }

    public void renewStepsLabel(){
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

    public MapMatrix getMapMatrix() {
        mapMatrix.setFinalStep(steps);
        return mapMatrix;

    }
    public void setBasicTime(int basicTime) {
        this.basicTime = basicTime;
    }
}
