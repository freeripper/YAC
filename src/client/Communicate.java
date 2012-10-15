package client;

import client.Client;
import java.io.*;
import java.net.Socket;

public class Communicate {
	public User _unnamed_User_;
	public Client _unnamed_Client_;
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

	public void listen() {
		 while (true) {
            try {
                //lecture du message envoyé par le client
                int messageType = inData.readInt();
                //lancement des différentes fonctions en fonction du type de message reçu
                switch (messageType) {
                    case 10: //envoi d'un message
                        transmitMessageToGui();
                        break;
                    case 3: //envoi d'un fichier
                        //sendFile();
                        break;
                    

                }
            } catch (IOException ex) {
               // Logger.getLogger(Communicate.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
	}
        
        public void transmitMessageToGui(){
            //envoyer le message au gui
        }

	public void sendMessage() {
		throw new UnsupportedOperationException();
	}
        

	public void sendFile() {
		throw new UnsupportedOperationException();
	}

	public void sentFileTransferPort() {
		throw new UnsupportedOperationException();
	}
}