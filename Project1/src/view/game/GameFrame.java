package view.game;//包含游戏界面的窗口

import javax.swing.*;
import java.awt.*;

import controller.GameController;
import model.MapMatrix;
import view.FrameUtil;

public class GameFrame extends JFrame {

    private GameController controller;
    private JButton restartBtn;
    private JButton loadBtn;

    private JLabel stepLabel;
    private GamePanel gamePanel;

    public GameFrame(int width, int height, MapMatrix mapMatrix) {
        this.setTitle("2024 CS109 Project Demo");
        this.setLayout(null);
        this.setSize(width, height);
        //创建游戏面板（GamePanel）
        gamePanel = new GamePanel(mapMatrix);
        gamePanel.setLocation(30, height / 2 - gamePanel.getHeight() / 2);
        this.add(gamePanel);
        this.controller = new GameController(gamePanel, mapMatrix);
        //设置按钮，并保证其在gamePanel窗口右侧
        this.restartBtn = FrameUtil.createButton(this, "Restart", new Point(gamePanel.getWidth() + 80, 120), 80, 50);
        this.loadBtn = FrameUtil.createButton(this, "Load", new Point(gamePanel.getWidth() + 80, 210), 80, 50);
        //创建步数显示标签
        this.stepLabel = FrameUtil.createJLabel(this, "Start", new Font("serif", Font.ITALIC, 22), new Point(gamePanel.getWidth() + 80, 70), 180, 50);
        gamePanel.setStepLabel(stepLabel);
        //按钮事件监听器
        this.restartBtn.addActionListener(e -> {
            //点击restart按钮时，调用controller.restartGame()来重启游戏
            controller.restartGame();
            //重新获取焦点，确保游戏面板能够接受键盘事件
            gamePanel.requestFocusInWindow();//enable key listener
        });
        this.loadBtn.addActionListener(e -> {
            //点击load按钮时会弹出输入框，让用户输入文件路径
            String string = JOptionPane.showInputDialog(this, "Input path:");
            //输入的路径打印到控制台
            System.out.println(string);
            //重新获取键盘焦点
            gamePanel.requestFocusInWindow();//enable key listener
        });
        //任务：在这里加入更多按钮
        //todo: add other button here
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

}
