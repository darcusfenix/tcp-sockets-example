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
    public static String nombreArchivo;
    private JFrameServerConnection frameConnection;
    public static ServerSocket serverSocket;
    public static Socket socket;
    public static InputStream in;
    public static OutputStream out;
    public static DataOutputStream outToMessage;
    public static Long size;

    public Servidor() {
        frameConnection = new JFrameServerConnection();
        init();
    }

    public void init() {
        frameConnection.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frameConnection.setLocationRelativeTo(null);
        frameConnection.pack();
        frameConnection.setVisible(true);
    }

    public static void iniciarConexion(Integer puerto, String ruta){
        rutaGuardado = ruta;
        try {
            serverSocket = new ServerSocket(4444);
        } catch (IOException ex) {
            System.out.println("Can't setup server on this port number. ");
        }
    }

    public static void aceptarCliente() {
        socket = null;

        try {
            socket = serverSocket.accept();
        } catch (IOException ex) {
            System.out.println("Can't accept client connection. ");
        }
        try {
            in = socket.getInputStream();
        } catch (IOException ex) {
            System.out.println("Can't get socket input stream. ");
        }
    }

    public static void recibirMensajes() {
        DataInputStream inFromClient = null;
        try {
            inFromClient = new DataInputStream(socket.getInputStream());
            nombreArchivo = inFromClient.readLine();
            System.err.println("SERVIDOR: " + nombreArchivo);
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void recibirArchivos() {

        try {
            out = new FileOutputStream(rutaGuardado + nombreArchivo);
        } catch (FileNotFoundException ex) {
            System.out.println("File not found. ");
        }

        byte[] bytes = new byte[16 * 1024];

        try {
            int count;
            while ((count = in.read(bytes)) > 0) {
                out.write(bytes, 0, count);
                size = size - count;
                System.err.println("QUEDA: " + size);
            }
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void cerrarConexiones() {

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