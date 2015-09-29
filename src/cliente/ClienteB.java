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
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author darcusfenix
 */
public class ClienteB {

    public static void main(String[] args) throws IOException {

        List<Archivo> archivos = null;
        archivos = new ArrayList<Archivo>();

        //archivos.add(new Archivo("a.html", "html", new Long(21523), false, 0, "/home/darcusfenix/Escritorio"));
        archivos.add(new Archivo("angular_sanitize.min.js", "js", new Long(4294), false, 0, "/home/darcusfenix/Escritorio"));
        archivos.add(new Archivo("data.sql", "sql", new Long(24867), false, 0, "/home/darcusfenix/Escritorio"));

        archivos.add(new Archivo("a.js", "js", new Long(4363), false, 0, "/home/darcusfenix/Escritorio"));

        String hostName = "localhost";
        int portNumber = 4444;
        String fromServer;
        String fromUser = null;

        try {
            Socket kkSocket = new Socket(hostName, portNumber);
            PrintWriter out = new PrintWriter(kkSocket.getOutputStream(), true);
//            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
            BufferedReader in = new BufferedReader(new InputStreamReader(kkSocket.getInputStream()));

            Integer enviados = -1;

            while ((fromServer = in.readLine()) != null) {
                System.out.println("Server: " + fromServer);

                if (fromServer.equals("0")) {
                    enviados++;
                    String archivoAENviar = archivos.get(enviados).getNombre() + "-" + archivos.get(enviados).getTipo() + "-" + archivos.get(enviados).getSize();
                    System.err.println("EL CLIENTE RECIBE PETICIÓN DE LISTO DEL SERVIDOR -------- " + archivoAENviar);

                    out.println(archivoAENviar);
                    out.flush();
                }

                if (fromServer.equals("1")) {
                    File file = new File(archivos.get(enviados).getRuta() + "/" + archivos.get(enviados).getNombre());

                    Long length = file.length();
                    byte[] bytes = new byte[16 * 1024];

                    InputStream inputStream = new FileInputStream(file);

                    OutputStream outputStream = kkSocket.getOutputStream();

                    int count;
                    long size = archivos.get(enviados).getSize();
                    while ((count = inputStream.read(bytes)) > 0) {
                        outputStream.write(bytes, 0, count);
                    }
                    outputStream.flush();
                    
                    System.err.println("EL CLIENTE FINALIZA ENVIO DE ARCHIVO");
                }
            }
        } catch (Exception e) {
            System.err.println("Couldn't get I/O for the connection to "
                    + hostName);
        }

    }
}
