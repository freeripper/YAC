package server;

import java.io.*;
import java.net.Socket;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import server.PublicVariables.UserStatus;
import server.PublicVariables;
import server.User;
import server.PublicVariables.NonExistentUserException;

public class Communicate implements Runnable {

    PublicVariables publicVariables  = new PublicVariables();
    
    NonExistentUserException nex;
    public User connectedUser;
    public Server _unnamed_Server_;
    InputStream inStream;
    OutputStream outStream;
    DataInputStream inData;
    DataOutputStream outData;
    Socket socket;

    public Communicate(Socket socket) {
        this.socket = socket;

        try {
            //Récuperation d'un flot d'entée
            inStream = socket.getInputStream();
            //Récuperation d'un flot de sortie
            outStream = socket.getOutputStream();
            //Création d'un flot d'entrée pour données typées
            inData = new DataInputStream(inStream);
            //Création d'un flot de sortie pour données typées
            outData = new DataOutputStream(outStream);


//                //création d'une sortie Byte
//                outData.writeByte(42);
//                //Création d'une sortie Int
//                outData.writeInt(42);
//                //Création d'une sortie float
//                outData.writeFloat(Float.parseFloat("2.0"));
//                //Création d'une sortie String
//                outData.writeUTF("Quaranté-deux");




            if (checkCredentials()) {
            }
        } catch (Exception e) {
            System.out.print("Exception = " + e.toString());
//                e.printStackTrace();
        }

    }

    public void run() {
    }

    //vérification des identifiants de l'utilisateur
    public boolean checkCredentials() {
        try {


            //récupération username/password
            String username = inData.readUTF();
            String password = inData.readUTF();


        } catch (IOException ex) {
            Logger.getLogger(Communicate.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;


    }

    public void listen() {
        try {
            //lecture du message envoyé par le client
            int messageType = inData.readInt();
            switch (messageType) {
                case 0: //connexion
                    connectUser();
                    break;
                case 1: //déconnexion
                    disconnectUser();
                    break;
                case 2: //envoi d'un message
                    sendMessage();
                    break;
                case 3: //envoi d'un fichier
                    sendFile();
                    break;
                case 4: //mise à jour du nickname
                    updateUserNick();
                    break;
                case 5: //mise à jour du statut
                    updateUserStatus();
                    break;

            }
        } catch (IOException ex) {
            Logger.getLogger(Communicate.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void connectUser() {
        try {

            //lecture du nom d'utilisateur envoyé par le client
            String username = inData.readUTF();
       try{     
 connectedUser = getUserFromUsername(username);
       }catch(NonExistentUserException neue){
           System.out.println("Utilisateur inexistant");
           //il faut également envoyer un message d'erreur au client
           //il faut déconnecter le socket et fermer le thread
           socket.close();
           
       }

        } catch (IOException ex) {
            Logger.getLogger(Communicate.class.getName()).log(Level.SEVERE, null, ex);
        }


    }

    public void disconnectUser() {
        try {
            //fermeture du socket
            socket.close();
        
        
        } catch (IOException ex) {
            Logger.getLogger(Communicate.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void sendMessage() {
    }

    public void sendFile() {
    }

    public void sendFileTransferPort() {
    }

    public void updateUserNick() {
    }

    public void updateUserStatus() {
    }
    
    //méthode "synchronized" pour l'exclusion mutuelle
    public synchronized User getUserFromUsername(String username) throws NonExistentUserException{
        
        User user = null;
        //recherche, dans la liste des utilisateurs enregistrés, de l'utilisateur ayant le même username que celui envoyé par le client
            for (int i = 0; i < PublicVariables.UserList.size(); i++) {
                if (PublicVariables.UserList.get(i).getUsername().equals(username)) {
                    user = PublicVariables.UserList.get(i);
                }
            }
            if (user == null){
                throw publicVariables.new NonExistentUserException();
                
            }
            return user;
    }
}
