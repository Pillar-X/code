package Data.GameArchive;

import model.MapMatrix;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class SerializeRecord {
    private String pathway;

    public static void serializeRecord(String pathway, MapMatrix mapMatrix) throws IOException {

        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(pathway)) ;
        oos.reset();
        oos.writeObject(mapMatrix);  // 将 MapMatrix 对象写入文件
        oos.close();

    }
}
