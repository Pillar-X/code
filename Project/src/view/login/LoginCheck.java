package view.login;

import Data.SignUp.DeserializeUserList;
import Data.Vector2D;
import view.level.LevelFrame;

import java.io.IOException;
import java.util.ArrayList;

public class LoginCheck {
    ArrayList<Vector2D> userList;
    private Vector2D vector2D;
    private LoginFrame loginFrame;
    private LevelFrame levelFrame;

    public LoginCheck(Vector2D vector2D,LoginFrame loginFrame) throws IOException, ClassNotFoundException {
        this.userList = DeserializeUserList.deserializeUserList("UserList.ser");
        System.out.println("已经注册好的账号：");
        for(Vector2D v:userList){
            System.out.println(v);
        }

        this.loginFrame = loginFrame;
        this.vector2D = vector2D;
        this.levelFrame = loginFrame.getLevelFrame();
        if(checkIfEmpty()){
            loginFrame.setNote("Please enter your username and password");
        }
        else{
            if(checkIfRight()){
                loginFrame.setNote("");
                levelFrame.setVisible(true);
                loginFrame.setVisible(false);
            }
            else{
                loginFrame.setNote("Username or password is incorrect");
            }
        }
    }

    public boolean checkIfEmpty (){
        if(vector2D.getPassword().isEmpty() || vector2D.getUsername().isEmpty()){
            return true;
        }
        else return false;
    }

    public boolean checkIfRight (){
        for(Vector2D v : userList){
            if(v.getUsername().equals(vector2D.getUsername()) && v.getPassword().equals(vector2D.getPassword())){
                return true;
            }
        }
        return false;
    }
}
