/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.util.ArrayList;

/**
 *
 * @author lecottiy
 */
public class PublicVariables {
    //type énuméré pour la définition du statut de chaque utilisateur

    public enum UserStatus {

        ONLINE, OFFLINE, BUSY;
    }
    static ArrayList<Communicate> CommunicateList = new ArrayList<Communicate>();
    ;
    static ArrayList<Message> MessageList = new ArrayList<Message>();
    static ArrayList<User> UserList = new ArrayList<User>();

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
        return this.CommunicateList;

    }

    public void setCommunicateList(ArrayList<Communicate> CommunicateList) {
        this.CommunicateList = CommunicateList;
    }

    public ArrayList<Message> getMessageList() {
        return MessageList;
    }

    public void setMessageList(ArrayList<Message> MessageList) {
        this.MessageList = MessageList;
    }

    public ArrayList<User> getUserList() {
        return UserList;
    }

    public void setUserList(ArrayList<User> UserList) {
        this.UserList = UserList;
    }

    class NonExistentUserException extends Exception {

        public NonExistentUserException() {
            System.out.println("Vous essayez d'instancier une classe Villeavec un nombre d'habitants négatif !");
        }
    }
}
