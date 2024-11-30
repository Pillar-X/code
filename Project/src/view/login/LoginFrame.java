package view.login;

import Data.Vector2D;
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


        submitBtn = FrameUtil.createButton(this, "Confirm", new Point(40, 140), 100, 40);
        resetBtn = FrameUtil.createButton(this, "Reset", new Point(160, 140), 100, 40);
        signupBtn = FrameUtil.createButton(this,"Sign Up",new Point(280,140),100,40);

        submitBtn.addActionListener(e -> {
            System.out.println("Username = " + username.getText());
            System.out.println("Password = " + password.getText());

            Vector2D vector2D = new Vector2D(username.getText(),password.getText());

            try {
                LoginCheck loginCheck = new LoginCheck(vector2D,this);
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
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
            signUpFrame.setVisible(true);

            });

        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void setLevelFrame(LevelFrame levelFrame) {
        this.levelFrame = levelFrame;
    }

    public void setNote(String massage) {
        this.note.setText(massage);
    }

    public LevelFrame getLevelFrame() {
        return levelFrame;
    }
}
