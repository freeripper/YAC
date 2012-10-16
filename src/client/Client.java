package client;

import java.util.ArrayList;
import resources.User;
import client.ClientVariables;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

    public static void main(String[] args) {
        //instanciation des variables publiques, qui du même coup lancent l'interface graphique
        new ClientVariables();

    }
    //connexion au serveur

    public void connectToServer(String server, String port) throws UnknownHostException, IOException {
        try {

            Socket socket = new Socket(server, Integer.parseInt(port));
            //création d'un nouveau thread de l'objet Communicate et redirection du nouveau socket vers ce thread
            
            
            Thread communicateThread = new Thread(new Communicate(socket));
            ClientVariables.setCommunicate(communicateThread);
            //démarrage du thread
            communicateThread.start();
        } catch (java.net.ConnectException ex) {
            ClientVariables.getGui().showConnexionFailedError();
        }
    }
}
