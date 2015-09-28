/*
 * The MIT License
 *
 * Copyright 2015 darcusfenix.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software withoutputStream restriction, including withoutputStream limitation the rights
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
import java.util.ArrayList;
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

    private JFrameServerConnection frameConnection;

    private OutputStream outputStream;
    private InputStream inputStream;

    private DataOutputStream dataOutputStream;
    private DataInputStream dataInputStream;

    private ServerSocket serverSocket;
    private Socket socket;

    private Integer numberFiles;
    private boolean flag;
    private ArrayList<String> infos;

    public Servidor() {
        //frameConnection = new JFrameServerConnection();
        //init();
    }

    public void init() {
        frameConnection.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frameConnection.setLocationRelativeTo(null);
        frameConnection.pack();
        frameConnection.setVisible(true);
    }

    public void iniciarConexion(Integer puerto, String ruta) {
        infos = new ArrayList<String>();

        this.outputStream = null;
        this.inputStream = null;

        this.dataOutputStream = null;
        this.dataInputStream = null;

        this.socket = null;
        this.numberFiles = null;
        this.flag = true;

        try {
            this.serverSocket = new ServerSocket(4444);
            this.socket = this.serverSocket.accept();

            this.inputStream = socket.getInputStream();

            dataInputStream = new DataInputStream(socket.getInputStream());
            Integer num = dataInputStream.readInt();
            System.err.println(num);

            /*
             this.dataOutputStream = new DataOutputStream(this.socket.getOutputStream());
             this.dataOutputStream.flush();
             */
            try {

                dataInputStream = new DataInputStream(socket.getInputStream());
                String fileName = dataInputStream.readUTF();
                String fileTipo = dataInputStream.readUTF();
                long size = dataInputStream.readLong();
                System.err.println(size);

                FileOutputStream fileOut = new FileOutputStream("/home/darcusfenix/NetBeansProjects/recibir/" + fileName);

                byte[] bytes = new byte[16 * 1024];
                int count;
                while ((count = this.inputStream.read(bytes)) > 0) {
                    fileOut.write(bytes, 0, count);
                    size -= count;
                    System.err.println("QUEDA: " + size);
                }

                boolean abc = dataInputStream.readBoolean();
                System.err.println(abc);
            } catch (FileNotFoundException ex) {
                System.err.println(ex.getMessage());
            }

        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        System.err.println("SERVIDOR");
        Servidor servidor = new Servidor();
        servidor.iniciarConexion(4444, "/home/darcusfenix/NetBeansProjects/recibir");
    }
}
