/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import ui.NewJFrame;

/**
 *
 * @author darcusfenix
 */
public class Cliente {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        
        NewJFrame frame = new NewJFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);
        
        
        JFileChooser chooser = new JFileChooser();
        chooser.setMultiSelectionEnabled(true);

        // Show the dialog; wait until dialog is closed
        chooser.showOpenDialog(frame);

        // Retrieve the selected files.
        File[] files = chooser.getSelectedFiles();
    }
    
}
