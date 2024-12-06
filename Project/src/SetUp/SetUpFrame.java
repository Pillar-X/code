package SetUp;

import javax.swing.*;
import java.io.File;

public class SetUpFrame extends JFrame {
    private static String username;
    private String pathway;
    private static  String MainPath;
    private static String savePath;


    public SetUpFrame (int width , int height , String username){
        SetUpFrame.username = username;
        CreateMainFile();
        CreateSaveFile();

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
        /*
        File file1 = new File(savePath+"/level1/");
        File file2 = new File(savePath+ "/level2/");
        File file3 = new File(savePath+"/level3/");
        File file4 = new File(savePath+ "/level4/");
        File file5 = new File(savePath+"/level5/");
        file1.mkdirs();
        file2.mkdirs();
        file3.mkdirs();
        file4.mkdirs();
        file5.mkdirs();
        */


    }

    public static String getUsername() {
        return username;
    }

    public static String getSavePath() {
        return savePath;
    }

    public static void setSavePath(String savePath) {
        SetUpFrame.savePath = savePath;
    }
}
