/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import resources.User;

/**
 *
 * @author youri
 */

 public class ClientVariables {

        protected static User user;
        //public ArrayList<Message> _Message_List = new ArrayList<Message>();
        protected static Communicate communicate;
        protected static GUI gui;
        protected static chatWindow chatWindow;
        protected static User connectedUser;

    public static client.chatWindow getChatWindow() {
        return chatWindow;
    }

    public static void setChatWindow(client.chatWindow chatWindow) {
        ClientVariables.chatWindow = chatWindow;
    }

    public static User getConnectedUser() {
        return connectedUser;
    }

    public static void setConnectedUser(User connectedUser) {
        ClientVariables.connectedUser = connectedUser;
    }
        
        ClientVariables(){
            //instanciation de l'interface utilisateur
            this.gui = new GUI();
            this.chatWindow = new chatWindow();
            this.chatWindow.setVisible(false);
        }
        

        public static User getUser() {
            return user;
        }

        public static void setUser(User user) {
            ClientVariables.user = user;
        }

        public static Communicate getCommunicate() {
            return communicate;
        }

        public static void setCommunicate(Communicate communicate) {
            ClientVariables.communicate = communicate;
        }

        public static GUI getGui() {
            return gui;
        }

        public static void setGui(GUI gui) {
            ClientVariables.gui = gui;
        }
    }