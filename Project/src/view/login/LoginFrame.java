package view.login;

import Data.Vector2D;
import controller.ButtonController;
import controller.FrameController;
import controller.MusicController;
import view.FrameUtil;
import view.level.LevelFrame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;


public class LoginFrame extends JFrame {
    private JTextField username;
    private JTextField password;
    private JLabel note;
    private JButton submitBtn;
    private JButton resetBtn;
    private JButton signupBtn;
    private JButton guestBtn;
    private LevelFrame levelFrame;


    public LoginFrame(int width, int height) {
        this.setTitle("Login Frame");
        this.setLayout(null);
        this.setSize(width, height);
        FrameController.setLoginFrame(this);
        JLabel userLabel = FrameUtil.createJLabel(this, new Point(200, 20+30), 120, 60, "username:");
        JLabel passLabel = FrameUtil.createJLabel(this, new Point(200, 100+30), 120, 60, "password:");
        note = FrameUtil.createJLabel(this,new Point(250,160+30),350,40,"");
        Font font = new Font("Times New Roman", Font.PLAIN, 28);
        Font font1 = new Font("Times New Roman", Font.PLAIN, 20);
        note.setFont(font1);
        note.setForeground(Color.RED);
        userLabel.setFont(font);
        passLabel.setFont(font);
        username = FrameUtil.createJTextField(this, new Point(320, 20+30), 280, 60);
        password = FrameUtil.createJTextField(this, new Point(320, 100+30), 280, 60);
        username.setFont(font);
        password.setFont(font);
        Toolkit tk = Toolkit.getDefaultToolkit();
        java.awt.Image img = tk.getImage("PictureResource/LOGO.png");
        setIconImage(img);//设置图标

        submitBtn = ButtonController.createButton(this, "Confirm", new Point(40+50, 210+30), 150, 60,"");
        resetBtn = ButtonController.createButton(this, "Reset", new Point(220+50, 210+30), 150, 60,"");
        signupBtn = ButtonController.createButton(this,"Sign Up",new Point(400+50,210+30),150,60,"");
        guestBtn = ButtonController.createButton(this, "Guest", new Point(580+50, 210+30), 150, 60,"");//添加游客按钮
        submitBtn.setFont(font);
        resetBtn.setFont(font);
        signupBtn.setFont(font);
        guestBtn.setFont(font);
        submitBtn.addActionListener(e -> {
            MusicController.playClickSound();
            System.out.println("Username = " + username.getText());
            System.out.println("Password = " + password.getText());

            Vector2D vector2D = new Vector2D(username.getText(),password.getText());

            try {
                LoginCheck loginCheck = new LoginCheck(vector2D,this);
                this.clearText();//登录检查后清理残余文本
            } catch (IOException | ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        });

        resetBtn.addActionListener(e -> {
            MusicController.playClickSound();
            username.setText("");
            password.setText("");
        });

        signupBtn.addActionListener(e -> {
            MusicController.playClickSound();
            //new一个signUpFrame并处理异常
            SignUpFrame signUpFrame = null;
            try {
                signUpFrame = new SignUpFrame(700,400);
            } catch (IOException | ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
            signUpFrame.setVisible(true);

            });

        guestBtn.addActionListener(e -> {
            MusicController.playClickSound();
            Vector2D vector2D = new Vector2D("youke","123456");

            try {
                LoginCheck loginCheck = new LoginCheck(vector2D,this);
            } catch (IOException | ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        });//增加游客按钮，以youke账户登录
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

        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    //public static FrameController getFrameController() {
    //    return frameController;
    //}

    public void setLevelFrame(LevelFrame levelFrame) {
        this.levelFrame = levelFrame;
    }

    public void setNote(String massage) {
        this.note.setText(massage);
    }

    public LevelFrame getLevelFrame() {
        return levelFrame;
    }

    public void clearText(){
        username.setText("");
        password.setText("");
    }//清理登陆界面的残留信息
}

