package controller;

import SetUp.SetUpFrame;
import view.ending.LoseFrame;
import view.ending.WinFrame;
import view.game.GameFrame;
import view.level.LevelFrame;
import view.login.LoginFrame;

import java.awt.*;
//这部分完全按照视频教程内容增加，从而控制游戏界面返回选关界面的Return按钮和重新登录的ReLogin按钮
public class FrameController {
    private static LevelFrame levelFrame;
    private static LoginFrame loginFrame;
    private static SetUpFrame setUpFrame;
    private static WinFrame winFrame;
    private static GameFrame gameFrame;
    private static LoseFrame loseFrame;
    //GameFrame的 getter,setter 和 从 levelFrame -> gameFrame 的方法
    public static GameFrame getGameFrame(LevelFrame levelFrame) {
        return gameFrame;
    }
    public static LoseFrame getLoseFrame() {
        return loseFrame;
    }
    public static void returnGameFrame(LoseFrame loseFrame) {
        loseFrame.setVisible(false);
        getGameFrame().setVisible(true);

    }
    public static GameFrame getGameFrame() {
        return gameFrame;
    }
    public static void setGameFrame(GameFrame gameFrame) {
        FrameController.gameFrame = gameFrame;
    }
    //LevelFrame的 getter,setter 和 从 gameFrame -> levelFrame
    public static LevelFrame getLevelFrame() {
        return levelFrame;
    }
    public static void setLevelFrame(LevelFrame levelFrame) {
        FrameController.levelFrame = levelFrame;
    }
    public static void moveToWinFrame(GameFrame gameFrame) {
        gameFrame.setVisible(false);
        winFrame.setVisible(true);
    }
    public static void returnLevelFrame(GameFrame gameFrame) {
        gameFrame.setVisible(false);
        levelFrame.setVisible(true);
    }
    public static void returnLevelFrame(WinFrame winFrame) {
        winFrame.setVisible(false);
        getGameFrame().setVisible(false);
        levelFrame.setVisible(true);
    }
    public static void setWinFrame(WinFrame winFrame) {FrameController.winFrame = winFrame;}

    //LoginFrame的getter,setter 和 从 levelFrame -> LoginFrame 的方法
    public static LoginFrame getLoginFrame() {
        return loginFrame;
    }
    public static void setLoginFrame(LoginFrame loginFrame) {
        FrameController.loginFrame = loginFrame;
    }
    public static void returnLoginFrame(LevelFrame levelFrame) {
        levelFrame.setVisible(false);
        loginFrame.setVisible(true);
    }

    public static SetUpFrame getSetUpFrame() {return setUpFrame;}
    public static void setSetUpFrame(SetUpFrame setUpFrame) {FrameController.setUpFrame = setUpFrame;}
    public static void returnLevelFrame(SetUpFrame setUpFrame) {
        setUpFrame.setVisible(false);
        levelFrame.setVisible(true);
    }
}
