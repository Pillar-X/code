package Data;
import java.io.Serializable;

public class Vector2D implements Serializable {
    private String username;
    private String password;

    public Vector2D(String username,String password){
        this.username = username;
        this.password = password;
    }



    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "Vector2D{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
