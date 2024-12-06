package Data.GameArchive;
import model.MapMatrix;

import java.io.*;
import java.util.ArrayList;

public class DeserializeGame {
    //private String pathway;
    //private  int levelNumber;
   // private  ArrayList<MapMatrix> mapMatrixList;



    public static ArrayList<MapMatrix> deserializeGame(String pathway,int levelNumber,String userName) throws IOException, ClassNotFoundException {
        ArrayList<MapMatrix> mapMatrixList = new ArrayList<>();
        File file = new File(pathway);

        // 确保文件存在并且可以读取
        if (!file.exists() || !file.canRead()) {
            throw new IOException("File does not exist or cannot be read: " + pathway);
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            // 读取反序列化的对象并添加到 mapMatrixList
            mapMatrixList = (ArrayList<MapMatrix>) ois.readObject();  // 强制转换成正确的类型
            System.out.println("反序列化成功，返回的 mapMatrixList 长度: " + mapMatrixList.size());
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println("反序列化错误: " + ex.getMessage());
            throw ex;  // 抛出异常以便外部处理
        }

        return mapMatrixList;
    }


    }






