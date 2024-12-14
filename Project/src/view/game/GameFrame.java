package view.game;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import java.util.Vector;

import Data.GameArchive.AutoSerialize;
import Data.GameArchive.DeserializeGame;
import Data.GameArchive.SerializeGame;
import Data.Vector2D;
import SetUp.SetUpFrame;
import controller.ButtonController;
import controller.FrameController;
import controller.GameController;
import controller.MusicController;
import Data.GameArchive.*;
import model.Direction;
import model.MapMatrix;
import view.FrameUtil;
import view.ending.WinFrame;
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
    private JLabel minutesLabel;
    private JLabel secondsLabel;
    private JLabel noteLabel;
    private JLabel bestStepLabel;
    private JLabel bestTimeLabel;
    private MapMatrix BestMapMatrix;
    private WinFrame winFrame;


    public GameFrame(int width, int height, MapMatrix mapMatrix, int levelNumber) {


        this.levelNumber = levelNumber;
        this.mapMatrix = mapMatrix;
        this.setTitle("Level " + levelNumber);
        this.setLayout(null);
        if(levelNumber!=6 && levelNumber!=7){
        this.setSize(width, height);
        FrameController.setGameFrame(this);
        gamePanel = new GamePanel(mapMatrix);
        gamePanel.setLocation(30, height / 2 - gamePanel.getHeight() / 2);
        this.add(gamePanel);
        //gamePanel.setFocusable(true);

        this.gameController = new GameController(gamePanel, mapMatrix);
        Toolkit tk = Toolkit.getDefaultToolkit();
        Image img = tk.getImage("PictureResource/LOGO.png");
        setIconImage(img);//设置图标
        this.restartBtn = ButtonController.createButton(this, "Restart", new Point(gamePanel.getWidth() + 400 - 80, 280), 160, 100, "");
        this.loadBtn = ButtonController.createButton(this, "Load", new Point(480-80, 30), 80, 50, "");
        this.returnBtn = ButtonController.createButton(this, "Return", new Point(640-80, 30), 100, 50, "");//返回按钮
        this.saveBtn = ButtonController.createButton(this, "Save", new Point(280-80, 30), 80, 50, "");
        this.upBtn = ButtonController.createButton(this, "↑", new Point(gamePanel.getWidth() + 300 - 60, 360 + 80), 80 * 2, 50 * 2, "");
        this.downBtn = ButtonController.createButton(this, "↓", new Point(gamePanel.getWidth() + 300 - 60, 460 + 80), 80 * 2, 50 * 2, "");
        this.leftBtn = ButtonController.createButton(this, "←", new Point(gamePanel.getWidth() + 140 - 60, 410 + 80), 80 * 2, 50 * 2, "");
        this.rightBtn = ButtonController.createButton(this, "→", new Point(gamePanel.getWidth() + 460 - 60, 410 + 80), 80 * 2, 50 * 2, "");//上下左右移动按钮
        this.moveBackBtn = ButtonController.createButton(this, "StepBack", new Point(gamePanel.getWidth() + 80 + 80, 280), 160, 100, "");
        this.deleteBtn = ButtonController.createButton(this, "Delete", new Point(560-80, 30), 80, 50, "");
        this.stepLabel = FrameUtil.createJLabel(this, "Start", new Font("serif", Font.ITALIC, 42), new Point(gamePanel.getWidth() + 160-60+50+20, 140), 200, 100);
        this.saveNameText = FrameUtil.createJTextField(this, new Point(140-80, 30), 140, 50);
        this.secondsLabel = ButtonController.createJLabel(this, new Point(gamePanel.getWidth() +280+60-60+120, 140), 200, 100, "00'0\"");
        this.minutesLabel = ButtonController.createJLabel(this, new Point(gamePanel.getWidth() +280-60, 140), 200, 100, "");
        this.bestStepLabel = FrameUtil.createJLabel(this, new Point(gamePanel.getWidth() + 160-60, 100), 200, 50, "Best: null");
        this.bestTimeLabel = FrameUtil.createJLabel(this, new Point(gamePanel.getWidth() + 350, 100), 300, 50, "Time: null");
        FrameController.setGameFrame(this);
        gamePanel.setBasicTime(mapMatrix.getBasicTime());
        SetSeconds(mapMatrix.getBasicTime());
        Font font = new Font("New Roman", Font.PLAIN, 28);
        Font font1 = new Font("New Roman", Font.PLAIN, 16);
        this.stepLabel.setFont(font);
        this.bestStepLabel.setFont(font);
        this.bestTimeLabel.setFont(font);
        this.minutesLabel.setFont(font);
        this.secondsLabel.setFont(font);
        this.restartBtn.setFont(font);
        this.loadBtn.setFont(font1);
        this.saveBtn.setFont(font1);
        this.moveBackBtn.setFont(font);
        this.upBtn.setFont(font);
        this.downBtn.setFont(font);
        this.leftBtn.setFont(font);
        this.rightBtn.setFont(font);
        this.deleteBtn.setFont(font1);
        this.saveNameText.setFont(font1);
        this.returnBtn.setFont(font1);
        tryAddLoadComboBox();//看有没有存档，如果有就把下拉框加入到界面中

        gamePanel.setStepLabel(stepLabel);
        gamePanel.renewStepsLabel();
        if (Objects.equals(SetUpFrame.getUsername(), "youke")){
            this.saveBtn.setVisible(false);
            this.loadBtn.setVisible(false);
            this.deleteBtn.setVisible(false);
            this.saveNameText.setVisible(false);
            this.loadComboBox.setVisible(false);
            this.bestStepLabel.setVisible(false);
            this.bestTimeLabel.setVisible(false);
        }//如果是游客，就不显示存档和加载按钮
        try {
            BestMapMatrix = DeserializeRecord.deserializeRecord(SetUpFrame.getRecordPath() + "level" + this.getLevelNumber() + ".ser");
            if (BestMapMatrix != null) {
                //if(bestTimeLabel == null) System.out.println("最佳时间标签为空");
                //else System.out.println("最佳时间标签存在");
                bestStepLabel.setText("Best: Steps:  "+BestMapMatrix.getFinalStep() );
                bestTimeLabel.setText("Time:  "+BestMapMatrix.getBasicTime() / 600 + "'" + (BestMapMatrix.getBasicTime() % 600) / 10 + "." + BestMapMatrix.getBasicTime() % 10 + "\"");
                this.repaint();
            }
        } catch (IOException | ClassNotFoundException e) {

        }

        //点击页面的其它地方，取消对saveNameText和loadComboBox的焦点，把焦点给到gamePanel
        this.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (!saveNameText.getBounds().contains(e.getPoint()) || loadComboBox.getBounds().contains(e.getPoint())) {
                    saveNameText.setFocusable(false);
                    if (loadComboBox != null) loadComboBox.setFocusable(false);
                    gamePanel.requestFocusInWindow();
                    saveNameText.setFocusable(true);
                    if (loadComboBox != null) loadComboBox.setFocusable(true);
                } else {
                    saveNameText.setFocusable(true);
                }
            }
        });

        saveNameText.addFocusListener(new FocusAdapter() {
            public void focusLost(FocusEvent e) {

            }
        });


        this.moveBackBtn.addActionListener(e -> {
            MusicController.playClickSound();
            gamePanel.MoveBack();
            gamePanel.requestFocusInWindow();
        });

        if (loadComboBox != null) {
            loadComboBox.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    gamePanel.requestFocusInWindow();
                }
            });
        }


        this.restartBtn.addActionListener(e -> {
            MusicController.playClickSound();
            gamePanel.RestartTimer();
            gameController.restartGame();
            gamePanel.requestFocusInWindow();//enable key listener
        });

        this.saveBtn.addActionListener(e -> {
            MusicController.playClickSound();
            this.mapMatrix.setBasicTime(gamePanel.getBasicTime());
            System.out.println("储存的mapMatrix的基本时间为" + this.mapMatrix.getBasicTime());

            if (saveNameText.getText().length() > 12) {
                this.saveNameLabel = FrameUtil.createJLabel(this, new Point(gamePanel.getWidth() + 340, 415), 200, 70, "No more than 12 words");
                saveNameLabel.setVisible(true);
                this.repaint();
            } else {

                if (this.saveNameLabel != null) {
                    this.saveNameLabel.setVisible(false);
                    this.repaint();
                }
                pathway = SetUpFrame.getSavePath() + "/level" + this.levelNumber + ".ser";
                ArrayList<MapMatrix> mapMatrixList = new ArrayList<>();


                try {
                    mapMatrixList = DeserializeGame.deserializeGame(pathway, this.levelNumber, SetUpFrame.getUsername());
                    //以下处理存档名称
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
                    //一下处理存档名称
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

        this.deleteBtn.addActionListener(e -> {
            MusicController.playClickSound();
            if (loadComboBox != null) {
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
                        mapMatrixList = DeserializeGame.deserializeGame(pathway, levelNumber, SetUpFrame.getUsername());

                    } catch (IOException | ClassNotFoundException ex) {

                    }
                    if (!mapMatrixList.isEmpty()) {
                        mapMatrixList.remove(mapMatrixList.size() - selectedIndex - 1);//把对应的mapMatrix从数组中删除
                        // 由于下拉框从后往前显示数组，删除下拉框中索引i，相当于索引n-i-1处的mapMatrix
                        System.out.println("删完后，数组长度为：" + mapMatrixList.size());
                    }
                    try {
                        SerializeGame.serializeGame(pathway, mapMatrixList);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }

                }
            }
            gamePanel.requestFocusInWindow();
        });

        this.loadBtn.addActionListener(e -> {
            gamePanel.StopTimer();
            MusicController.playClickSound();
            pathway = SetUpFrame.getSavePath() + "/level" + this.levelNumber + ".ser";
            ArrayList<MapMatrix> mapMatrixList = new ArrayList<>();
            try {
                mapMatrixList = DeserializeGame.deserializeGame(pathway, this.levelNumber, SetUpFrame.getUsername());
                System.out.println("反序列化进mapMatrixList");

            } catch (IOException | ClassNotFoundException ex) {
                //System.out.println("反序列化错误 ");
                //JOptionPane.showMessageDialog(this,"failed to load.","Warning",JOptionPane.WARNING_MESSAGE);
                gamePanel.requestFocusInWindow();
            }

            if (mapMatrixList.isEmpty()) {
                JOptionPane.showMessageDialog(this, "The GameArchive is empty", "Warning", JOptionPane.INFORMATION_MESSAGE);
            } else {
                gamePanel.RemoveBoxAndHero();
                gamePanel.repaint();

                MapMatrix lastMapMatrix = mapMatrixList.get(mapMatrixList.size() - loadComboBox.getSelectedIndex() - 1);//取出选中的对象
                gamePanel.setBasicTime(lastMapMatrix.getBasicTime());
                SetSeconds(gamePanel.getBasicTime());

                for (int i = 0; i < lastMapMatrix.getMatrix().length; i++) {
                    System.arraycopy(lastMapMatrix.getMatrix()[i], 0, this.mapMatrix.getMatrix()[i], 0, lastMapMatrix.getMatrix()[0].length);
                }
                this.mapMatrix.setBasicTime(lastMapMatrix.getBasicTime());
                gamePanel.setSteps(lastMapMatrix.getFinalStep());
                gamePanel.renewStepsLabel();

                gamePanel.ResetBoxAndHero();

                //若加载了存档，则清空原来的moveBackList()
                gamePanel.getMoveBackList().clear();
                this.mapMatrix.setFinalStep(gamePanel.getSteps());
                gamePanel.getMoveBackList().add(this.mapMatrix.clone());
            }

            gamePanel.requestFocusInWindow();//enable key listener
        });

        this.returnBtn.addActionListener(e -> {
            //MusicController.stopMusic();
            MusicController.playClickSound();
            gamePanel.StopTimer();
            try {
                AutoSerialize.autoserialize(SetUpFrame.getAutoSavePath() + "level" + this.levelNumber + ".ser", this.getGamePanel().getMapMatrix().clone());
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            FrameController.returnLevelFrame(this);
            gamePanel.requestFocusInWindow();//返回按钮的监听器
            FrameController.getMusicController().startMusic();
        });


        this.upBtn.addActionListener(e -> {
            MusicController.playClickSound();
            gamePanel.doMoveUp();

            gamePanel.requestFocusInWindow();//把焦点还给gamePanel让其对键盘事件继续监听
        });
        this.downBtn.addActionListener(e -> {
            MusicController.playClickSound();
            gamePanel.doMoveDown();

            gamePanel.requestFocusInWindow();
        });
        this.leftBtn.addActionListener(e -> {
            MusicController.playClickSound();
            gamePanel.doMoveLeft();

            gamePanel.requestFocusInWindow();
        });
        this.rightBtn.addActionListener(e -> {
            MusicController.playClickSound();
            gamePanel.doMoveRight();

            gamePanel.requestFocusInWindow();
        });
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // 设置背景
        JLabel lblBackground = new JLabel(); // 创建一个标签组件对象
        BufferedImage bufferedImage = null;
            String path;
        if(levelNumber == 6 || levelNumber == 7){
            path = "PictureResource/forest.png";
        }else{
            path = "PictureResource/background.png";
        }
        try {
            bufferedImage = ImageIO.read(new File(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ImageIcon icon = new ImageIcon(bufferedImage); // 创建背景图片对象
        lblBackground.setIcon(icon); // 设置标签组件要显示的图标
        lblBackground.setBounds(0, 0, icon.getIconWidth(), icon.getIconHeight()); // 设置组件的显示位置及大小
        this.getContentPane().add(lblBackground); // 将组件添加到面板中
        }
        else if(levelNumber == 6)
        {
            this.setSize(width, height);
            this.setLevelNumber(6);
            FrameController.setGameFrame(this);

            noteLabel = FrameUtil.createJLabel(FrameController.getGameFrame(),new Point(280,10),200,30,"");

            gamePanel = new GamePanel(mapMatrix);
            gamePanel.setLocation(width / 2 - 100, height / 2 - gamePanel.getHeight() / 2);
            this.add(gamePanel);
            this.gameController = new GameController(gamePanel, mapMatrix);
            Toolkit tk = Toolkit.getDefaultToolkit();
            Image img = tk.getImage("PictureResource/LOGO.png");
            setIconImage(img);//设置图标

            this.returnBtn = ButtonController.createButton(this, "Return", new Point(gamePanel.getWidth() + 200, 300), 80, 50, "");//返回按钮
            //this.stepLabel = FrameUtil.createJLabel(this, "Start", new Font("serif", Font.ITALIC, 22), new Point(gamePanel.getWidth() + 80, 70), 180, 50);
            //this.remove(stepLabel);


            FrameController.setGameFrame(this);
            this.returnBtn.addActionListener(e -> {
                //MusicController.stopMusic();
                MusicController.playClickSound();

                FrameController.returnLevelFrame(this);
                gamePanel.requestFocusInWindow();//返回按钮的监听器
                //MusicController musicController = new MusicController("MusicResource/BGM2.wav");
                //musicController.setMusicVolume(-1.0);
            });


            this.setLocationRelativeTo(null);
            this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            // 设置背景
            JLabel lblBackground = new JLabel(); // 创建一个标签组件对象
            BufferedImage bufferedImage = null;
            String path = "PictureResource/forest_2.png";
            try {
                bufferedImage = ImageIO.read(new File(path));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            ImageIcon icon = new ImageIcon(bufferedImage); // 创建背景图片对象
            lblBackground.setIcon(icon); // 设置标签组件要显示的图标
            lblBackground.setBounds(0, 0, icon.getIconWidth(), icon.getIconHeight()); // 设置组件的显示位置及大小
            this.getContentPane().add(lblBackground); // 将组件添加到面板中
        }
        else{
            this.setSize(width, height);
            this.setLevelNumber(7);
            FrameController.setGameFrame(this);

            gamePanel = new GamePanel(mapMatrix);
            gamePanel.setLocation(0, 0);
            this.add(gamePanel);
            this.gameController = new GameController(gamePanel, mapMatrix);
            Toolkit tk = Toolkit.getDefaultToolkit();
            Image img = tk.getImage("PictureResource/LOGO.png");
            setIconImage(img);//设置图标

            this.returnBtn = ButtonController.createButton(this, "Return", new Point(gamePanel.getWidth(), 450), 160, 100, "");//返回按钮

            this.returnBtn.addActionListener(e -> {
                MusicController.playClickSound();
                FrameController.returnLevelFrame(this);
                gamePanel.requestFocusInWindow();//返回按钮的监听器
            });
            FrameController.setGameFrame(this);

            this.setLocationRelativeTo(null);
            this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            // 设置背景
            JLabel lblBackground = new JLabel(); // 创建一个标签组件对象
            BufferedImage bufferedImage = null;
            String path = "PictureResource/forest_2.png";
            try {
                bufferedImage = ImageIO.read(new File(path));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            ImageIcon icon = new ImageIcon(bufferedImage); // 创建背景图片对象
            lblBackground.setIcon(icon); // 设置标签组件要显示的图标
            lblBackground.setBounds(0, 0, icon.getIconWidth(), icon.getIconHeight()); // 设置组件的显示位置及大小
            this.getContentPane().add(lblBackground); // 将组件添加到面板中


        }
        }

    public void SetSeconds (int basic_time) {
        String tmp = (basic_time%600)/10+"."+basic_time%10;
        secondsLabel.setText(basic_time/600+"'"+tmp+ "\"");
    }

    public void SetMinutes(int basic_time){
        minutesLabel.setText(basic_time/600+" m");
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

            Collections.reverse(try_mapMatrixList);
            loadComboBox = FrameUtil.createComboBox(this,new Point(360-80,30),120,50,try_mapMatrixList);
            loadComboBox.setFont(new Font("serif", Font.ITALIC, 16));
            System.out.println("成功加入下拉框");
            this.repaint();
            this.requestFocusInWindow();
            gamePanel.requestFocusInWindow();

        } catch (IOException | ClassNotFoundException ex) {
            System.out.println("下拉框尝试反序列化失败,创建空下拉框");
            try_mapMatrixList = new ArrayList<>();
            loadComboBox = FrameUtil.createComboBox(this,new Point(360-80,30),120,50,try_mapMatrixList);
        }
    }

    public JTextField getSaveNameText() {
        return saveNameText;
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }

    public int getLevelNumber() {
        return levelNumber;
    }
    public JLabel getBestStepLabel() {
        return bestStepLabel;
    }

    public void setBestStepLabel(JLabel bestStepLabel) {
        this.bestStepLabel = bestStepLabel;
    }

    public JLabel getBestTimeLabel() {
        return bestTimeLabel;
    }
    public void setLevelNumber(int levelNumber) {
        this.levelNumber = levelNumber;
    }

    public void setBestTimeLabel(JLabel bestTimeLabel) {
        this.bestTimeLabel = bestTimeLabel;
    }
    public JLabel getNoteLabel() {
        return noteLabel;
    }
    }




