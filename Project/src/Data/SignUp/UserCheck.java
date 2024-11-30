package Data.SignUp;

import Data.Vector2D;

import java.io.IOException;
import java.util.ArrayList;
import view.login.SignUpFrame;

public class UserCheck {
    private ArrayList<Vector2D> userList;
    private Vector2D vector2D;
    private String rePassword;
    private SignUpFrame signUpFrame;


    public UserCheck (Vector2D vector2D,String rePassword,SignUpFrame signUpFrame) throws IOException, ClassNotFoundException {

        this.signUpFrame = signUpFrame;
        this.userList = new ArrayList<>();
        this.userList = DeserializeUserList.deserializeUserList("UserList.ser");
        this.vector2D = vector2D;
        this.rePassword = rePassword;

        if(checkFormat()){
            if(checkRepeatPassword()){
                signUpFrame.setRepeatNoteLabel("The password is different");
                System.out.println("The password is different ");
            }
            else{
                signUpFrame.setRepeatNoteLabel("Accepted");
                if(checkUserName()){
                    AddUser();
                    signUpFrame.setVisible(false);
                }
            }
        }
    }

    public boolean checkRepeatPassword(){
        if(rePassword.equals(vector2D.getPassword())) return false;

        else return true;
    }

    public boolean checkFormat(){
        if(checkNameLength()){
            signUpFrame.setUserNoteLabel("The name needs to be 6-18 chapter");

            return false;
        }
        if(checkFirstCharacter()){
            signUpFrame.setUserNoteLabel("The name should begin with letter");
            return false;
        }
        if(checkNameCharacters()){
            signUpFrame.setUserNoteLabel("The name has illegal character");
            return false;
        }
        if(!checkUserName()){
            signUpFrame.setUserNoteLabel("The name repeat");
        }
        signUpFrame.setUserNoteLabel("Accepted");

        if(checkPasswordLength()){
            signUpFrame.setPassNoteLabel("The password should be 8-16 characters");
            return false;
        }
        if(checkPasswordCharacters()){
            signUpFrame.setPassNoteLabel("The password has illegal character");
            return false;
        }
        signUpFrame.setPassNoteLabel("Accepted");
        return true;
    }

    public boolean checkNameLength(){
        if(vector2D.getUsername().length()>18 || vector2D.getUsername().length()<6){
            return true;
        }
        else return false;
    }

    public boolean checkFirstCharacter(){
        if(vector2D.getUsername().charAt(0)<65 ||(vector2D.getUsername().charAt(0)>90 && vector2D.getUsername().charAt(0)<97) || vector2D.getUsername().charAt(0)>122){
            return true;
        }
        else return false;
    }


    public boolean checkNameCharacters (){
        for(int i=0;i<vector2D.getUsername().length();i++){
            if((vector2D.getUsername().charAt(i)<65 ||(vector2D.getUsername().charAt(i)>90 && vector2D.getUsername().charAt(i)<97) || vector2D.getUsername().charAt(i)>122)){
                if((vector2D.getUsername().charAt(i)<48 || vector2D.getUsername().charAt(i)>57) ) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean checkPasswordLength(){
        if(vector2D.getPassword().length()>16 || vector2D.getPassword().length()<8){
            return true;
        }
        else return false;
    }

    public boolean checkPasswordCharacters(){
        for(int i=0;i<vector2D.getPassword().length();i++){
            if((vector2D.getPassword().charAt(i)<65 ||(vector2D.getPassword().charAt(i)>90 && vector2D.getPassword().charAt(i)<97) || vector2D.getPassword().charAt(i)>122)){
                if((vector2D.getPassword().charAt(i)>57 || vector2D.getPassword().charAt(i)<48) ){
                    return true;
                }
            }
        }
        return false;
    }

    public void AddUser() throws IOException {
        this.userList.add(vector2D);
        SerializeUserList s = new SerializeUserList();
        s.serializeUserList(userList);
    }

    public boolean checkUserName(){
        boolean letItIn = true;
        for(Vector2D vec:userList){
            if(vec.getUsername().equals(vector2D.getUsername())){
                letItIn = false;
                break;
            }
        }
        if(letItIn) return true;
        else return false;
    }

    public void PrintOutUsers(){
        for(Vector2D v :userList){
            System.out.println(v);
        }
    }
}
