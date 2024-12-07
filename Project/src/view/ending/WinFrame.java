package view.ending;

import Data.Vector2D;
import controller.FrameController;
import view.FrameUtil;


import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class WinFrame extends JFrame {
    private final JButton returnBtn;
    public WinFrame(int width, int height) {

        this.setTitle("Win Frame");
        this.setLayout(null);
        this.setSize(width, height);
        Toolkit tk = Toolkit.getDefaultToolkit();
        java.awt.Image img = tk.getImage("PictureResource/LOGO.png");
        // 获取屏幕大小
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        // 创建全屏的透明窗口
        JWindow window = new JWindow();
        window.setSize(screenSize);
        window.setBackground(new Color(0, 0, 0, 0)); // 设置背景透明
        window.setLayout(new BorderLayout()); // 使用 BorderLayout 布局

        // 添加烟花面板
        FireworkPanel panel = new FireworkPanel(screenSize.height); // 传递屏幕高度
        window.add(panel, BorderLayout.CENTER); // 确保 FireworkPanel 占满整个窗口
//        window.setAlwaysOnTop(true); // 窗口始终在最前面
        window.setVisible(true);

        // 强制重绘刷新
        RepaintManager.currentManager(panel).markCompletelyDirty(panel);

        // 启动后立即生成发射粒子
        panel.addLaunchParticle();
        this.returnBtn = FrameUtil.createButton(this, "Return", new Point(420, 250), 80, 50);//返回按钮
        this.returnBtn.addActionListener(e -> {
            FrameController.returnLevelFrame(this);

            panel.requestFocusInWindow();//返回按钮的监听器
        });
        add(panel);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }


}
