package controller;

import SetUp.SetUpFrame;
import view.ending.LoseFrame;
import view.ending.WinFrame;
import view.game.GameFrame;
import view.level.LevelFrame;
import view.login.LoginFrame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

//这部分完全按照视频教程内容增加，从而控制游戏界面返回选关界面的Return按钮和重新登录的ReLogin按钮
public class FrameController {
    private static LevelFrame levelFrame;
    private static LoginFrame loginFrame;
    private static SetUpFrame setUpFrame;
    private static WinFrame winFrame;
    private static GameFrame gameFrame;
    private static MusicController musicController;
    private static MusicController musicController_level1;
    private static MusicController musicController_level2;
    private static MusicController musicController_level3;
    private static MusicController musicController_level4;
    private static MusicController musicController_level5;
    private static MusicController musicController_level6;
    private static MusicController musicController_level7;


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

    public static MusicController getMusicController() {
        return musicController;
    }

    public static void setMusicController(MusicController musicController) {
        FrameController.musicController = musicController;
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
        musicController.startMusic();
        switch (getGameFrame().getLevelNumber()){
            case 1:
                musicController_level1.stopMusic();
                break;
            case 2:
                musicController_level2.stopMusic();
                break;
            case 3:
                musicController_level3.stopMusic();
                break;
            case 4:
                musicController_level4.stopMusic();
                break;
            case 5:
                musicController_level5.stopMusic();
                break;
            case 6:
                musicController_level6.stopMusic();
                break;
            case 7:
                musicController_level7.stopMusic();
                break;
        }
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

    public static MusicController getMusicController_level1() {
        return musicController_level1;
    }

    public static void setMusicController_level1(MusicController musicController_level1) {
        FrameController.musicController_level1 = musicController_level1;
    }

    public static MusicController getMusicController_level2() {
        return musicController_level2;
    }

    public static void setMusicController_level2(MusicController musicController_level2) {
        FrameController.musicController_level2 = musicController_level2;
    }

    public static MusicController getMusicController_level3() {
        return musicController_level3;
    }

    public static void setMusicController_level3(MusicController musicController_level3) {
        FrameController.musicController_level3 = musicController_level3;
    }

    public static MusicController getMusicController_level4() {
        return musicController_level4;
    }

    public static void setMusicController_level4(MusicController musicController_level4) {
        FrameController.musicController_level4 = musicController_level4;
    }

    public static MusicController getMusicController_level5() {
        return musicController_level5;
    }

    public static void setMusicController_level5(MusicController musicController_level5) {
        FrameController.musicController_level5 = musicController_level5;
    }

    public static MusicController getMusicController_level6() {
        return musicController_level6;
    }

    public static void setMusicController_level6(MusicController musicController_level6) {
        FrameController.musicController_level6 = musicController_level6;
    }

    public static MusicController getMusicController_level7() {
        return musicController_level7;
    }

    public static void setMusicController_level7(MusicController musicController_level7) {
        FrameController.musicController_level7 = musicController_level7;
    }
}
