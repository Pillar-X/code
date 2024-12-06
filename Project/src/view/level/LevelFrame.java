package view.level;

import controller.FrameController;
import model.MapMatrix;
import view.FrameUtil;
import view.game.GameFrame;
import view.game.GamePanel;
import view.login.LoginFrame;

import javax.swing.*;
import java.awt.*;

public class LevelFrame extends JFrame {



    public LevelFrame(int width, int height) {
        this.setTitle("Level");
        this.setLayout(null);
        this.setSize(width, height);
        JButton level1Btn = FrameUtil.createButton(this, "Level1", new Point(30, height / 2 - 130), 80, 60);
        JButton level2Btn = FrameUtil.createButton(this, "Level2", new Point(140, height / 2 - 130), 80, 60);
        JButton level3Btn = FrameUtil.createButton(this, "Level3", new Point(250, height / 2 - 130), 80, 60);
        JButton level4Btn = FrameUtil.createButton(this, "Level4", new Point(30, height / 2 +10), 80, 60);
        JButton level5Btn = FrameUtil.createButton(this, "Level5", new Point(140, height / 2 +10), 80, 60);
        JButton ReLoginBtn = FrameUtil.createButton(this, "ReLogin", new Point(250, height / 2 +10), 120, 60);
        Toolkit tk = Toolkit.getDefaultToolkit();
        java.awt.Image img = tk.getImage("PictureResource/LOGO.png");
        setIconImage(img);//设置图标
        level1Btn.addActionListener(l->{

            MapMatrix mapMatrix = new MapMatrix(new int[][]{
                    {1, 1, 1, 1, 1, 1},
                    {1, 20, 0, 0, 0, 1},
                    {1, 0, 0, 10, 2, 1},
                    {1, 0, 2, 10, 0, 1},
                    {1, 1, 1, 1, 1, 1},
            });
            GameFrame gameFrame = new GameFrame(900, 500, mapMatrix,1);
            this.setVisible(false);
            gameFrame.setVisible(true);
        });

        level2Btn.addActionListener(l->{

            MapMatrix mapMatrix = new MapMatrix(new int[][]{
                    {1, 1, 1, 1, 1, 1, 0},
                    {1, 20, 0, 0, 0, 1, 1},
                    {1, 0, 10, 10, 0, 0, 1},
                    {1, 0, 1, 2, 0, 2, 1},
                    {1, 0, 0, 0, 0, 0, 1},
                    {1, 1, 1, 1, 1, 1, 1},
            });
            GameFrame gameFrame = new GameFrame(900, 500, mapMatrix,2);
            this.setVisible(false);
            gameFrame.setVisible(true);
        });

        level3Btn.addActionListener(l->{

            MapMatrix mapMatrix = new MapMatrix(new int[][]{
                    {0, 0, 1, 1, 1, 1, 0},
                    {1, 1, 1, 0, 0, 1, 0},
                    {1, 20, 0, 2, 10, 1, 1},
                    {1, 0, 0, 0, 10, 0, 1},
                    {1, 0, 1, 2, 0, 0, 1},
                    {1, 0, 0, 0, 0, 0, 1},
                    {1, 1, 1, 1, 1, 1, 1}
            });
            GameFrame gameFrame = new GameFrame(900, 500, mapMatrix,3);
            this.setVisible(false);
            gameFrame.setVisible(true);
        });

        level4Btn.addActionListener(l->{

            MapMatrix mapMatrix = new MapMatrix(new int[][]{
                    {0, 1, 1, 1, 1, 1, 0},
                    {1, 1, 20, 0, 0, 1, 1},
                    {1, 0, 0, 1, 0, 0, 1},
                    {1, 0, 10, 12, 10, 0, 1},
                    {1, 0, 0, 2, 0, 0, 1},
                    {1, 1, 0, 2, 0, 1, 1},
                    {0, 1, 1, 1, 1, 1, 0}
            });
            GameFrame gameFrame = new GameFrame(900, 500, mapMatrix,4);
            this.setVisible(false);
            gameFrame.setVisible(true);
        });

        level5Btn.addActionListener(l->{

            MapMatrix mapMatrix = new MapMatrix(new int[][]{
                    {1, 1, 1, 1, 1, 1, 0, 0},
                    {1, 0, 0, 0, 0, 1, 1, 1},
                    {1, 0, 0, 0, 2, 2, 0, 1},
                    {1, 0, 10, 10, 10, 20, 0, 1},
                    {1, 0, 0, 1, 0, 2, 0, 1},
                    {1, 1, 1, 1, 1, 1, 1, 1},
            });
            GameFrame gameFrame = new GameFrame(1000, 500, mapMatrix,5);
            this.setVisible(false);
            gameFrame.setVisible(true);
        });

        ReLoginBtn.addActionListener(e -> {
            FrameController.returnLoginFrame(this);
            requestFocusInWindow();
        });//增加重新登录界面按钮

        FrameController.setLevelFrame(this);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
    //public static FrameController getFrameController() {
     //   return frameController;
    //}
}
