package Data.SignUp;

import Data.Vector2D;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class SerializeUserList {

    public void serializeUserList(ArrayList<Vector2D> userList) throws FileNotFoundException, IOException {
        String filePath = "UserList.ser";
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("UserList.ser"));
        oos.reset();
        oos.writeObject(userList);
        oos.close();
    }

}
