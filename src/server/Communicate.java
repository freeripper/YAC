package server;

import resources.User;
import resources.Message;
import java.io.*;
import java.net.Socket;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import resources.PublicVariables.UserStatus;
import resources.PublicVariables.NonExistentUserException;
import resources.PublicVariables;
//plop !

public class Communicate implements Runnable {

    PublicVariables publicVariables = new PublicVariables();
    NonExistentUserException nex;
    public User connectedUser;
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
        //lecture du nom d'utilisateur envoyé par le client
        try {
            try {
                String username = inData.readUTF();

                //récupération de l'utilisateur concerné
                connectedUser = getUserFromUsername(username);

                //récupération du mot de passe envoyé par le client
                String password = inData.readUTF();
                
                //si le mot de passe envoyé par le client est le même que celui stocké sur le serveur
                if (password.equals(connectedUser.getPassword())) {
                    return true;
                } else {
                    return false;
                }

            } catch (NonExistentUserException neue) {
                System.out.println("Utilisateur inexistant");

                //il faut également envoyer un message d'erreur au client
                //code 2: nom d'utilisateur ou mot de passe invalide
                outData.writeInt(2);

                //il faut déconnecter le socket
                disconnectUser();
                //puis fermer le thread ?

            }


        } catch (IOException ex) {
            Logger.getLogger(Communicate.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;


    }

    //écoute permanente
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
                    case 10: //envoi d'un message depuis le client
                        forwardMessageFromClient();
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

    //connexion de l'utilisateur
    public void connectUser() {
        try {
            //si les identifiants fournis par le client correspondent à ceux stockés sur le serveur...
            if (checkCredentials()) {
                //envoi au client du code 4: "succès de la connexion"
                outData.writeInt(4);
            } else {
                //envoi au client du code 2: "nom d'utilisateur ou mot de passe invalide"
                outData.writeInt(2);
            }

        } catch (IOException ex) {
            Logger.getLogger(Communicate.class.getName()).log(Level.SEVERE, null, ex);
        }


    }

    //déconnexion de l'utilisateur
    public void disconnectUser() {
        try {
            //mise à jour du statut du contact
//            connectedUser.setStatus(UserStatus.OFFLINE);


            //fermeture du socket
            socket.close();
            
            //une fois le socket fermé, il faut aussi killer le thread !


        } catch (IOException ex) {
            Logger.getLogger(Communicate.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void forwardMessageFromClient() {
        try {
            //détermine le user de réception à partir de son username, puis appelle le sendMessage(...) du socket de cet user en lui transmettant le message envoyé

            //on commence par trouver à quel user cet username correspond
            User dstUser = getUserFromUsername(inData.readUTF());

            //on récupère le texte du message et on crée un objet message (contenu du message, srcUser)
            Message message = new Message(inData.readUTF(), connectedUser);

            //puis on transmet le message au socket dédié au destinataire
            dstUser.getCommunicateObject().sendMessageToClient(message);


        } catch (NonExistentUserException ex) {
            Logger.getLogger(Communicate.class.getName()).log(Level.SEVERE, null, ex);

        } catch (IOException ex2) {
            Logger.getLogger(Communicate.class.getName()).log(Level.SEVERE, null, ex2);
        }



    }

    public void forwardFileTransferPort() { //User user ?
        //forwarde 
    }

    public void sendMessageToClient(Message message) {
        //envoyer au client le message forwardé par le socket dédié à l'autre client

        try {

            //envoi de l'int 10, qui correspond à un envoi de message
            outData.writeInt(10);

            //envoi de l'utilisateur source
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
        //réception du nouveau nick du client
        try {
            //réception du nouveau nick du client
            String nick = inData.readUTF();

            //on met à jour le nick de l'user dans l'objet qui lui est dédié
            connectedUser.setNick(nick);

            //puis on envoie à tous les clients:
            //-connectés
            //-étant dans la liste d'amis de l'user dont on met à jour le nick
            //le nouveau nick de l'user
            //pour cela, on fait une boucle
            for (int i = 0; i < publicVariables.getUserList().size(); i++) {
                //si, dans la liste de contacts de l'user, un user est connecté...
                if (publicVariables.getUserList().get(i).getStatus().equals(UserStatus.ONLINE)) {
                    //alors on lui transmet le nouveau nick de l'user
                    publicVariables.getUserList().get(i).getCommunicateObject().updateContactNick(connectedUser);
                }
            }


        } catch (IOException ex) {
            Logger.getLogger(Communicate.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public synchronized void updateUserStatus() {
        try {
            //réception du nouveau statut du client
            String utfStatus = inData.readUTF();

            //on met à jour le statut de l'user dans l'objet qui lui est dédié
            connectedUser.setStatus(UserStatus.valueOf(utfStatus));

            //puis on envoie à tous les clients:
            //-connectés
            //-étant dans la liste d'amis de l'user dont on met à jour le statut
            //le nouveau statut de l'user
            //pour cela, on fait une boucle
            for (int i = 0; i < publicVariables.getUserList().size(); i++) {
                //si, dans la liste de contacts de l'user, un user est connecté...
                if (publicVariables.getUserList().get(i).getStatus().equals(UserStatus.ONLINE)) {
                    //alors on lui transmet le nouveau statut de l'user
                    publicVariables.getUserList().get(i).getCommunicateObject().updateContactStatus(connectedUser);
                }
            }


        } catch (IOException ex) {
            Logger.getLogger(Communicate.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateContactNick(User user) {
        try {
            //envoi au client du nouveau nick d'un de ses contacts
            outData.writeInt(31);
            outData.writeUTF(user.getUsername());
            outData.writeUTF(user.getNick());
        } catch (IOException ex) {
            Logger.getLogger(Communicate.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void updateContactStatus(User user) {
        //envoi au client du nouveau statut d'un de ses contacts
        try {
            //envoi au client du nouveau nick d'un de ses contacts
            outData.writeInt(31);
            outData.writeUTF(user.getUsername());
            //renvoie une version UTFisée du type énuméré userStatus
            outData.writeUTF(String.valueOf(user.getStatus()));
        } catch (IOException ex) {
            Logger.getLogger(Communicate.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //méthode "synchronized" pour l'exclusion mutuelle
    public synchronized User getUserFromUsername(String username) throws NonExistentUserException {

        User user = null;
        //recherche, dans la liste des utilisateurs enregistrés, de l'utilisateur ayant le même username que celui envoyé par le client
        for (int i = 0; i < PublicVariables.usersList.size(); i++) {
            if (PublicVariables.usersList.get(i).getUsername() == username) {
                user = PublicVariables.usersList.get(i);
            }
        }
        if (user == null) {
            throw publicVariables.new NonExistentUserException();

        }
        return user;
    }
}
