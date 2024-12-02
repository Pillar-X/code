package Data.GameArchive;
import model.MapMatrix;

import java.io.*;

public class SerializeGame {
    private String pathway;

    public static void serializeGame(String pathway, MapMatrix mapMatrix) throws IOException {

        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(pathway)) ;
        oos.writeObject(mapMatrix);  // 将 MapMatrix 对象写入文件
        System.out.println("MapMatrix 对象已序列化到: " + pathway);  // 输出成功信息

    }



}
