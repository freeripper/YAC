/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

import java.util.ArrayList;
import server.Communicate;

/**
 *
 * @author lecottiy
 */
public class PublicVariables {
    //type énuméré pour la définition du statut de chaque utilisateur

    public enum UserStatus {

        ONLINE, OFFLINE, BUSY;
    }
    public static ArrayList<Communicate> communicateList = new ArrayList<Communicate>();
    ;
    public static ArrayList<Message> messageList = new ArrayList<Message>();
    public static ArrayList<User> usersList = new ArrayList<User>();

//    PublicVariables() {
//        //mémorisation des objets "Communicate"
//        CommunicateList = new ArrayList<Communicate>();
//        //mémorisation des objets "Message"
//        MessageList = new ArrayList<Message>();
//        //gestion de la liste des utilisateurs
//        UserList = new ArrayList<User>();
//
//    }
    public ArrayList<Communicate> getCommunicateList() {
        return this.communicateList;

    }

    public void setCommunicateList(ArrayList<Communicate> CommunicateList) {
        this.communicateList = CommunicateList;
    }

    public ArrayList<Message> getMessageList() {
        return messageList;
    }

    public void setMessageList(ArrayList<Message> MessageList) {
        this.messageList = MessageList;
    }

    public ArrayList<User> getUserList() {
        return usersList;
    }

    public void setUserList(ArrayList<User> UserList) {
        this.usersList = UserList;
    }

    public class NonExistentUserException extends Exception {

        public NonExistentUserException() {
            System.out.println("Vous essayez d'instancier une classe Villeavec un nombre d'habitants négatif !");
        }
    }
}
