package controller;

import SetUp.SetUpFrame;
import view.game.GameFrame;
import view.level.LevelFrame;
import view.login.LoginFrame;

import java.awt.*;
//这部分完全按照视频教程内容增加，从而控制游戏界面返回选关界面的Return按钮和重新登录的ReLogin按钮
public class FrameController {
    private static LevelFrame levelFrame;
    private static LoginFrame loginFrame;
    private static SetUpFrame setUpFrame;

    //LevelFrame的 getter,setter 和 从 gameFrame -> levelFrame
    public static LevelFrame getLevelFrame() {
        return levelFrame;
    }
    public static void setLevelFrame(LevelFrame levelFrame) {
        FrameController.levelFrame = levelFrame;
    }
    public static void returnLevelFrame(GameFrame gameFrame) {
        gameFrame.dispose();
        levelFrame.setVisible(true);
    }


    //LoginFrame的getter,setter 和 从 levelFrame -> LoginFrame 的方法
    public static LoginFrame getLoginFrame() {
        return loginFrame;
    }
    public static void setLoginFrame(LoginFrame loginFrame) {
        FrameController.loginFrame = loginFrame;
    }
    public static void returnLoginFrame(LevelFrame levelFrame) {
        levelFrame.dispose();
        loginFrame.setVisible(true);
    }

    public static SetUpFrame getSetUpFrame() {return setUpFrame;}
    public static void setSetUpFrame(SetUpFrame setUpFrame) {FrameController.setUpFrame = setUpFrame;}

}
