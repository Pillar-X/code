package controller;

import view.game.GameFrame;
import view.level.LevelFrame;
import view.login.LoginFrame;

import java.awt.*;
//这部分完全按照视频教程内容增加，从而控制游戏界面返回选关界面的Return按钮和重新登录的ReLogin按钮
public class FrameController {
    private LevelFrame levelFrame;
    private LoginFrame loginFrame;
    public LevelFrame getLevelFrame() {
        return levelFrame;
    }
    public void setLevelFrame(LevelFrame levelFrame) {
        this.levelFrame = levelFrame;
    }

    public void returnLevelFrame(GameFrame gameFrame) {
        gameFrame.dispose();
        levelFrame.setVisible(true);
    }

    public LoginFrame getLoginFrame() {
        return loginFrame;
    }
    public void setLoginFrame(LoginFrame loginFrame) {
        this.loginFrame = loginFrame;
    }
    public void returnLoginFrame(LevelFrame levelFrame) {
        levelFrame.dispose();
        loginFrame.setVisible(true);
    }
}
