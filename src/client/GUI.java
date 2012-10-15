/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author roussfra
 */
public class GUI extends Window {

    public Client client;
    public ArrayList<Window> _unnamed_Window_ = new ArrayList<Window>();

//	public void openChat(User user) {
//		throw new UnsupportedOperationException();
//	}
    public void closeChat() {
    }

    public void closeWindow() {
    }

    public void handleMessage() {
    }

    /**
     * Creates new form GUI2
     */
    public GUI() {

        initComponents();
        friend_jScrollPane.setVisible(false);
        friend_jPanel.setVisible(false);
        connection_jPanel.setSize(this.getHeight(), this.getWidth());
        disconnect_jMenuItem.setVisible(false);
        this.setVisible(true);

        client = new Client(); //c'est bon ça ? pas sûr, il faudrait que client soit en static

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu1 = new javax.swing.JMenu();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        jScrollPane1 = new javax.swing.JScrollPane();
        connection_jPanel = new javax.swing.JPanel();
        username_jTextField = new javax.swing.JTextField();
        connect_jButton = new javax.swing.JButton();
        password_jPasswordField = new javax.swing.JPasswordField();
        yac = new javax.swing.JLabel();
        serverAddress_jTextField = new javax.swing.JTextField();
        serverPort_jTextField = new javax.swing.JTextField();
        friend_jScrollPane = new javax.swing.JScrollPane();
        friend_jPanel = new javax.swing.JPanel();
        nickname_jTextField = new javax.swing.JTextField();
        status_jComboBox = new javax.swing.JComboBox();
        userslist_jScrollPane = new javax.swing.JScrollPane();
        user_jList = new javax.swing.JList();
        menuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        disconnect_jMenuItem = new javax.swing.JMenuItem();
        exitMenuItem = new javax.swing.JMenuItem();
        helpMenu = new javax.swing.JMenu();
        aboutMenuItem = new javax.swing.JMenuItem();

        jMenu1.setText("jMenu1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(300, 500));

        connection_jPanel.setName(""); // NOI18N

        username_jTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        username_jTextField.setText("Username");

        connect_jButton.setText("Connect");
        connect_jButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                connect_jButtonActionPerformed(evt);
            }
        });

        password_jPasswordField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        password_jPasswordField.setText("Password");

        yac.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/yak_icone.png"))); // NOI18N
        yac.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        yac.setPreferredSize(new java.awt.Dimension(1231, 854));

        serverAddress_jTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        serverAddress_jTextField.setText("localhost");
        serverAddress_jTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                serverAddress_jTextFieldActionPerformed(evt);
            }
        });

        serverPort_jTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        serverPort_jTextField.setText("55555");

        javax.swing.GroupLayout connection_jPanelLayout = new javax.swing.GroupLayout(connection_jPanel);
        connection_jPanel.setLayout(connection_jPanelLayout);
        connection_jPanelLayout.setHorizontalGroup(
            connection_jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(username_jTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE)
            .addComponent(password_jPasswordField)
            .addComponent(connect_jButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, connection_jPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(yac, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(serverAddress_jTextField, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(serverPort_jTextField, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        connection_jPanelLayout.setVerticalGroup(
            connection_jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(connection_jPanelLayout.createSequentialGroup()
                .addComponent(yac, javax.swing.GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)
                .addGap(43, 43, 43)
                .addComponent(serverAddress_jTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(serverPort_jTextField)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(username_jTextField)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(password_jPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(connect_jButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        friend_jScrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        friend_jScrollPane.setMaximumSize(new java.awt.Dimension(767, 32767));
        friend_jScrollPane.setMinimumSize(new java.awt.Dimension(230, 230));

        nickname_jTextField.setText("Nickname");

        status_jComboBox.setMaximumRowCount(3);
        status_jComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Avaible", "Busi", "Offline", " " }));

        user_jList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "User 1", "User 2", "User 3", "User 4", "User 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        user_jList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        user_jList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                user_jListMouseClicked(evt);
            }
        });
        userslist_jScrollPane.setViewportView(user_jList);

        javax.swing.GroupLayout friend_jPanelLayout = new javax.swing.GroupLayout(friend_jPanel);
        friend_jPanel.setLayout(friend_jPanelLayout);
        friend_jPanelLayout.setHorizontalGroup(
            friend_jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(friend_jPanelLayout.createSequentialGroup()
                .addGroup(friend_jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(userslist_jScrollPane)
                    .addComponent(status_jComboBox, 0, 246, Short.MAX_VALUE)
                    .addComponent(nickname_jTextField))
                .addGap(252, 252, 252))
        );
        friend_jPanelLayout.setVerticalGroup(
            friend_jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(friend_jPanelLayout.createSequentialGroup()
                .addComponent(nickname_jTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(status_jComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(userslist_jScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
                .addGap(164, 164, 164))
        );

        friend_jScrollPane.setViewportView(friend_jPanel);

        fileMenu.setMnemonic('f');
        fileMenu.setText("File");

        disconnect_jMenuItem.setText("Disconnect");
        disconnect_jMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                disconnect_jMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(disconnect_jMenuItem);

        exitMenuItem.setMnemonic('x');
        exitMenuItem.setText("Exit");
        exitMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        helpMenu.setMnemonic('h');
        helpMenu.setText("Help");

        aboutMenuItem.setMnemonic('a');
        aboutMenuItem.setText("About");
        aboutMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aboutMenuItemActionPerformed(evt);
            }
        });
        helpMenu.add(aboutMenuItem);

        menuBar.add(helpMenu);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(connection_jPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(friend_jScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(connection_jPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(friend_jScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 254, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMenuItemActionPerformed
        //envoyer un disconnect()
        System.exit(0);
    }//GEN-LAST:event_exitMenuItemActionPerformed

    private void connect_jButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_connect_jButtonActionPerformed
        // TODO add your handling code here:
        if (serverAddress_jTextField.getText().isEmpty() == true) {
            JOptionPane username_error = new JOptionPane();
            username_error.showMessageDialog(null, "The field \"Server address\" is empty.");

        } else if (serverPort_jTextField.getText().isEmpty() == true) {
            JOptionPane username_error = new JOptionPane();
            username_error.showMessageDialog(null, "The field \"Server port\" is empty.");

        } else if (username_jTextField.getText().isEmpty() == true) {
            JOptionPane username_error = new JOptionPane();
            username_error.showMessageDialog(null, "The field \"Username\" is empty.");

        } else if (password_jPasswordField.getPassword().length == 0) {
            JOptionPane password_error = new JOptionPane();
            password_error.showMessageDialog(null, "The field \"Password\" is empty.");

        } else {
            try {
                //si tous les champs sont OK, on tente la connexion
                Client client = new Client();
                client.connectToServer(serverAddress_jTextField.getText(), serverPort_jTextField.getText());
            } catch (UnknownHostException ex) {
                //si l'adresse n'est pas bonne
                JOptionPane error = new JOptionPane();
                error.showMessageDialog(null, "Could not connect to server: unknown host.");
            } catch (IOException ex) {
                Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

       



    }//GEN-LAST:event_connect_jButtonActionPerformed

    private void aboutMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aboutMenuItemActionPerformed
        // TODO add your handling code here:
        About aboutWindow = new About();
    }//GEN-LAST:event_aboutMenuItemActionPerformed

    private void disconnect_jMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_disconnect_jMenuItemActionPerformed
        // TODO add your handling code here:
        // envoyer le disconnect()
        friend_jPanel.setVisible(false);
        connection_jPanel.setVisible(true);
        disconnect_jMenuItem.setVisible(false);


    }//GEN-LAST:event_disconnect_jMenuItemActionPerformed

    private void user_jListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_user_jListMouseClicked
        // TODO add your handling code here:
        chatWindow chatWindow = new chatWindow();
    }//GEN-LAST:event_user_jListMouseClicked

    private void serverAddress_jTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_serverAddress_jTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_serverAddress_jTextFieldActionPerformed

    public void showServerWrongCredentialsError() {
        JOptionPane error = new JOptionPane();
        error.showMessageDialog(null, "The username and password is incorrect. Please try again.");
    }

    public void showServerShuttingDownNotification() {
        JOptionPane error = new JOptionPane();
        error.showMessageDialog(null, "Disconnected: server shutting down.");
    }
    

    public void showFileTransferFailedError() {
        JOptionPane error = new JOptionPane();
        error.showMessageDialog(null, "File transfer failed. Please try again");
    }
    
        public void showConnexionFailedError() {
        JOptionPane error = new JOptionPane();
        error.showMessageDialog(null, "Connexion to server failed.");
    }

    public void connexionSucceeded() {
        JOptionPane error = new JOptionPane();
        error.showMessageDialog(null, "Connexion successful !");
         friend_jScrollPane.setVisible(true);
        friend_jPanel.setVisible(true);
        connection_jPanel.setVisible(false);
        disconnect_jMenuItem.setVisible(true);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem aboutMenuItem;
    private javax.swing.JButton connect_jButton;
    private javax.swing.JPanel connection_jPanel;
    private javax.swing.JMenuItem disconnect_jMenuItem;
    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JPanel friend_jPanel;
    private javax.swing.JScrollPane friend_jScrollPane;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JTextField nickname_jTextField;
    private javax.swing.JPasswordField password_jPasswordField;
    private javax.swing.JTextField serverAddress_jTextField;
    private javax.swing.JTextField serverPort_jTextField;
    private javax.swing.JComboBox status_jComboBox;
    private javax.swing.JList user_jList;
    private javax.swing.JTextField username_jTextField;
    private javax.swing.JScrollPane userslist_jScrollPane;
    private javax.swing.JLabel yac;
    // End of variables declaration//GEN-END:variables
}
