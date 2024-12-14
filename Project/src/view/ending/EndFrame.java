package view.ending;

import controller.MusicController;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class EndFrame extends JFrame {
    public EndFrame(int width, int height) {
        MusicController musicController = new MusicController("MusicResource/FinalPage~1.wav");

        this.setTitle("End Frame");

        this.setLayout(null);
        this.setSize(width, height);
        Toolkit tk = Toolkit.getDefaultToolkit();
        Image img = tk.getImage("PictureResource/LOGO.png");
        setIconImage(img);//设置图标
        // 获取屏幕大小
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        JWindow window = new JWindow();
        window.setBackground(new Color(0, 0, 0, 0)); // 设置背景透明
        window.setSize(screenSize);
        window.setLayout(new BorderLayout()); // 使用 BorderLayout 布局
        // 添加烟花面板
        FireworkPanel panel = new FireworkPanel(screenSize.height); // 传递屏幕高度
        window.add(panel, BorderLayout.CENTER); // 确保 FireworkPanel 占满整个窗口
        window.setVisible(true);
        // 强制重绘刷新
        RepaintManager.currentManager(panel).markCompletelyDirty(panel);
        // 启动后立即生成发射粒子
        panel.addLaunchParticle();
        this.add(panel);
        this.setVisible(true);
        // 设置背景
        JLabel lblBackground = new JLabel(); // 创建一个标签组件对象
        BufferedImage bufferedImage = null;
        String path = "PictureResource/End.png";
        try {
            bufferedImage = ImageIO.read(new File(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ImageIcon icon = new ImageIcon(bufferedImage); // 创建背景图片对象
        lblBackground.setIcon(icon); // 设置标签组件要显示的图标
        lblBackground.setBounds(0, 0, icon.getIconWidth(), icon.getIconHeight()); // 设置组件的显示位置及大小
        this.getContentPane().add(lblBackground); // 将组件添加到面板中
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

    }
    public static void main(String[] args) {
        new EndFrame(1400, 1000);

    }
}
