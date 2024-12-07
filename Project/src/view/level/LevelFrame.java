package view.level;

import Data.GameArchive.AutoDeserialize;
import Data.GameArchive.AutoSerialize;
import SetUp.SetUpFrame;
import controller.ButtonController;
import controller.FrameController;
import controller.MusicController;
import model.MapMatrix;
import view.FrameUtil;
import view.game.GameFrame;
import view.game.GamePanel;
import view.login.LoginFrame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class LevelFrame extends JFrame {
    String BGM = "BGM3";//背景音乐


    public LevelFrame(int width, int height) {
        this.setTitle("Level");
        this.setLayout(null);
        this.setSize(width, height);
        try{
            MusicController.stopMusic();//清除原有音乐
        }catch (Exception e){
            System.out.println(e);
        }
        MusicController.playMusic("MusicResource/"+BGM+".wav");//设置背景音乐
        JButton level1Btn = ButtonController.createButton(this, "Level1", new Point(30, height / 2 - 130), 80, 60,"");
        JButton level2Btn = ButtonController.createButton(this, "Level2", new Point(140, height / 2 - 130), 80, 60,"");
        JButton level3Btn = ButtonController.createButton(this, "Level3", new Point(250, height / 2 - 130), 80, 60,"");
        JButton level4Btn = ButtonController.createButton(this, "Level4", new Point(30, height / 2 +10), 80, 60,"");
        JButton level5Btn = ButtonController.createButton(this, "Level5", new Point(140, height / 2 +10), 80, 60,"");
        JButton ReLoginBtn = ButtonController.createButton(this, "ReLogin", new Point(250, height / 2 +10), 120, 60,"");
        JButton SetUpBtn = ButtonController.createButton(this,"Set Up",new Point(300,10),80,20,"");
        Toolkit tk = Toolkit.getDefaultToolkit();
        java.awt.Image img = tk.getImage("PictureResource/LOGO.png");
        setIconImage(img);//设置图标

        SetUpBtn.addActionListener(l->{
            MusicController.playClickSound();
            SetUpFrame setUpFrame = new SetUpFrame(400,400,SetUpFrame.getUsername());
            setUpFrame.setVisible(true);
        });

        level1Btn.addActionListener(l->{
            MusicController.playClickSound();
            MapMatrix mapMatrix;
            try {
                mapMatrix = new MapMatrix(AutoDeserialize.autodeserialize(SetUpFrame.getAutoSavePath()+"level1.ser").getMatrix().clone());
                mapMatrix.setBasicTime(AutoDeserialize.autodeserialize(SetUpFrame.getAutoSavePath()+"level1.ser").getBasicTime());
                mapMatrix.setFinalStep(AutoDeserialize.autodeserialize(SetUpFrame.getAutoSavePath()+"level1.ser").getFinalStep());

            } catch (IOException | ClassNotFoundException e) {
                mapMatrix = new MapMatrix(new int[][]{
                        {1, 1, 1, 1, 1, 1},
                        {1, 20, 0, 0, 0, 1},
                        {1, 0, 0, 10, 2, 1},
                        {1, 0, 2, 10, 0, 1},
                        {1, 1, 1, 1, 1, 1},
                });}
                mapMatrix.setCopymatrix(new int[][]{
                        {1, 1, 1, 1, 1, 1},
                        {1, 20, 0, 0, 0, 1},
                        {1, 0, 0, 10, 2, 1},
                        {1, 0, 2, 10, 0, 1},
                        {1, 1, 1, 1, 1, 1},});

            GameFrame gameFrame = new GameFrame(900, 500, mapMatrix,1);

            gameFrame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    try {
                        AutoSerialize.autoserialize(SetUpFrame.getAutoSavePath()+"level1.ser",gameFrame.getGamePanel().getMapMatrix().clone());
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    //FrameController.returnLevelFrame(gameFrame);

                    gameFrame.dispose();

                }
            });
            this.setVisible(false);
            gameFrame.setVisible(true);
        });


        level2Btn.addActionListener(l->{
            MusicController.playClickSound();
                    MapMatrix mapMatrix;
                    try {
                        mapMatrix = new MapMatrix(AutoDeserialize.autodeserialize(SetUpFrame.getAutoSavePath()+"level2.ser").getMatrix().clone());
                        mapMatrix.setBasicTime(AutoDeserialize.autodeserialize(SetUpFrame.getAutoSavePath()+"level2.ser").getBasicTime());
                        mapMatrix.setFinalStep(AutoDeserialize.autodeserialize(SetUpFrame.getAutoSavePath()+"level2.ser").getFinalStep());

                    } catch (IOException | ClassNotFoundException e) {
                        mapMatrix = new MapMatrix(new int[][]{
                                {1, 1, 1, 1, 1, 1, 0},
                                {1, 20, 0, 0, 0, 1, 1},
                                {1, 0, 10, 10, 0, 0, 1},
                                {1, 0, 1, 2, 0, 2, 1},
                                {1, 0, 0, 0, 0, 0, 1},
                                {1, 1, 1, 1, 1, 1, 1}
                        });}
                    mapMatrix.setCopymatrix(new int[][]{
                            {1, 1, 1, 1, 1, 1, 0},
                            {1, 20, 0, 0, 0, 1, 1},
                            {1, 0, 10, 10, 0, 0, 1},
                            {1, 0, 1, 2, 0, 2, 1},
                            {1, 0, 0, 0, 0, 0, 1},
                            {1, 1, 1, 1, 1, 1, 1}});


                            GameFrame gameFrame = new GameFrame(900, 500, mapMatrix,2);

                    gameFrame.addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosing(WindowEvent e) {
                            try {
                                AutoSerialize.autoserialize(SetUpFrame.getAutoSavePath()+"level2.ser",gameFrame.getGamePanel().getMapMatrix().clone());

                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                            //FrameController.returnLevelFrame(gameFrame);

                            gameFrame.dispose();

                        }
                    });
                    this.setVisible(false);
                    gameFrame.setVisible(true);
        });

        level3Btn.addActionListener(l->{
            MusicController.playClickSound();
            MapMatrix mapMatrix;
            try {
                mapMatrix = new MapMatrix(AutoDeserialize.autodeserialize(SetUpFrame.getAutoSavePath()+"level3.ser").getMatrix().clone());
                mapMatrix.setBasicTime(AutoDeserialize.autodeserialize(SetUpFrame.getAutoSavePath()+"level3.ser").getBasicTime());
                mapMatrix.setFinalStep(AutoDeserialize.autodeserialize(SetUpFrame.getAutoSavePath()+"level3.ser").getFinalStep());

            } catch (IOException | ClassNotFoundException e) {
                mapMatrix = new MapMatrix(new int[][]{

                        {0, 0, 1, 1, 1, 1, 0},
                        {1, 1, 1, 0, 0, 1, 0},
                        {1, 20, 0, 2, 10, 1, 1},
                        {1, 0, 0, 0, 10, 0, 1},
                        {1, 0, 1, 2, 0, 0, 1},
                        {1, 0, 0, 0, 0, 0, 1},
                        {1, 1, 1, 1, 1, 1, 1}});
                }
                mapMatrix.setCopymatrix(new int[][]{
                        {0, 0, 1, 1, 1, 1, 0},
                        {1, 1, 1, 0, 0, 1, 0},
                        {1, 20, 0, 2, 10, 1, 1},
                        {1, 0, 0, 0, 10, 0, 1},
                        {1, 0, 1, 2, 0, 0, 1},
                        {1, 0, 0, 0, 0, 0, 1},
                        {1, 1, 1, 1, 1, 1, 1}});

                        GameFrame gameFrame = new GameFrame(900, 500, mapMatrix,3);

                gameFrame.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        try {
                            AutoSerialize.autoserialize(SetUpFrame.getAutoSavePath()+"level3.ser",gameFrame.getGamePanel().getMapMatrix().clone());

                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                        //FrameController.returnLevelFrame(gameFrame);

                        gameFrame.dispose();

                    }
                });
                this.setVisible(false);
                gameFrame.setVisible(true);
        });

        level4Btn.addActionListener(l->{
            MusicController.playClickSound();
            MapMatrix mapMatrix;
            try {
                mapMatrix = new MapMatrix(AutoDeserialize.autodeserialize(SetUpFrame.getAutoSavePath()+"level4.ser").getMatrix().clone());
                mapMatrix.setBasicTime(AutoDeserialize.autodeserialize(SetUpFrame.getAutoSavePath()+"level4.ser").getBasicTime());
                mapMatrix.setFinalStep(AutoDeserialize.autodeserialize(SetUpFrame.getAutoSavePath()+"level4.ser").getFinalStep());

            } catch (IOException | ClassNotFoundException e) {
                mapMatrix = new MapMatrix(new int[][]{
                        {0, 1, 1, 1, 1, 1, 0},
                        {1, 1, 20, 0, 0, 1, 1},
                        {1, 0, 0, 1, 0, 0, 1},
                        {1, 0, 10, 12, 10, 0, 1},
                        {1, 0, 0, 2, 0, 0, 1},
                        {1, 1, 0, 2, 0, 1, 1},
                        {0, 1, 1, 1, 1, 1, 0}}
                );}
                mapMatrix.setCopymatrix(new int[][]{
                        {0, 1, 1, 1, 1, 1, 0},
                        {1, 1, 20, 0, 0, 1, 1},
                        {1, 0, 0, 1, 0, 0, 1},
                        {1, 0, 10, 12, 10, 0, 1},
                        {1, 0, 0, 2, 0, 0, 1},
                        {1, 1, 0, 2, 0, 1, 1},
                        {0, 1, 1, 1, 1, 1, 0}}
                );

                        GameFrame gameFrame = new GameFrame(900, 500, mapMatrix,4);

                gameFrame.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        try {
                            AutoSerialize.autoserialize(SetUpFrame.getAutoSavePath()+"level4.ser",gameFrame.getGamePanel().getMapMatrix().clone());

                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                        //FrameController.returnLevelFrame(gameFrame);

                        gameFrame.dispose();

                    }
                });
                this.setVisible(false);
                gameFrame.setVisible(true);
        });

        level5Btn.addActionListener(l->{
            MusicController.playClickSound();
            MapMatrix mapMatrix;
            try {
                mapMatrix = new MapMatrix(AutoDeserialize.autodeserialize(SetUpFrame.getAutoSavePath()+"level5.ser").getMatrix().clone());
                mapMatrix.setBasicTime(AutoDeserialize.autodeserialize(SetUpFrame.getAutoSavePath()+"level5.ser").getBasicTime());
                mapMatrix.setFinalStep(AutoDeserialize.autodeserialize(SetUpFrame.getAutoSavePath()+"level5.ser").getFinalStep());

            } catch (IOException | ClassNotFoundException e) {
                mapMatrix = new MapMatrix(new int[][]{
                        {1, 1, 1, 1, 1, 1, 0, 0},
                        {1, 0, 0, 0, 0, 1, 1, 1},
                        {1, 0, 0, 0, 2, 2, 0, 1},
                        {1, 0, 10, 10, 10, 20, 0, 1},
                        {1, 0, 0, 1, 0, 2, 0, 1},
                        {1, 1, 1, 1, 1, 1, 1, 1}}
                );}
            mapMatrix.setCopymatrix(new int[][] {
                    {1, 1, 1, 1, 1, 1, 0, 0},
                    {1, 0, 0, 0, 0, 1, 1, 1},
                    {1, 0, 0, 0, 2, 2, 0, 1},
                    {1, 0, 10, 10, 10, 20, 0, 1},
                    {1, 0, 0, 1, 0, 2, 0, 1},
                    {1, 1, 1, 1, 1, 1, 1, 1},
            });

            GameFrame gameFrame = new GameFrame(1000, 500, mapMatrix,5);

            gameFrame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    try {
                        AutoSerialize.autoserialize(SetUpFrame.getAutoSavePath()+"level5.ser",gameFrame.getGamePanel().getMapMatrix().clone());

                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    //FrameController.returnLevelFrame(gameFrame);

                    gameFrame.dispose();

                }
            });
            this.setVisible(false);
            gameFrame.setVisible(true);

        });

        ReLoginBtn.addActionListener(e -> {
            MusicController.playClickSound();
            FrameController.returnLoginFrame(this);
            requestFocusInWindow();
        });//增加重新登录界面按钮
        // 设置背景
        JLabel lblBackground = new JLabel(); // 创建一个标签组件对象
        BufferedImage bufferedImage = null;
        String path = "PictureResource/background.png";
        try {
            bufferedImage = ImageIO.read(new File(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ImageIcon icon = new ImageIcon(bufferedImage); // 创建背景图片对象
        lblBackground.setIcon(icon); // 设置标签组件要显示的图标
        lblBackground.setBounds(0, 0, icon.getIconWidth(), icon.getIconHeight()); // 设置组件的显示位置及大小
        this.getContentPane().add(lblBackground); // 将组件添加到面板中
        FrameController.setLevelFrame(this);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
    //public static FrameController getFrameController() {
     //   return frameController;
    //}
}
