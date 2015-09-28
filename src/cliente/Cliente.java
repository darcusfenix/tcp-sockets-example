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
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import ui.JFrameClientConnection;

/**
 *
 * @author darcusfenix
 */
public class Cliente {

    private JFrameClientConnection frameConnection;
    
    private String ip;
    private Integer puerto;
    
    public Cliente() {
        //frameConnection = new JFrameClientConnection();
        //init();
    }

    public void init() {
        /*
         frameConnection.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
         frameConnection.setLocationRelativeTo(null);
         frameConnection.pack();
         frameConnection.setVisible(true);
         */
    }

    public void iniciarConexion(String ip, Integer puerto){
        this.ip = ip;
        this.puerto = puerto;
    }

    public Boolean enviarArchivos(Archivo archivo) throws IOException {

        
        try {
                Socket socket = new Socket(ip, puerto);
                DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
                DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
                
                dataOutputStream.writeInt(4);
                //while (flag) {                    
                dataOutputStream.writeUTF(archivo.getNombre());
                dataOutputStream.writeUTF(archivo.getTipo());
                dataOutputStream.writeLong(archivo.getSize());

                File file = new File(archivo.getRuta() + "/" + archivo.getNombre());

                Long length = file.length();
                byte[] bytes = new byte[16 * 1024];

                InputStream inputStream = new FileInputStream(file);

                OutputStream outputStream = socket.getOutputStream();

                int count;
                long size = archivo.getSize();
                while ((count = inputStream.read(bytes)) > 0) {
                    outputStream.write(bytes, 0, count);
                    size -= count;
                    System.err.println("QUEDA: " + size);
                }
                dataOutputStream.writeBoolean(true);
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
        return true;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        System.err.println("CLIENTE");
        Cliente cliente = new Cliente();
        
        List<Archivo> archivos = null;
        archivos = new ArrayList<Archivo>();

        archivos.add(new Archivo("data.sql", "sql", new Long(24867), false, 0, "/home/darcusfenix/Escritorio"));
        archivos.add(new Archivo("a.html", "html", new Long(21523), false, 0, "/home/darcusfenix/Escritorio"));
        archivos.add(new Archivo("a.js", "js", new Long(4363), false, 0, "/home/darcusfenix/Escritorio"));
        archivos.add(new Archivo("angular-sanitize.min.js", "js", new Long(4294), false, 0, "/home/darcusfenix/Escritorio"));
        
        cliente.iniciarConexion("192.168.1.84", 4444);
        
        
        for (Archivo archivo : archivos) {
            cliente.enviarArchivos(archivo);
        }
        
        
    }

}
