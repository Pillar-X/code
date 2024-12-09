package controller;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;


public class MusicController  {
    public static Clip music = null; //声明Clip接口
    static File sourceFile = null; //声明文件变量
    /**
     * 音乐播放方法
     */
    public static void playMusic(String path){
        try {
            music = AudioSystem.getClip(); // 获取可用于播放音频文件或音频流的数据流
            sourceFile = new File(path);//获取文件
            AudioInputStream ais = AudioSystem.getAudioInputStream(sourceFile);//获得指示格式的音频输入流
            music.open(ais); //打开数据流
            music.start();    //开始播放音乐

        } catch (LineUnavailableException e) {
            e.printStackTrace();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static void stopMusic(){
        music.stop();
    }
    public static void playMoveSound(){
        playMusic("MusicResource/move.wav");
    }
    public static void playWinSound(){
        playMusic("MusicResource/BGM1.wav");
    }
    public static void playLoseSound(){
        playMusic("MusicResource/failed.wav");
    }
    public static void playClickSound(){
        playMusic("MusicResource/click.wav");
    }
    public static void playBGM2(){
        playMusic("MusicResource/BGM2.wav");
    }
    public static void playBGM3(){
        playMusic("MusicResource/BGM3.wav");
    }
    public static void playBGM4(){
        playMusic("MusicResource/BGM4.wav");
    }
    public static void changeLevelMusic(){
        playMusic("MusicResource/changeLevel.wav");
    }
    public static void setMusicVolume(double volume){
        FloatControl volumeControl = (FloatControl) music.getControl(FloatControl.Type.MASTER_GAIN);
        volumeControl.setValue((float) volume); // 设置音量
    }
}
