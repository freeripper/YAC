package client;

import java.awt.Image;
import java.awt.Toolkit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JOptionPane;

public class Window extends javax.swing.JFrame {

    Window() {
        setIcon();
        setSystemLookAndFeel();
    }
    

    private void setIcon() {
        Image im = Toolkit.getDefaultToolkit().getImage(
                getClass().getResource("/resources/yak_icone.png"));

        this.setIconImage(im);
    }

private void setSystemLookAndFeel() {
               UIManager UIManager = new UIManager();
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        

} catch (ClassNotFoundException ex) {
            Logger.getLogger(GUI.class  

.getName()).log(Level.SEVERE, null, ex);
        } 

catch (InstantiationException ex) {
            Logger.getLogger(GUI.class  

.getName()).log(Level.SEVERE, null, ex);
        } 

catch (IllegalAccessException ex) {
            Logger.getLogger(GUI.class  

.getName()).log(Level.SEVERE, null, ex);
        } 

catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(GUI.class  

.getName()).log(Level.SEVERE, null, ex);
        }
    }
}