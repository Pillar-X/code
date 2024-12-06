package Data.GameArchive;
import model.MapMatrix;

import java.io.*;
import java.util.ArrayList;

public class SerializeGame {
    private String pathway;


    public static void serializeGame(String pathway, ArrayList<MapMatrix> mapMatrixList) throws IOException {

        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(pathway)) ;
        oos.reset();
        oos.writeObject(mapMatrixList);  // 将 MapMatrix 对象写入文件
        oos.close();
        System.out.println("MapMatrixList 对象已序列化到: " + pathway);  // 输出成功信息

    }



}
