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
import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class LevelFrame extends JFrame {
    MusicController musicController;
//    String BGM = "BGM3";//背景音乐


    public LevelFrame(int width, int height) {
        this.setTitle("Level");
        this.setLayout(null);
        this.setSize(width, height);

        try{
            musicController = new MusicController("MusicResource/BGM2.wav");
            musicController.setMusicVolume(-1.0);
            FrameController.setMusicController(musicController);
        }catch (Exception e){
            System.out.println(e);
        }
//        MusicController.playMusic("MusicResource/BGM3.wav");//设置背景音乐

        //设置背景音乐（12.10注：换了个时间长点的音乐，防止夹断）

//        File bgMusicFile = new File("MusicResource/BGM2.wav");
//        if (bgMusicFile.exists()) {
//            Clip bgMusic ;
//            try {
//                bgMusic = AudioSystem.getClip();
//                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(bgMusicFile);
//                bgMusic.open(audioInputStream);
//            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
//                throw new RuntimeException(e);
//            }
//            bgMusic.loop(1);
//        }

        JButton level1Btn = ButtonController.createButton(this, "Level1", new Point(width/2-370, height / 2 -165-50+30), 200, 130,"");
        JButton level2Btn = ButtonController.createButton(this, "Level2", new Point(width/2-100, height / 2 - 165-50+30), 200, 130,"");
        JButton level3Btn = ButtonController.createButton(this, "Level3", new Point(width/2+170, height / 2 - 165-50+30), 200, 130,"");
        JButton level4Btn = ButtonController.createButton(this, "Level4", new Point(width/2-370+135, height / 2 + 35-50+30), 200, 130,"");
        JButton level5Btn = ButtonController.createButton(this, "Level5", new Point(width/2-100+135,height / 2 + 35-50+30), 200, 130,"");
        Font font = new Font("Times New Roman", Font.PLAIN, 42);
        level1Btn.setFont(font);
        level2Btn.setFont(font);
        level3Btn.setFont(font);
        level4Btn.setFont(font);
        level5Btn.setFont(font);
        JButton ReLoginBtn = ButtonController.createButton(this, "ReLogin", new Point(920, 10), 100, 30,"");
//        JButton SetUpBtn = ButtonController.createButton(this,"Set Up",new Point(800,10),100,30,"");
        Toolkit tk = Toolkit.getDefaultToolkit();
        Image img = tk.getImage("PictureResource/LOGO.png");
        setIconImage(img);//设置图标

//        SetUpBtn.addActionListener(l->{
//            MusicController.playClickSound();
//            SetUpFrame setUpFrame = new SetUpFrame(400,400,SetUpFrame.getUsername());
//            setUpFrame.setVisible(true);
//        });

        level1Btn.addActionListener(l->{musicController.stopMusic();
            //MusicController.stopMusic_Force();
            musicController.checkMusic();

            openLevel1();
        });
        level2Btn.addActionListener(l->{musicController.stopMusic();openLevel2();
            });
        level3Btn.addActionListener(l->{musicController.stopMusic();openLevel3();
            });
        level4Btn.addActionListener(l->{musicController.stopMusic();openLevel4();
            });
        level5Btn.addActionListener(l->{musicController.stopMusic();openLevel5();
            });

        ReLoginBtn.addActionListener(e -> {
            //musicController.stopMusic();
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
//    public void clearBGM(){
//        MusicController.stopMusic();
//    }
    public void openLevel1(){
//        FrameController.getLevelFrame().clearBGM();
        MusicController.playClickSound();
        //MusicController.stopMusic();
//        MusicController.playMusic("MusicResource/BGM2.wav");
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

        GameFrame gameFrame = new GameFrame(1200, 800, mapMatrix,1);

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
        gameFrame.getGamePanel().requestFocusInWindow();
        MusicController musicController1 = new MusicController("MusicResource/level1 - Stardew-Valley.wav");
        FrameController.setMusicController_level1(musicController1);


    }

    public void openLevel2(){
        MusicController.playClickSound();
//        MusicController.playMusic("MusicResource/BGM2.wav");
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


        GameFrame gameFrame = new GameFrame(1300, 800, mapMatrix,2);

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
        gameFrame.getGamePanel().requestFocusInWindow();
        MusicController musicController2 = new MusicController("MusicResource/level2-Minecraft.wav");
        FrameController.setMusicController_level2(musicController2);
    }

    public void openLevel3(){
        MusicController.playClickSound();
//        MusicController.playMusic("MusicResource/BGM2.wav");
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

        GameFrame gameFrame = new GameFrame(1300, 800, mapMatrix,3);

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
        gameFrame.getGamePanel().requestFocusInWindow();
        MusicController musicController3 = new MusicController("MusicResource/level3-Terraria.wav");
        FrameController.setMusicController_level3(musicController3);
    }

    public void openLevel4(){
        MusicController.playClickSound();
//        MusicController.playMusic("MusicResource/BGM4.wav");
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

        GameFrame gameFrame = new GameFrame(1300, 800, mapMatrix,4);

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
        gameFrame.getGamePanel().requestFocusInWindow();
        MusicController musicController4 = new MusicController("MusicResource/level4-Undertale.wav");
        FrameController.setMusicController_level4(musicController4);
    }

    public void openLevel5(){
        MusicController.playClickSound();
//        MusicController.playMusic("MusicResource/BGM4.wav");
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

        GameFrame gameFrame = new GameFrame(1400, 800, mapMatrix,5);

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
        gameFrame.getGamePanel().requestFocusInWindow();
        MusicController musicController5 = new MusicController("MusicResource/level5-Celeste.wav");
        FrameController.setMusicController_level5(musicController5);
    }
    public void openLevel6(){
        MusicController.playClickSound();
        MapMatrix mapMatrix;
        //try {
        //    mapMatrix = new MapMatrix(AutoDeserialize.autodeserialize(SetUpFrame.getAutoSavePath()+"level6.ser").getMatrix().clone());
        //    mapMatrix.setBasicTime(AutoDeserialize.autodeserialize(SetUpFrame.getAutoSavePath()+"level6.ser").getBasicTime());
        //    mapMatrix.setFinalStep(AutoDeserialize.autodeserialize(SetUpFrame.getAutoSavePath()+"level6.ser").getFinalStep());
//
        //} catch (IOException | ClassNotFoundException e) {
        mapMatrix = new MapMatrix(new int[][]{
                {1, 1, 1, 3, 1, 1, 1},
                {1, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 10, 1},
                {1, 0, 0, 0, 0, 0, 1},
                {1, 10, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 10, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 1},
                {1, 0, 10, 0, 0, 0, 1},
                {1, 0, 0, 0, 10, 0, 1},
                {1, 0, 10, 0, 0, 0, 1},
                {1, 0, 0, 10, 0, 10, 1},
                {1, 10, 0, 10, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 1},
                {1, 0, 10, 10, 10, 0, 1},
                {1, 0, 0, 0, 0, 10, 1},
                {1, 10, 0, 10, 0, 10, 1},
                {1, 0, 10, 0, 10, 0, 1},
                {1, 10, 10, 10, 0, 10, 1},
                {1, 10, 0, 20, 10, 10,1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1},}
        );
        mapMatrix.setCopymatrix(new int[][] {
                {1, 1, 1, 3, 1, 1, 1},
                {1, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 10, 1},
                {1, 0, 0, 0, 0, 0, 1},
                {1, 10, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 10, 0, 0, 1},
                {1, 0, 0, 0, 0, 10, 1},
                {1, 0, 10, 0, 0, 0, 1},
                {1, 10, 0, 0, 10, 10, 1},
                {1, 0, 10, 0, 0, 0, 1},
                {1, 0, 0, 10, 0, 10, 1},
                {1, 10, 0, 10, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 1},
                {1, 0, 10, 10, 10, 0, 1},
                {1, 0, 0, 0, 0, 10, 1},
                {1, 10, 0, 10, 0, 10, 1},
                {1, 0, 10, 0, 10, 0, 1},
                {1, 10, 10, 10, 0, 10, 1},
                {1, 10, 0, 20, 10, 10,1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1},}
        );

        GameFrame gameFrame = new GameFrame(600, 800, mapMatrix,6);

        this.setVisible(false);
        gameFrame.setVisible(true);
        gameFrame.getGamePanel().requestFocusInWindow();
        MusicController musicController6 = new MusicController("MusicResource/level6-Celeste.wav");
        FrameController.setMusicController_level6(musicController6);
    }

    public void openLevel7(){
        FrameController.getMusicController_level6().stopMusic();
        MapMatrix mapMatrix;
        //4:压力板
        //5:门方块
        //6:传送门

        mapMatrix = new MapMatrix(new int[][]{
                {1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 0, 0, 0, 10, 5, 5, 6, 1},
                {1, 0, 0, 0, 30, 0, 0, 0, 1},
                {1, 0, 4, 0, 0, 0, 0, 0, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 6, 0, 10, 1},
                {1, 4, 0, 0, 0, 0, 0, 4, 1},
                {1, 1, 1, 1, 5, 1, 1, 1, 1},
                {1, 1, 1, 1, 22, 1, 1, 1, 1,},
                {1, 1, 1, 1, 3, 1, 1, 1, 1}
        }
        );
        mapMatrix.setCopymatrix(new int[][] {
                {1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 0, 0, 0, 10, 5, 5, 6, 1},
                {1, 0, 0, 0, 30, 0, 0, 0, 1},
                {1, 0, 4, 0, 0, 0, 0, 0, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 6, 0, 10, 1},
                {1, 4, 0, 0, 0, 0, 0, 4, 1},
                {1, 1, 1, 1, 5, 1, 1, 1, 1},
                {1, 1, 1, 1, 22, 1, 1, 1, 1,},
                {1, 1, 1, 1, 3, 1, 1, 1, 1}}
        );

        GameFrame gameFrame = new GameFrame(1000,1025,mapMatrix,7);
        this.setVisible(false);
        gameFrame.setVisible(true);
        gameFrame.getGamePanel().requestFocusInWindow();
        MusicController musicController7 = new MusicController("MusicResource/level7-Undertale.wav");
        FrameController.setMusicController_level7(musicController7);
    }

    public void LevelChooser(int levelNumber){
        if(levelNumber==2) openLevel2();
        else if(levelNumber==3) openLevel3();
        else if(levelNumber == 4) openLevel4();
        else if(levelNumber == 5) openLevel5();
        else if(levelNumber == 6) openLevel6();
    }

    public MusicController getMusicController() {
        return musicController;
    }

    public void setMusicController(MusicController musicController) {
        this.musicController = musicController;
    }
    //public static FrameController getFrameController() {
     //   return frameController;
    //}
}
