package view.ending;

import controller.ButtonController;
import controller.FrameController;
import controller.MusicController;
import view.FrameUtil;
import view.game.GamePanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

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

        this.moveBackBtn = ButtonController.createButton(this,"Move Back",new Point(0,280),120,50,"PictureResource/button.png");
        label1.add(moveBackBtn);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.moveBackBtn.addActionListener(e -> {

            FrameController.returnGameFrame(this);
            try {
                MusicController.stopMusic();
            } catch (Exception f) {
                throw new RuntimeException(f);
            }
            MusicController.playClickSound();
//            gamePanel.requestFocusInWindow();
        });
        add(label1);
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
        this.setVisible(true);

    }
}
