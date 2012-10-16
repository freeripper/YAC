package client;

import java.io.*;
import java.net.Socket;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import resources.PublicVariables.UserStatus;
import resources.PublicVariables.NonExistentUserException;
import resources.*;
import client.ClientVariables;
import client.ClientVariables.*;

import java.net.UnknownHostException;

public class Communicate  implements Runnable {

    PublicVariables publicVariables = new PublicVariables();
    NonExistentUserException nex;
    InputStream inStream;
    OutputStream outStream;
    DataInputStream inData;
    DataOutputStream outData;
    Socket socket;

    Communicate(Socket socket){
              this.socket = socket;

    }
    
    public void run() {

            

        try {
            //Récuperation d'un flot d'entée
            inStream = socket.getInputStream();
            //Récuperation d'un flot de sortie
            outStream = socket.getOutputStream();
            //Création d'un flot d'entrée pour données typées
            inData = new DataInputStream(inStream);
            //Création d'un flot de sortie pour données typées
            outData = new DataOutputStream(outStream);

            connectUser();
            
        } catch (Exception e) {
            System.out.print("Exceptionnnn = " + e.toString());
              e.printStackTrace();
        }


    }
  

    //écoute permanente
    public void listen() {
        while (true) {
            try {
                //lecture du message envoyé par le client
                int messageType = inData.readInt();
                //lancement des différentes fonctions en fonction du type de message reçu
                switch (messageType) {
                    case 2: //nom d'utilisateur ou mot de passe invalide
                        connexionFailed();
                        break;
                    case 3: //extinction du serveur
                        serverShuttingDown();
                        break;
                    case 4: //succès de la connexion
                        connexionSucceeded();
                        break;

                    case 10: //réception d'un message depuis le serveur
                        receiveMessageFromServer();
                        break;

                    case 30: //réception d'une mise à jour de l'état de connexion d'un contact
                        updateRemoteUserStatus();
                        break;
                    case 31: //réception d'une mise à jour du pseudo d'un contact

                }
            } catch (IOException ex) {
                Logger.getLogger(Communicate.class.getName()).log(Level.SEVERE, null, ex);
                try {
                    socket.close();
                } catch (IOException ex1) {
                    Logger.getLogger(Communicate.class.getName()).log(Level.SEVERE, null, ex1);
                }
            }
        }
    }

    //connexion de l'utilisateur
    public void connectUser() {
        try {
            //envoi du type 0, qui correspond à une demande de connexion
            outData.writeInt(0);
            //envoi de l'username
            outData.writeUTF(ClientVariables.getConnectedUser().getUsername());
            //envoi du password
            System.out.println(ClientVariables.getConnectedUser().getPassword());
            outData.writeUTF(ClientVariables.getConnectedUser().getPassword());
            //on écoute la réponse du serveur
            listen();

        } catch (IOException ex) {
            Logger.getLogger(Communicate.class.getName()).log(Level.SEVERE, null, ex);
        }


    }

    public void connexionFailed() {
         ClientVariables.getGui().showServerWrongCredentialsError();
    }

    public void connexionSucceeded() {
        //si la connexion a réussi, on affiche un beau message !
        ClientVariables.getGui().connexionSucceeded();
    }

    //déconnexion de l'utilisateur
    public void serverShuttingDown() {
        try {

            //fermeture du socket
            socket.close();

            //il faudrait aussi informer l'utilisateur avec un message d'alerte, et revenir à la fenêtre d'accueil


        } catch (IOException ex) {
            Logger.getLogger(Communicate.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void receiveMessageFromServer() {
        try {
            //détermine le user d'origine à partir de son username, puis transmet au GUI

            //on commence par trouver à quel user cet username correspond
            User srcUser = getUserFromUsername(inData.readUTF());

            //on récupère le texte du message et on crée un objet message (contenu du message, srcUser)
            Message message = new Message(inData.readUTF(), srcUser);

            //puis on transmet le message au GUI
            //dstUser.getCommunicateObject().sendMessageToClient(message);


        } catch (NonExistentUserException ex) {
            Logger.getLogger(Communicate.class.getName()).log(Level.SEVERE, null, ex);

        } catch (IOException ex2) {
            Logger.getLogger(Communicate.class.getName()).log(Level.SEVERE, null, ex2);
        }



    }

    public void forwardFileTransferPort() { //User user ?
        //forwarde 
    }

    public void sendMessageToServer(Message message) {
        //envoyer au client le message forwardé par le socket dédié à l'autre client

        try {

            //envoi de l'int 10, qui correspond à un envoi de message
            outData.writeInt(10);

            //envoi de l'utilisateur source (c'est nous ! :p)
            outData.writeUTF(message.getSrcUser().getUsername());

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

            //envoi du numéro de port (valeur bidon)
            outData.writeInt(133742);

            //ici, faudra appeler la fonction qui lance un nouveau socket


        } catch (IOException ex) {
            Logger.getLogger(Communicate.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public synchronized void updateUserNick() {
        //envoi du nouveau nick au serveur
        try {
            //envoi du code 31: mise à jour du nick
            outData.writeInt(31);

            //envoi du nouveau nick
            outData.writeUTF(ClientVariables.getConnectedUser().getNick());


        } catch (IOException ex) {
            Logger.getLogger(Communicate.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public synchronized void updateUserStatus() {
        //envoi du nouveau statut au serveur
        try {
            //envoi du code 30: mise à jour du statut
            outData.writeInt(30);

            //envoi du nouveau statut
            outData.writeUTF(String.valueOf(ClientVariables.getConnectedUser().getStatus()));


        } catch (IOException ex) {
            Logger.getLogger(Communicate.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateRemoteUserNick() {
        try {
            //lecture du username concerné
            User remoteUser = getUserFromUsername(inData.readUTF());
            //on met à jour le nick du user concerné
            remoteUser.setNick(inData.readUTF());

        } catch (NonExistentUserException ex) {
            Logger.getLogger(Communicate.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Communicate.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void updateRemoteUserStatus() {
        //réception depuis le serveur du nouveau statut d'un des contacts
        try {
            //lecture du username concerné
            User remoteUser = getUserFromUsername(inData.readUTF());

            //on met à jour le statut du user concerné
            remoteUser.setStatus(UserStatus.valueOf(inData.readUTF()));

        } catch (NonExistentUserException ex) {
            Logger.getLogger(Communicate.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Communicate.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //méthode "synchronized" pour l'exclusion mutuelle
    public synchronized User getUserFromUsername(String username) throws NonExistentUserException {

        User user = null;
        //recherche, dans la liste des utilisateurs enregistrés, de l'utilisateur ayant le même username que celui envoyé par le client
        for (int i = 0; i < PublicVariables.usersList.size(); i++) {
            if (PublicVariables.usersList.get(i).getUsername().equals(username)) {
                user = PublicVariables.usersList.get(i);
            }
        }
        if (user == null) {
            throw publicVariables.new NonExistentUserException();

        }
        return user;
    }
}
