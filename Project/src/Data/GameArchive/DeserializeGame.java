package Data.GameArchive;
import model.MapMatrix;

import java.io.*;
import java.util.ArrayList;

public class DeserializeGame {
    //private String pathway;
    //private  int levelNumber;
   // private  ArrayList<MapMatrix> mapMatrixList;



    public static ArrayList<MapMatrix> deserializeGame(String pathway, int levelNumber) throws IOException, ClassNotFoundException {
        ArrayList<MapMatrix> mapMatrixList= new ArrayList<>();
        MapMatrix temMatrix;
        File file = new File(pathway);


        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            while (true) {
                try {
                    temMatrix = (MapMatrix) ois.readObject();
                    if (temMatrix.getLevelNumber() == levelNumber) {
                        mapMatrixList.add(temMatrix);

                    }
                } catch (EOFException e) {
                    break;
                }
            }
        }

        return mapMatrixList;
    }


}



