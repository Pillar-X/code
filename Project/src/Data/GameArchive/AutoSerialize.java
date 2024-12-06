package Data.GameArchive;
import model.MapMatrix;

import java.io.*;
import java.util.ArrayList;

public class AutoSerialize {
    private String pathway;

    public static void autoserialize(String pathway, MapMatrix mapMatrix) throws IOException {

        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(pathway)) ;
        oos.reset();
        oos.writeObject(mapMatrix);  // 将 MapMatrix 对象写入文件
        oos.close();
        System.out.println("mapMatrix moveBackList 对象已序列化到: " + pathway);  // 输出成功信息

    }
}