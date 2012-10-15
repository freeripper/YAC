package resources;

import resources.PublicVariables;
import java.util.ArrayList;
import server.Communicate;

public class User {

    private String password;
//gestion de la liste des utilisateurs
    private String nick;
    private String username;
    private PublicVariables.UserStatus status;
    private Communicate communicateObject;

    public Communicate getCommunicateObject() {
        return communicateObject;
    }

    public void setCommunicateObject(Communicate communicateObject) {
        this.communicateObject = communicateObject;
    }



    public User(String username, String password) {
        setUsername(username);
        setPassword(password);
        setNick(username); //à la création de l'utilisateur, on lui donne pour nick son nom d'utilisateur, changeable par la suite
        setStatus(PublicVariables.UserStatus.OFFLINE);
    }
    

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNick() {
        return this.nick;
    }

    public void setNick(String nick) {
        this.nick = nick;

    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String aPassword) {
        this.password = password;
    }


    public PublicVariables.UserStatus getStatus() {
        return this.status;
    }

    public void setStatus(PublicVariables.UserStatus status) {
        this.status = status;
    }
}