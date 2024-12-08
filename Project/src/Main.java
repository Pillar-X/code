import Data.SignUp.SerializeUserList;
import controller.MusicController;
import view.ending.WinFrame;
import view.level.LevelFrame;
import view.login.LoginFrame;
import Data.*;

import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LoginFrame loginFrame = new LoginFrame(850,400);
            loginFrame.setVisible(true);
            LevelFrame levelFrame = new LevelFrame(1100,700);
            levelFrame.setVisible(false);
            loginFrame.setLevelFrame(levelFrame);
        });
    }
}
