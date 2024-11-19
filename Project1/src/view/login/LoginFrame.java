package view.login;

import view.FrameUtil;
import view.level.LevelFrame;

import javax.swing.*;
import java.awt.*;


public class LoginFrame extends JFrame {
    private JTextField username;
    private JTextField password;
    private JButton submitBtn;
    private JButton resetBtn;
    private LevelFrame levelFrame;


    public LoginFrame(int width, int height) {
        this.setTitle("Login Frame");//设置窗口标题
        this.setLayout(null);        //设置窗口布局为null，即手动设置组件位置和大小
        this.setSize(width, height); //设置窗口大小
        int i=0;

        JLabel userLabel = FrameUtil.createJLabel(this, new Point(50, 20), 70, 40, "username:");
        JLabel passLabel = FrameUtil.createJLabel(this, new Point(50, 80), 70, 40, "password:");
        //创建文本框用来输入用户名和密码
        username = FrameUtil.createJTextField(this, new Point(120, 20), 120, 40);
        password = FrameUtil.createJTextField(this, new Point(120, 80), 120, 40);
        //创建确认按钮和重置按钮
        submitBtn = FrameUtil.createButton(this, "Confirm", new Point(40, 140), 100, 40);
        resetBtn = FrameUtil.createButton(this, "Reset", new Point(160, 140), 100, 40);
        //按钮事件处理
        submitBtn.addActionListener(e -> {//当用户按了Confirm按钮时触发该事件
            System.out.println("Username = " + username.getText());//输出用户输入的密码和姓名
            System.out.println("Password = " + password.getText());
            if (this.levelFrame != null) {//如果levelFrame不为空（即已经被初始化）
                this.levelFrame.setVisible(true);//将levelFrame设置为可见，将当前登录界面隐藏
                this.setVisible(false);
            }
            //任务：加入逻辑验证用户名和密码是否正确
            //todo: check login info

        });
        resetBtn.addActionListener(e -> {//用户点击Reset按钮时清空用户名和密码框中内容
            username.setText("");
            password.setText("");
        });

        this.setLocationRelativeTo(null);//设置窗口显示在屏幕中央
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //设置关闭窗口时退出应用程序
    }
    //登陆成功后LoginFrame会切换到LevelFrame

    public void setLevelFrame(LevelFrame levelFrame) {
        this.levelFrame = levelFrame;
    }
}
