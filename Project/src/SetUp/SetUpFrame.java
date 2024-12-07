package SetUp;

import view.FrameUtil;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class SetUpFrame extends JFrame {

    private static String username;
    private String pathway;
    private static  String MainPath;
    private static String savePath;
    private static String autoSavePath;
    private JLabel nameLabel;


    public SetUpFrame (int width , int height , String username){
        SetUpFrame.username = username;
        CreateMainFile();
        CreateSaveFile();
        CreateAutoSaveFile();
        this.setTitle("Set Up");
        this.setLayout(null);
        this.setSize(width,height);
        this.setLocationRelativeTo(null);
        nameLabel = FrameUtil.createJLabel(this,new Point(this.getWidth()/2-20, 20),40,40,username);
        this.setVisible(false);

    }

    public void CreateMainFile (){
        String userHome = System.getProperty("user.home");//获取用户名称，以便输入到指定路径
        String tmpPath = userHome + "/AppData/Local/Packages/Sokoban/";//一般软件的存档都存放在这里
        MainPath = tmpPath + username +"/";

        File file = new File(MainPath);
        if(file.mkdirs()){
            System.out.println("主文件夹创建成功！");
        }else {
            System.out.println("主文件夹创建失败，可能是文件夹已存在或者路径有误。");
        }
    }

    public void CreateSaveFile(){
        savePath = MainPath + "/save/";
        File file = new File(savePath);
        if(file.mkdirs()){
            System.out.println("Save文件夹创建成功！");
        }else {
            System.out.println("Save文件夹创建失败，可能是文件夹已存在或者路径有误。");
        }
    }

    public void CreateAutoSaveFile(){
        autoSavePath = MainPath + "/autoSave/";
        File file = new File(autoSavePath);
        if(file.mkdirs()){
            System.out.println("AutoSave文件夹创建成功");
        }else{
            System.out.println("AutoSave文件夹创建失败，可能是文件夹已存在或者路径有误。");
        }
    }

    public static String getUsername() {
        return username;
    }

    public static String getSavePath() {
        return savePath;
    }

    public static String getAutoSavePath() {
        return autoSavePath;
    }

    public static void setSavePath(String savePath) {
        SetUpFrame.savePath = savePath;
    }
}
