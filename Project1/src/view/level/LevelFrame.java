package view.level;

import model.MapMatrix;
import view.FrameUtil;
import view.game.GameFrame;

import javax.swing.*;
import java.awt.*;

public class LevelFrame extends JFrame {

    public LevelFrame(int width, int height) {
        this.setTitle("Level");//窗口标题
        this.setLayout(null);//使用绝对布局，可手动设置布局
        this.setSize(width, height);//设置窗口大小
        //Point的位置是相对于弹出窗口的而非电脑屏幕
        JButton level1Btn = FrameUtil.createButton(this, "Level1", new Point(30, height / 2 - 50), 80, 60);
        JButton level2Btn = FrameUtil.createButton(this, "Level2", new Point(140, height / 2 - 50), 80, 60);
        JButton level3Btn = FrameUtil.createButton(this, "Level3", new Point(250, height / 2 - 50), 80, 60);

        level1Btn.addActionListener(l->{//在做初始化的时候这些按钮就已经被设置好，但在LevelFrame可见之前还点击不了，但对鼠标事件的捕捉已经开始。
            MapMatrix mapMatrix = new MapMatrix(new int[][]{
                    {1, 1, 1, 1, 1, 1},
                    {1, 20, 0, 0, 0, 1},
                    {1, 0, 0, 10, 2, 1},
                    {1, 0, 2, 10, 0, 1},
                    {1, 1, 1, 1, 1, 1},
            });
            //创建GameFrame对象，把地图传给它
            GameFrame gameFrame = new GameFrame(600, 450, mapMatrix);
            this.setVisible(false);//隐藏当前LevelFrame窗口
            gameFrame.setVisible(true);//显示gameFrame窗口
        });

        level2Btn.addActionListener(l->{
            MapMatrix mapMatrix = new MapMatrix(new int[][]{
                    {1, 1, 1, 1, 1, 1,0},
                    {1, 20, 0, 0, 0, 1,1},
                    {1, 0, 10, 10, 0, 0,1},
                    {1, 0, 1, 2, 0, 2,1},
                    {1, 0, 0, 0, 0, 0,1},
                    {1, 1, 1, 1, 1, 1,1},
            });
            GameFrame gameFrame = new GameFrame(600, 450, mapMatrix);
            this.setVisible(false);
            gameFrame.setVisible(true);
        });

        level3Btn.addActionListener(l->{
            MapMatrix mapMatrix = new MapMatrix(new int[][]{
                    {0, 0, 1, 1, 1, 1,0},
                    {1, 1, 1, 0, 0, 1,0},
                    {1, 20, 0, 2, 10, 1,1},
                    {1, 0, 0, 0, 10,0 ,1},
                    {1, 0, 1, 2, 0, 0,1},
                    {1,0,0,0,0,0,1},
                    {1, 1, 1, 1, 1, 1,1},
            });
            GameFrame gameFrame = new GameFrame(600, 450, mapMatrix);
            this.setVisible(false);
            gameFrame.setVisible(true);
        });

        //任务：加入更多关卡
        //todo: complete all level.

        this.setLocationRelativeTo(null);//窗口显示在中间
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//关闭窗口时退出程序
    }

}
