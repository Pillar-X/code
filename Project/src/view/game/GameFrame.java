package view.game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Vector;

import Data.GameArchive.DeserializeGame;
import Data.GameArchive.SerializeGame;
import SetUp.SetUpFrame;
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
    private JButton moveBackBtn;
    private int levelNumber;
    private String pathway;

    private JLabel stepLabel;
    private GamePanel gamePanel;
    private MapMatrix mapMatrix;

    private JButton upBtn;
    private JButton downBtn;
    private JButton leftBtn;
    private JButton rightBtn;
    private JButton deleteBtn;
    private JComboBox loadComboBox;
    private JTextField saveNameText;
    private JLabel saveNameLabel;


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
        Image img = tk.getImage("PictureResource/LOGO.png");
        setIconImage(img);//设置图标
        this.restartBtn = FrameUtil.createButton(this, "Restart", new Point(gamePanel.getWidth() + 80, 120), 80, 50);
        this.loadBtn = FrameUtil.createButton(this, "Load", new Point(gamePanel.getWidth() + 80, 210), 80, 50);
        this.returnBtn = FrameUtil.createButton(this, "Return", new Point(gamePanel.getWidth() + 80, 300), 80, 50);//返回按钮
        this.saveBtn = FrameUtil.createButton(this, "Save", new Point(gamePanel.getWidth() + 80, 390), 80, 50);
        this.upBtn = FrameUtil.createButton(this, "↑", new Point(gamePanel.getWidth() + 200, 120), 80, 50);
        this.downBtn = FrameUtil.createButton(this, "↓", new Point(gamePanel.getWidth() + 200, 210), 80, 50);
        this.leftBtn = FrameUtil.createButton(this, "←", new Point(gamePanel.getWidth() + 200, 300), 80, 50);
        this.rightBtn = FrameUtil.createButton(this, "→", new Point(gamePanel.getWidth() + 200, 390), 80, 50);//上下左右移动按钮
        this.moveBackBtn = FrameUtil.createButton(this,"Move Back",new Point(gamePanel.getWidth()+320,120),120,50);
        this.deleteBtn = FrameUtil.createButton(this,"Delete",new Point(gamePanel.getWidth()+440,210),80,50);
        this.stepLabel = FrameUtil.createJLabel(this, "Start", new Font("serif", Font.ITALIC, 22), new Point(gamePanel.getWidth() + 80, 70), 180, 50);
        this.saveNameText = FrameUtil.createJTextField(this,new Point(gamePanel.getWidth()+340,390),140,50);


        tryAddLoadComboBox();//看有没有存档，如果有就把下拉框加入到界面中

        gamePanel.setStepLabel(stepLabel);
        gamePanel.renewStepsLabel();

        this.moveBackBtn.addActionListener(e -> {
            gamePanel.MoveBack();
            gamePanel.requestFocusInWindow();
        });

        if(loadComboBox != null) {
            loadComboBox.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    gamePanel.requestFocusInWindow();
                }
            });
        }



        this.restartBtn.addActionListener(e -> {
            gameController.restartGame();
            gamePanel.requestFocusInWindow();//enable key listener
        });

        this.saveBtn.addActionListener(e->{
            if(saveNameText.getText().length()>12){
                this.saveNameLabel = FrameUtil.createJLabel(this,new Point(gamePanel.getWidth()+340,415),200,70,"No more than 12 words");
                saveNameLabel.setVisible(true);
                this.repaint();
            }
            else {

                if(this.saveNameLabel!=null){
                    this.saveNameLabel.setVisible(false);
                    this.repaint();
                }
                pathway = SetUpFrame.getSavePath() + "/level" + this.levelNumber + ".ser";
                ArrayList<MapMatrix> mapMatrixList = new ArrayList<>();


                try {
                    mapMatrixList = DeserializeGame.deserializeGame(pathway, this.levelNumber, SetUpFrame.getUsername());
                    this.mapMatrix.setFinalStep(gamePanel.getSteps());
                    //让新加入的mapMatrix的cnt成为数组中最大的
                    if (saveNameText.getText().equals("")) {//如果没有自定义名称就自动生成
                        int tmp = 1;
                        for (MapMatrix m : mapMatrixList) {
                            if (m.getCnt() >= tmp) tmp = m.getCnt() + 1;
                        }
                        this.mapMatrix.setCnt(tmp);
                    } else {//如果自定义了就按自定义的名称显示
                        this.mapMatrix.setCnt(0);
                        this.mapMatrix.setSaveName(saveNameText.getText());
                        saveNameText.setText("");
                    }

                    mapMatrixList.add(this.mapMatrix.clone());
                    SerializeGame.serializeGame(pathway, mapMatrixList);

                    tryAddLoadComboBox();
                    this.repaint();

                } catch (IOException | ClassNotFoundException ex) {
                    System.out.println("未成功反序列化，说明文件还不存在，直接序列化一个新文件");

                    mapMatrixList.add(this.mapMatrix.clone());
                    try {
                        SerializeGame.serializeGame(pathway, mapMatrixList);
                        tryAddLoadComboBox();
                    } catch (IOException exc) {
                        throw new RuntimeException(exc);
                    }
                }
            }

            gamePanel.requestFocusInWindow();//enable key listener


        });

        this.deleteBtn.addActionListener(e ->  {
            if(loadComboBox != null){
                //获取当前下拉框中选项
                MapMatrix selectedItem = (MapMatrix) loadComboBox.getSelectedItem();
                if (selectedItem != null) {
                    // 从 JComboBox 中删除选项
                    DefaultComboBoxModel<MapMatrix> model = (DefaultComboBoxModel<MapMatrix>) loadComboBox.getModel();
                    int selectedIndex = loadComboBox.getSelectedIndex();
                    model.removeElement(selectedItem);
                    //System.out.println("删除后下拉框中还有 "+loadComboBox.getItemCount());
                    ArrayList<MapMatrix> mapMatrixList = new ArrayList<>();
                    try {
                        mapMatrixList = DeserializeGame.deserializeGame(pathway,levelNumber,SetUpFrame.getUsername());

                    } catch (IOException | ClassNotFoundException ex) {

                    }
                    if(!mapMatrixList.isEmpty()) {
                        mapMatrixList.remove(mapMatrixList.size() - selectedIndex-1);//把对应的mapMatrix从数组中删除
                        // 由于下拉框从后往前显示数组，删除下拉框中索引i，相当于索引n-i-1处的mapMatrix
                        System.out.println("删完后，数组长度为："+mapMatrixList.size());
                    }
                    try {
                        SerializeGame.serializeGame(pathway,mapMatrixList);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }

                }
            }
            gamePanel.requestFocusInWindow();
        });

        this.loadBtn.addActionListener(e -> {

            pathway = SetUpFrame.getSavePath()+"/level"+this.levelNumber+".ser";
            ArrayList<MapMatrix> mapMatrixList = new ArrayList<>();
            try {
                mapMatrixList = DeserializeGame.deserializeGame(pathway,this.levelNumber,SetUpFrame.getUsername());
                System.out.println("反序列化进mapMatrixList");

            } catch (IOException | ClassNotFoundException ex) {
                //System.out.println("反序列化错误 ");
                //JOptionPane.showMessageDialog(this,"failed to load.","Warning",JOptionPane.WARNING_MESSAGE);
                gamePanel.requestFocusInWindow();
            }

            if(mapMatrixList.isEmpty()){
                JOptionPane.showMessageDialog(this,"The GameArchive is empty","Warning",JOptionPane.INFORMATION_MESSAGE);
            }
            else {
                gamePanel.RemoveBoxAndHero();
                gamePanel.repaint();

                MapMatrix lastMapMatrix = mapMatrixList.get(mapMatrixList.size()-loadComboBox.getSelectedIndex()-1);//取出选中的对象

                for (int i = 0; i < lastMapMatrix.getMatrix().length; i++) {
                    System.arraycopy(lastMapMatrix.getMatrix()[i], 0, this.mapMatrix.getMatrix()[i], 0, lastMapMatrix.getMatrix()[0].length);
                }
                gamePanel.setSteps(lastMapMatrix.getFinalStep() - 1);
                gamePanel.afterMove();
                gamePanel.ResetBoxAndHero();

                //若加载了存档，则清空原来的moveBackList()
                gamePanel.getMoveBackList().clear();
                this.mapMatrix.setFinalStep(gamePanel.getSteps());
                gamePanel.getMoveBackList().add(this.mapMatrix.clone());
            }

            gamePanel.requestFocusInWindow();//enable key listener
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

    public void tryAddLoadComboBox (){//如果文件内有存档，就显示出下拉框
        if(loadComboBox!=null){
        this.remove(loadComboBox);//每次存档先把原来的下拉框移除，编辑好再重新加入
        this.repaint();
        }
        System.out.println("尝试加入下拉框");
        pathway = SetUpFrame.getSavePath()+"/level"+this.levelNumber+".ser";
        ArrayList<MapMatrix> try_mapMatrixList = new ArrayList<>();

        try {
            try_mapMatrixList = DeserializeGame.deserializeGame(pathway,this.levelNumber,SetUpFrame.getUsername());

            if(!try_mapMatrixList.isEmpty()){
                Collections.reverse(try_mapMatrixList);
                loadComboBox = FrameUtil.createComboBox(this,new Point(gamePanel.getWidth()+320,210),120,50,try_mapMatrixList);

                System.out.println("成功加入下拉框");
                this.repaint();
                this.requestFocusInWindow();
                gamePanel.requestFocusInWindow();
            }
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println("下拉框尝试反序列化失败");
        }
    }

    public JTextField getSaveNameText() {
        return saveNameText;
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }
}


