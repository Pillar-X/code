package controller;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;


public class MusicController  {
    private Clip music = null; //声明Clip接口
    private File sourceFile = null; //声明文件变量
    /**
     * 音乐播放方法
     */
    public MusicController (String path){
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



    public void stopMusic(){
        if(music!=null){
            music.stop();


        }
    }

    public void startMusic(){
        music.setFramePosition(0);
        music.start();
    }
    public static void playMoveSound(){
        MusicController musicController = new MusicController("MusicResource/move.wav");
    }

    public static MusicController playWinSound(){
        MusicController musicController = new MusicController("MusicResource/BGM1.wav");
        return musicController;

    }
    public static void playLoseSound(){
        MusicController musicController = new MusicController("MusicResource/failed.wav");

    }
    public static void playClickSound(){
        MusicController musicController = new MusicController("MusicResource/click.wav");

    }

//    public static void playBGM3(){
//        playMusic("MusicResource/BGM3.wav");
//    }
//    public static void playBGM4(){
//        playMusic("MusicResource/BGM4.wav");
//    }
    public static void changeLevelMusic(){
        MusicController musicController = new MusicController("MusicResource/changeLevel.wav");

    }
    //public static void stopMusic_Force(){
    //    music = null;
    //}
    public void setMusicVolume(double volume){
        FloatControl volumeControl = (FloatControl) music.getControl(FloatControl.Type.MASTER_GAIN);
        volumeControl.setValue((float) volume); // 设置音量
    }

    public void checkMusic(){
        System.out.println(music);
    }
}
