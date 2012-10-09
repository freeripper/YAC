package server;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.*;
import java.util.ArrayList;

/**
 *
 * @author lecottiy
 */
public class Server {



    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {


        //création d'un user bidon
        User testUser = new User("razounette", "mdp");
        PublicVariables.UserList.add(testUser);
        System.out.println(PublicVariables.UserList.get(0).getNick());



        try {
            //récupération du paramètre contenant le port d'écoute
            int port = Integer.parseInt(args[0]); //catch ArrayIndexOutOfBoundsException
            //ouverture d'un port
            if (port < 1024) {
                System.out.println("Ce port est réservé à root.");
            } else if (port > 65535) {
                System.out.println("Entrez un port inférieur ou égal à 65535.");
            } else { //si le port est valide
                //lancer l'écoute sur le port indiqué
                listen(port);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Veuillez entrer un numéro de port valide");
        }



    }

    //méthode qui lance le socket de dispatch
    public static void listen(Integer port) {
        try {
            //création du socket d'écoute, celui qui dispatche les connexions
            ServerSocket socket_ecoute = new ServerSocket(port); // catch java.Net.BindException

            //boucle pour poursuivre l'écoute une fois un client dispatché sur un socket
            while (true) {

                //acceptation de la connexion du client
                Socket socket_communication = socket_ecoute.accept();
                //création d'un nouveau thread de l'objet Communicate et redirection du nouveau socket vers ce thread
                Thread communicationThread = new Thread(new Communicate(socket_communication));
                //démarrage du thread
                communicationThread.start();

            }
        } catch (java.net.BindException e) {
            System.out.println("Ce port est déjà occupé.");

        } catch (Exception e) {
            System.out.println("Erreur lors de la création du serveur.");
        }
    }

    //gestion du bouton "quitter" ou de la fermeture de la fenêtre
    public void exit() {
        //throw new UnsupportedOperationException();
    }
}