import view.level.LevelFrame;
import view.login.LoginFrame;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LoginFrame loginFrame = new LoginFrame(280,280);//设置好登录窗口
            loginFrame.setVisible(true);//让其可见
            LevelFrame levelFrame = new LevelFrame(500,200);//设置好关卡选择窗口
            levelFrame.setVisible(false);//让其不可见
            loginFrame.setLevelFrame(levelFrame);//把设置好的levelFrame传递给loginFrame
            //以上只是设置好了LoginFrame和levelFrame的窗口和弹出条件
            //此后按了confirm按钮就会触发弹出
        });
    }
}
