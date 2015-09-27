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
package servidor;

import bean.Archivo;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import ui.JFrameClientConnection;
import ui.JFrameServerConnection;

/**
 *
 * @author darcusfenix
 */
public class Servidor {

    public static String rutaGuardado;
    public static Integer numArchivos;
    private JFrameServerConnection frameConnection;
    public static ServerSocket serverSocket;
    public static Socket socket;

    public static DataOutputStream outToMessage;
    public static Long size;

    public Servidor() {
        frameConnection = new JFrameServerConnection();
        init();
    }

    public void init() {
        frameConnection.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameConnection.setLocationRelativeTo(null);
        frameConnection.pack();
        frameConnection.setVisible(true);
    }

    public static void iniciarConexion(Integer puerto, String ruta) {
        System.err.println("SE INICIA CONEXIÓN");
        rutaGuardado = ruta;
        try {
            serverSocket = new ServerSocket(4444);
        } catch (IOException ex) {
            System.out.println("Can't setup server on this port number. ");
        }

        socket = null;

        try {
            socket = serverSocket.accept();
        } catch (IOException ex) {
            System.out.println("Can't accept client connection. ");
        }

        DataInputStream inFromClient = null;
        try {
            inFromClient = new DataInputStream(socket.getInputStream());
            numArchivos = inFromClient.readInt();
            System.err.println(numArchivos);
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }

        OutputStream out = null;
        InputStream in = null;
        for (int i = 0; i < numArchivos; i++) {
            System.err.println("archivo: " + i);

            try {
                DataOutputStream outToClient = new DataOutputStream(socket.getOutputStream());
                in = socket.getInputStream();

                out = new FileOutputStream(rutaGuardado + "/archivo-" + numArchivos);

                byte[] bytes = new byte[16 * 1024];

                int count;
                while ((count = in.read(bytes)) > 0) {
                    out.write(bytes, 0, count);
                    /*
                     size = size - count;
                     System.err.println("QUEDA: " + size);
                     */
                }
                outToClient.writeBytes("1");
            } catch (IOException ex) {
                Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        

        try {
            out.close();
            in.close();
            socket.close();
            serverSocket.close();
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new Servidor();
    }
}
