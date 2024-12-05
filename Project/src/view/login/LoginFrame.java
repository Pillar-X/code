package view.login;

import Data.Vector2D;
import controller.FrameController;
import view.FrameUtil;
import view.level.LevelFrame;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
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
        JLabel userLabel = FrameUtil.createJLabel(this, new Point(50, 20), 70, 40, "username:");
        JLabel passLabel = FrameUtil.createJLabel(this, new Point(50, 80), 70, 40, "password:");
        note = FrameUtil.createJLabel(this,new Point(118,110),300,40,"");

        username = FrameUtil.createJTextField(this, new Point(120, 20), 120, 40);
        password = FrameUtil.createJTextField(this, new Point(120, 80), 120, 40);
        Toolkit tk = Toolkit.getDefaultToolkit();
        java.awt.Image img = tk.getImage("PictureResource/LOGO.png");
        setIconImage(img);//设置图标

        submitBtn = FrameUtil.createButton(this, "Confirm", new Point(40, 140), 100, 40);
        resetBtn = FrameUtil.createButton(this, "Reset", new Point(160, 140), 100, 40);
        signupBtn = FrameUtil.createButton(this,"Sign Up",new Point(280,140),100,40);
        guestBtn = FrameUtil.createButton(this, "Guest", new Point(400, 140), 100, 40);//添加游客按钮

        submitBtn.addActionListener(e -> {
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
            username.setText("");
            password.setText("");
        });

        signupBtn.addActionListener(e -> {
            //new一个signUpFrame并处理异常
            SignUpFrame signUpFrame = null;
            try {
                signUpFrame = new SignUpFrame(500,350);
            } catch (IOException | ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
            signUpFrame.setVisible(true);

            });

        guestBtn.addActionListener(e -> {
            Vector2D vector2D = new Vector2D("youke","123456");

            try {
                LoginCheck loginCheck = new LoginCheck(vector2D,this);
            } catch (IOException | ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        });//增加游客按钮，以youke账户登录

        FrameController.setLoginFrame(this);

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

