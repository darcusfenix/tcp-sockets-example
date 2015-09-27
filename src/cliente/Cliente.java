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

import bean.Archivo;
import java.awt.Button;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    public static Socket socket;
    public static InputStream in;
    public static OutputStream out;
    public static DataOutputStream outToMessage;

    public Cliente() {
        frameConnection = new JFrameClientConnection();
        init();
    }

    public void init() {
        frameConnection.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frameConnection.setLocationRelativeTo(null);
        frameConnection.pack();
        frameConnection.setVisible(true);
    }

    public static void iniciarConexion(String ip, Integer puerto) throws IOException {
        try {
            socket = new Socket(ip, puerto);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public static void enviarArchivos(List<Archivo> archivos) {
        for (Archivo archivo : archivos) {

            try {
                BufferedReader inFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                if (inFromServer.readLine().equals("1")) {

                    File file = new File(archivo.getRuta());

                    Long length = file.length();
                    byte[] bytes = new byte[16 * 1024];

                    in = new FileInputStream(file);

                    out = socket.getOutputStream();

                    outToMessage = new DataOutputStream(socket.getOutputStream());

                    outToMessage.writeChars(archivo.getNombre());

                    System.err.println("CLIENTE: " + file.length());

                    int count;

                    while ((count = in.read(bytes)) > 0) {
                        out.write(bytes, 0, count);
                    }
                }
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
        }
    }

    public static void cerrarConexiones() {

        try {
            out.close();
            in.close();
            socket.close();
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new Cliente();
    }

}
