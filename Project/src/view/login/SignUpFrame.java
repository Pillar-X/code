package view.login;

import Data.SignUp.DeserializeUserList;
import Data.SignUp.SerializeUserList;
import Data.SignUp.UserCheck;
import view.FrameUtil;
import Data.Vector2D;
import Data.*;

import javax.swing.*;
import java.awt.*;
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

    public SignUpFrame(int width,int height) throws IOException,ClassNotFoundException{
        this.setTitle("Sign up Frame");
        this.setLayout(null);
        this.setSize(width, height);
        JLabel userLabel = FrameUtil.createJLabel(this, new Point(80, 20), 70, 40, "username:");
        JLabel passLabel = FrameUtil.createJLabel(this, new Point(80, 100), 70, 40, "password:");
        JLabel repeatLabel = FrameUtil.createJLabel(this,new Point(40,180),130,40,"Repeat the password:");

        this.userNoteLabel = FrameUtil.createJLabel(this, new Point(180, 50), 200, 40, "");
        this.passNoteLabel = FrameUtil.createJLabel(this, new Point(180, 130), 200, 40, "");
        this.repeatNoteLabel = FrameUtil.createJLabel(this, new Point(180, 210), 200, 40, "");

        username = FrameUtil.createJTextField(this, new Point(180, 20), 200, 40);
        password = FrameUtil.createJTextField(this, new Point(180, 100), 200, 40);
        repeatPassword = FrameUtil.createJTextField(this,new Point(180,180),200,40);

        submitBtn = FrameUtil.createButton(this, "Confirm", new Point(150, 270), 100, 40);
        resetBtn = FrameUtil.createButton(this, "Reset", new Point(270, 270), 100, 40);

        submitBtn.addActionListener(e -> {

            Vector2D vector2D = new Vector2D(username.getText(),password.getText());
            try {
                UserCheck userCheck = new UserCheck(vector2D,repeatPassword.getText(),this);

            } catch (IOException | ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }


        });

        resetBtn.addActionListener(e -> {
            username.setText("");
            password.setText("");
            repeatPassword.setText("");
            userNoteLabel.setText("");
            repeatNoteLabel.setText("");
            passNoteLabel.setText("");

        });

        this.setLocationRelativeTo(null);



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
