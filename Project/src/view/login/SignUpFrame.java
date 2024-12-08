package view.login;

import Data.SignUp.DeserializeUserList;
import Data.SignUp.SerializeUserList;
import Data.SignUp.UserCheck;
import controller.ButtonController;
import controller.MusicController;
import view.FrameUtil;
import Data.Vector2D;
import Data.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class SignUpFrame extends JFrame {
    private JTextField username;
    private JTextField password;
    private JTextField repeatPassword;
    private JButton submitBtn;
    private JButton resetBtn;
    private JLabel userNoteLabel;
    private JLabel passNoteLabel;
    private JLabel repeatNoteLabel;
    private int x = 100;
    private int y = 100;
    public SignUpFrame(int width,int height) throws IOException,ClassNotFoundException{
        this.setTitle("Sign up Frame");
        this.setLayout(null);
        this.setSize(width, height);
        JLabel userLabel = FrameUtil.createJLabel(this, new Point(80+x, 20), 70+50, 40, "username:");
        JLabel passLabel = FrameUtil.createJLabel(this, new Point(80+x, 100), 70+50, 40, "password:");
        JLabel repeatLabel = FrameUtil.createJLabel(this,new Point(40+x-20,180),130+50,40,"Repeat the password:");

        this.userNoteLabel = FrameUtil.createJLabel(this, new Point(180+x, 50), 200, 40, "");
        this.passNoteLabel = FrameUtil.createJLabel(this, new Point(180+x, 130), 200+50, 40, "");
        this.repeatNoteLabel = FrameUtil.createJLabel(this, new Point(180+x, 210), 200, 40, "");
        this.userNoteLabel.setForeground(Color.RED);
        this.passNoteLabel.setForeground(Color.RED);
        this.repeatNoteLabel.setForeground(Color.RED);
        username = FrameUtil.createJTextField(this, new Point(180+x, 20), 200+50, 40);
        password = FrameUtil.createJTextField(this, new Point(180+x, 100), 200+50, 40);
        repeatPassword = FrameUtil.createJTextField(this,new Point(180+x,180),200+50,40);

        submitBtn = ButtonController.createButton(this, "Confirm", new Point(150+50-60+30, 270), 150, 60,"");
        resetBtn = ButtonController.createButton(this, "Reset", new Point(270+50+60, 270), 150, 60,"");
        Font font = new Font("Times New Roman", Font.PLAIN, 28);
        Font font1 = new Font("Times New Roman", Font.PLAIN, 18);
        userLabel.setFont(font1);
        passLabel.setFont(font1);
        repeatLabel.setFont(font1);
        username.setFont(font);
        password.setFont(font);
        repeatPassword.setFont(font);
        submitBtn.setFont(font);
        resetBtn.setFont(font);
        submitBtn.addActionListener(e -> {
            MusicController.playClickSound();
            Vector2D vector2D = new Vector2D(username.getText(),password.getText());
            try {
                UserCheck userCheck = new UserCheck(vector2D,repeatPassword.getText(),this);

            } catch (IOException | ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }


        });

        resetBtn.addActionListener(e -> {
            MusicController.playClickSound();
            username.setText("");
            password.setText("");
            repeatPassword.setText("");
            userNoteLabel.setText("");
            repeatNoteLabel.setText("");
            passNoteLabel.setText("");

        });
        Toolkit tk = Toolkit.getDefaultToolkit();
        java.awt.Image img = tk.getImage("PictureResource/LOGO.png");
        setIconImage(img);//设置图标
        this.setLocationRelativeTo(null);
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


    }

    public void setUserNoteLabel(String note) {
        this.userNoteLabel.setText(note);
    }

    public void setPassNoteLabel(String note) {
        this.passNoteLabel.setText(note);
    }

    public void setRepeatNoteLabel(String note) {
        this.repeatNoteLabel.setText(note);
    }
}
