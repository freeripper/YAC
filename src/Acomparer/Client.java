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
        try{
        Socket socket = new Socket(server, Integer.parseInt(port));
        Communicate communicate = new Communicate(socket);
       ClientVariables.setCommunicate(communicate);
        }catch(java.net.ConnectException ex){
            ClientVariables.getGui().showConnexionFailedError();
        }
    }
   
}
