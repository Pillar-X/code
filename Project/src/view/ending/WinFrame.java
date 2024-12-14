package view.ending;
import Data.Vector2D;
import controller.ButtonController;
import controller.FrameController;
import controller.MusicController;
import view.FrameUtil;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class WinFrame extends JFrame {
    private MusicController musicController;
    private JButton returnBtn;
    private JButton nextBtn;
    private JLabel BestRecordLabel;
    private JLabel ThisRecordLabel;
    public WinFrame(int width, int height)  {

        // 加载 GIF 图像
        ImageIcon WinFrameGIF = new ImageIcon("PictureResource/WinFrameGIF.gif");
        this.setTitle("Win Frame");
        JLabel label = new JLabel(WinFrameGIF);
        label.setBounds(10, 0, width, height);
        this.setLayout(null);
        this.setSize(width, height);
        Toolkit tk = Toolkit.getDefaultToolkit();
        Image img = tk.getImage("PictureResource/LOGO.png");
        setIconImage(img);//设置图标
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
        returnBtn = ButtonController.createButton(this, "Return", new Point(0,280),120,50,"");//返回按钮
        nextBtn = ButtonController.createButton(this,"Next Level",new Point(140,280),120,50,"");
//        returnBtn.setIcon(new ImageIcon("PictureResource/button.png"));
//        Image temp = ImageIO.read(new File("PictureResource/button.png")).getScaledInstance(width, height, Image.SCALE_SMOOTH);
        JLabel label1 = new JLabel("You Win!");
        label1.setBounds(0, 0, width, height);
        label1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        label1.add(returnBtn);
        nextBtn.addActionListener(e -> {
            int levelNow = FrameController.getGameFrame().getLevelNumber();
            int levelNext = levelNow+1;
            try {

                musicController.stopMusic();
            } catch (Exception f) {
                throw new RuntimeException(f);
            }
            MusicController.changeLevelMusic();
            if(levelNext>=8) System.out.println("不存在下一关 ");
            else{
                FrameController.getLevelFrame().LevelChooser(levelNext);
                this.setVisible(false);
            }

        });

        returnBtn.addActionListener(e -> {

            FrameController.returnLevelFrame(this);
            try {
                musicController.stopMusic();
            } catch (Exception f) {
                throw new RuntimeException(f);
            }
            MusicController.playClickSound();
            panel.requestFocusInWindow();//返回按钮的监听器
            FrameController.getMusicController().startMusic();
        });
        add(panel);
        add(label);
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
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);


    }

    public JLabel getThisRecordLabel() {
        return ThisRecordLabel;
    }

    public void setThisRecordLabel(JLabel thisRecordLabel) {
        ThisRecordLabel = thisRecordLabel;
    }

    public JLabel getBestRecordLabel() {
        return BestRecordLabel;
    }

    public void playWinSound(){
        this.musicController =MusicController.playWinSound();
    }

    public void setBestRecordLabel(JLabel bestRecordLabel) {
        BestRecordLabel = bestRecordLabel;
    }
}
