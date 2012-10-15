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
        
        ClientVariables(){
            //instanciation de l'interface utilisateur
            this.gui = new GUI();
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