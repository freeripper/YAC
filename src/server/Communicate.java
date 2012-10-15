package server;

import java.io.*;
import java.net.Socket;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import server.PublicVariables.UserStatus;
import server.PublicVariables.NonExistentUserException;

public class Communicate implements Runnable {
    

    PublicVariables publicVariables = new PublicVariables();
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


            if (checkCredentials()) {
            }
        } catch (Exception e) {
            System.out.print("Exception = " + e.toString());
//                e.printStackTrace();
        }

    }

    public void run() {

        listen();


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
        while (true) {
            try {
                //lecture du message envoyé par le client
                int messageType = inData.readInt();
                //lancement des différentes fonctions en fonction du type de message reçu
                switch (messageType) {
                    case 0: //connexion
                        connectUser();
                        break;
                    case 1: //déconnexion
                        disconnectUser();
                        break;
                    case 10: //envoi d'un message
                        forwardMessage();
                        break;
                    case 3: //envoi d'un fichier
                        //sendFile();
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
    }

    public void connectUser() {
        try {

            //lecture du nom d'utilisateur envoyé par le client
            String username = inData.readUTF();
            try {
                //récupération de l'utilisateur concerné
                connectedUser = getUserFromUsername(username);
                
                
                
                
            } catch (NonExistentUserException neue) {
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
            //envoi de l'int 1, qui correspond à un ordre de déconnexion
            outData.writeInt(1);
            //fermeture du socket
            socket.close();


        } catch (IOException ex) {
            Logger.getLogger(Communicate.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void forwardMessage() {
        try {
            //détermine le user de réception à partir de son username, puis appelle le sendMessage(...) du socket de cet user en lui transmettant le message envoyé

            //on commence par trouver à quel user cet username correspond
            User dstUser = getUserFromUsername(inData.readUTF());

            //on récupère le texte du message et on crée un objet message
            Message message = new Message(inData.readUTF());

            //puis on transmet le message au socket dédié au destinataire
            dstUser.getCommunicateObject().sendMessage(message);


        } catch (NonExistentUserException ex) {
            Logger.getLogger(Communicate.class.getName()).log(Level.SEVERE, null, ex);

        } catch (IOException ex2) {
            Logger.getLogger(Communicate.class.getName()).log(Level.SEVERE, null, ex2);
        }



    }

    public void forwardFileTransferPort() { //User user ?
        //forwarde 
    }

    public void sendMessage(Message message) {
                    //envoyer au client le message forwardé par le socket dédié à l'autre client

        try {
            
            //envoi de l'int 10, qui correspond à un envoi de message
            outData.writeInt(10);
            
            //envoi du texte du message
            outData.writeUTF(message.getText());
        } catch (IOException ex) {
            Logger.getLogger(Communicate.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void sendFileTransferPort() {
        //envoyer au client le port de transfert forwardé par le socket dédié à l'autre client
                try {
            
            //envoi de l'int 20, qui correspond à un déclenchement d'envoi de fichier et de port de transfert
            outData.writeInt(20);
            
            //ici, faudra appeler la fonction qui lance un nouveau socket
            
            
        } catch (IOException ex) {
            Logger.getLogger(Communicate.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public synchronized void updateUserNick() {
        //réception du nouveau nick du client
    }

    public synchronized void updateUserStatus() {
        //réception du nouveau statut du client
    }

    public synchronized void updateContactNick(String username, String nick) {
        //envoi au client du nouveau nick d'un de ses contacts
    }

    public synchronized void updateContactStatus(String username, PublicVariables.UserStatus status) {
        //envoi au client du nouveau statut d'un de ses contacts
    }

    //méthode "synchronized" pour l'exclusion mutuelle
    public synchronized User getUserFromUsername(String username) throws NonExistentUserException {

        User user = null;
        //recherche, dans la liste des utilisateurs enregistrés, de l'utilisateur ayant le même username que celui envoyé par le client
        for (int i = 0; i < PublicVariables.UserList.size(); i++) {
            if (PublicVariables.UserList.get(i).getUsername().equals(username)) {
                user = PublicVariables.UserList.get(i);
            }
        }
        if (user == null) {
            throw publicVariables.new NonExistentUserException();

        }
        return user;
    }
}
