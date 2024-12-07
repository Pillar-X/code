package Data.GameArchive;

import model.MapMatrix;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class DeserializeRecord {
    public static MapMatrix deserializeRecord(String pathway) throws IOException, ClassNotFoundException {
        File file = new File(pathway);
        MapMatrix mapMatrix;

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            // 读取反序列化的对象并添加到 mapMatrixList
            mapMatrix = (MapMatrix) ois.readObject();  // 强制转换成正确的类型
            System.out.println("Record反序列化成功");
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println("Record反序列化错误: " + ex.getMessage());
            throw ex;  // 抛出异常以便外部处理
        }

        return mapMatrix;
    }
}