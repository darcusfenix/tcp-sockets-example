/*
 * The MIT License
 *
 * Copyright 2015 darcusfenix.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package cliente;

import java.awt.Button;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import ui.JFrameClientConnection;
import ui.JFrameClientIndex;

/**
 *
 * @author darcusfenix
 */
public class Cliente {

    private JFrameClientConnection frameConnection;
    private JFrameClientIndex frameIndex;

    public Cliente() {
        frameIndex = new JFrameClientIndex();
        frameConnection = new JFrameClientConnection();
        init();
    }

    public void init() {
        frameConnection.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameConnection.setLocationRelativeTo(null);
        frameConnection.pack();
        frameConnection.setVisible(true);
    }

    public void ventana() {
        frameConnection.setVisible(false);
        frameIndex.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameIndex.setLocationRelativeTo(null);
        frameIndex.pack();
        frameIndex.setVisible(true);
    }
    
    public static void iniciarConexion(String ip, Integer puerto)  throws IOException {
        
        Socket socket = null;
       
        socket = new Socket(ip, puerto);

        File file = new File("/home/darcusfenix/Descargas/analisis.pdf");
        // Get the size of the file
        Long length = file.length();
        byte[] bytes = new byte[16 * 1024];
        InputStream in = new FileInputStream(file);
        OutputStream out = socket.getOutputStream();
        
        DataOutputStream outToServer = new DataOutputStream(socket.getOutputStream());
        
        outToServer.writeLong(length);
        System.err.println("CLIENTE: " + file.length());

        int count;
        while ((count = in.read(bytes)) > 0) {
            out.write(bytes, 0, count);
            
        }

        out.close();
        in.close();
        socket.close();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        /*
         JFrameClientConnection frame = new JFrameClientConnection();
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
         */
        new Cliente();
    }

}