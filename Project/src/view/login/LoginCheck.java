package view.login;

import Data.SignUp.DeserializeUserList;
import Data.Vector2D;

import SetUp.SetUpFrame;
import controller.FrameController;
import controller.MusicController;
import view.level.LevelFrame;

import java.io.IOException;
import java.util.ArrayList;

public class LoginCheck {
    ArrayList<Vector2D> userList;
    private Vector2D vector2D;
    private LoginFrame loginFrame;
    private LevelFrame levelFrame;
    private SetUpFrame setUpFrame;


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
                setUpFrame = new SetUpFrame(500,500, vector2D.getUsername());
                FrameController.setSetUpFrame(setUpFrame);
//                MusicController.playBGM2();

            }
            else{
                loginFrame.setNote("Username or password is incorrect");
            }
        }

    }

    public SetUpFrame getSetUpFrame() {
        return setUpFrame;
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
