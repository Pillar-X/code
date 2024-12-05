package view.game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Vector;

import Data.GameArchive.DeserializeGame;
import Data.GameArchive.SerializeGame;
import controller.FrameController;
import controller.GameController;
import model.Direction;
import model.MapMatrix;
import view.FrameUtil;
import view.level.LevelFrame;


public class GameFrame extends JFrame {



    private final JButton returnBtn;
    private GameController gameController;

    private JButton restartBtn;
    private JButton loadBtn;
    private JButton saveBtn;
    private int levelNumber;
    private String pathway;

    private JLabel stepLabel;
    private GamePanel gamePanel;
    private MapMatrix mapMatrix;

    private JButton upBtn;
    private JButton downBtn;
    private JButton leftBtn;
    private JButton rightBtn;

    public GameFrame(int width, int height, MapMatrix mapMatrix,int levelNumber) {
        this.levelNumber = levelNumber;
        this.mapMatrix = mapMatrix;
        this.setTitle("Level "+levelNumber);
        this.setLayout(null);
        this.setSize(width, height);
        gamePanel = new GamePanel(mapMatrix);
        gamePanel.setLocation(30, height / 2 - gamePanel.getHeight() / 2);
        this.add(gamePanel);
        this.gameController = new GameController(gamePanel, mapMatrix);
        Toolkit tk = Toolkit.getDefaultToolkit();
        java.awt.Image img = tk.getImage("PictureResource/LOGO.png");
        setIconImage(img);//设置图标
        this.restartBtn = FrameUtil.createButton(this, "Restart", new Point(gamePanel.getWidth() + 80, 120), 80, 50);
        this.loadBtn = FrameUtil.createButton(this, "Load", new Point(gamePanel.getWidth() + 80, 210), 80, 50);
        this.returnBtn = FrameUtil.createButton(this, "Return", new Point(gamePanel.getWidth() + 80, 300), 80, 50);//返回按钮
        this.saveBtn = FrameUtil.createButton(this, "Save", new Point(gamePanel.getWidth() + 80, 390), 80, 50);
        this.upBtn = FrameUtil.createButton(this, "↑", new Point(gamePanel.getWidth() + 200, 120), 80, 50);
        this.downBtn = FrameUtil.createButton(this, "↓", new Point(gamePanel.getWidth() + 200, 210), 80, 50);
        this.leftBtn = FrameUtil.createButton(this, "←", new Point(gamePanel.getWidth() + 200, 300), 80, 50);
        this.rightBtn = FrameUtil.createButton(this, "→", new Point(gamePanel.getWidth() + 200, 390), 80, 50);//上下左右移动按钮
        this.stepLabel = FrameUtil.createJLabel(this, "Start", new Font("serif", Font.ITALIC, 22), new Point(gamePanel.getWidth() + 80, 70), 180, 50);
        gamePanel.setStepLabel(stepLabel);

        this.restartBtn.addActionListener(e -> {
            gameController.restartGame();
            gamePanel.requestFocusInWindow();//enable key listener
        });
        this.loadBtn.addActionListener(e -> {
            if(pathway == null){//如果为空，则弹出pathway输入框
                pathway= JOptionPane.showInputDialog(this, "Input path:");
                if(pathway == null){//如果点击了取消，pathway仍然是null,此时直接返回，并交回控制权即可
                    gamePanel.requestFocusInWindow();
                    return;
                }
            }

            ArrayList<MapMatrix> mapMatrixList;
            try {
                mapMatrixList = DeserializeGame.deserializeGame(pathway,this.levelNumber);
            } catch (IOException | ClassNotFoundException ex) {
                System.out.println("反序列化错误 ");
                JOptionPane.showMessageDialog(this,"failed to load.","Warning",JOptionPane.WARNING_MESSAGE);
                pathway = null;
                gamePanel.requestFocusInWindow();
                return;
            }

            //以下代码用于按存档中的matrix设置游戏画面

            if(mapMatrixList.isEmpty()){
                JOptionPane.showMessageDialog(this,"The GameArchive is empty","Warning",JOptionPane.INFORMATION_MESSAGE);
            }
            else {
                gamePanel.RemoveBoxAndHero();
                gamePanel.repaint();
                MapMatrix lastMapMatrix = mapMatrixList.getLast();//取出最后一个存档的对象
                for (int i = 0; i < lastMapMatrix.getMatrix().length; i++) {
                    System.arraycopy(lastMapMatrix.getMatrix()[i], 0, this.mapMatrix.getMatrix()[i], 0, lastMapMatrix.getMatrix()[0].length);
                }
                gamePanel.setSteps(lastMapMatrix.getFinalStep() - 1);
                gamePanel.afterMove();
                gamePanel.ResetBoxAndHero();
            }

            System.out.println(pathway);
            gamePanel.requestFocusInWindow();//enable key listener
        });

        this.saveBtn.addActionListener(e -> {
            if(pathway == null) {//如果为空，则弹出pathway输入框
                 pathway = JOptionPane.showInputDialog(this, "Input path:");
                if(pathway == null){//如果点击了取消，pathway仍然是null,此时直接返回，并交回控制权即可
                    gamePanel.requestFocusInWindow();
                    return;
                }
            }  //把当前步数和关卡传入mapMatrix。这样只用记录mapMatrix对象即可还原此刻的游戏状态
                this.mapMatrix.setFinalStep(this.gamePanel.getSteps());
                this.mapMatrix.setLevelNumber(this.levelNumber);

                try {
                    SerializeGame.serializeGame(pathway, this.mapMatrix);
                } catch (IOException ex) {
                    System.out.println("序列化错误 ");
                    JOptionPane.showMessageDialog(this,"The pathway is illegal, failed to save.","Warning",JOptionPane.WARNING_MESSAGE);
                    pathway = null;
                    gamePanel.requestFocusInWindow();
                    return;
                }

            gamePanel.requestFocusInWindow();
        });

        this.returnBtn.addActionListener(e -> {
            FrameController.returnLevelFrame(this);
            gamePanel.requestFocusInWindow();//返回按钮的监听器
        });


        this.upBtn.addActionListener(e -> {
            gamePanel.doMoveUp();
            gamePanel.requestFocusInWindow();//把焦点还给gamePanel让其对键盘事件继续监听
        });
        this.downBtn.addActionListener(e -> {
            gamePanel.doMoveDown();
            gamePanel.requestFocusInWindow();
        });
        this.leftBtn.addActionListener(e -> {
            gamePanel.doMoveLeft();
            gamePanel.requestFocusInWindow();
        });
        this.rightBtn.addActionListener(e -> {
            gamePanel.doMoveRight();
            gamePanel.requestFocusInWindow();
        });
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
