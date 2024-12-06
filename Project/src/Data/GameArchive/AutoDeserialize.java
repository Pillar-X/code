package Data.GameArchive;
import model.MapMatrix;

import java.io.*;
import java.util.ArrayList;

public class AutoDeserialize {


    public static MapMatrix autodeserialize(String pathway) throws IOException, ClassNotFoundException {

        File file = new File(pathway);
        MapMatrix mapMatrix ;

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            // 读取反序列化的对象并添加到 mapMatrixList
            mapMatrix = (MapMatrix) ois.readObject();  // 强制转换成正确的类型
            System.out.println("Auto反序列化成功");
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println("反序列化错误: " + ex.getMessage());
            throw ex;  // 抛出异常以便外部处理
        }

        return mapMatrix;
    }


}