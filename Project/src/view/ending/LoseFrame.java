package view.ending;

import controller.FrameController;
import controller.MusicController;
import view.FrameUtil;
import view.game.GamePanel;

import javax.swing.*;
import java.awt.*;

public class LoseFrame extends javax.swing.JFrame {
    private JButton moveBackBtn;
    private GamePanel gamePanel;
    public LoseFrame(int width, int height) {
        // 加载 GIF 图像
        ImageIcon LoseFrameGIF = new ImageIcon("PictureResource/LoseFrameGIF.gif");
        // 创建一个 JLabel 并将 GIF 图像设置为其图标
        JLabel label = new JLabel(LoseFrameGIF);
        // 设置标签的位置和大小
        label.setBounds(0, 0, width, height);

        this.setTitle("Lose Frame");
        this.setLayout(null);
        this.setSize(width, height);
        Toolkit tk = Toolkit.getDefaultToolkit();
        java.awt.Image img = tk.getImage("PictureResource/LOGO.png");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        // 添加 GIF 图像标签到框架中
        add(label);
        JLabel label1 = new JLabel("You Lose!");
        label1.setBounds(0, 0, width, height);
        label1.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        this.moveBackBtn = FrameUtil.createButton(this,"Move Back",new Point(0,280),120,50);
        label1.add(moveBackBtn);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.moveBackBtn.addActionListener(e -> {
            FrameController.returnGameFrame(this);
            try {
                MusicController.stopMusic();
            } catch (Exception f) {
                throw new RuntimeException(f);
            }
//            gamePanel.requestFocusInWindow();
        });
        add(label1);

        this.setVisible(true);

    }
}
