import Data.SignUp.SerializeUserList;
import view.level.LevelFrame;
import view.login.LoginFrame;
import Data.*;

import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LoginFrame loginFrame = new LoginFrame(550,280);
            loginFrame.setVisible(true);
            LevelFrame levelFrame = new LevelFrame(500,400);
            levelFrame.setVisible(false);
            loginFrame.setLevelFrame(levelFrame);
        });
    }
}
