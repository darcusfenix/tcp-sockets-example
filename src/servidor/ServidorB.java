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
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;
import test.KnockKnockProtocol;

/**
 *
 * @author darcusfenix
 */
public class ServidorB {

    public static void main(String[] args) throws IOException {

        int portNumber = 4444;
        Vector<Integer> estados = new Vector<Integer>();
        estados.add(0);
        estados.add(1);
        estados.add(2);
        estados.add(3);
        estados.add(4);
        estados.add(5);

        Integer estadoActual;

        try (
                ServerSocket serverSocket = new ServerSocket(portNumber);
                Socket clientSocket = serverSocket.accept();
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                DataInputStream dataInputStream = new DataInputStream(clientSocket.getInputStream());
                InputStream inputStream = clientSocket.getInputStream();
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));) {

            String inputLine, outputLine;

            // está listo el servidor para recibir info de cliente
            estadoActual = estados.get(0);

            Integer numeroArchivosRecibidos = 0;

            List<Archivo> archivos = null;
            archivos = new ArrayList<Archivo>();

            out.println(estadoActual);
            System.err.println("el servidor está listo");

            while ((inputLine = in.readLine()) != null) {
                //outputLine = kkp.processInput(inputLine);

                if (estadoActual == 0) {
                    System.err.println("DATOS DEL ARCHIVO: " + inputLine);
                    estadoActual = estados.get(1);
                    List<String> items = Arrays.asList(inputLine.split("\\s*-\\s*"));

                    archivos.add(new Archivo(items.get(0), items.get(1), Long.parseLong(items.get(2)), false, null, null));
                    System.err.print(archivos.get(numeroArchivosRecibidos));
                    //se recibe la información de un archivo y se pasa el estado a obtener archivo
                    out.println(estadoActual);
                    out.flush();
                }
                if (estadoActual == 1) {
                    
                    long size = archivos.get(numeroArchivosRecibidos).getSize();

                    FileOutputStream fileOut = new FileOutputStream("/home/darcusfenix/NetBeansProjects/recibir/" + archivos.get(numeroArchivosRecibidos).getNombre());

                    byte[] bytes = new byte[16 * 1024];
                    int count;
                    System.err.println("////");
                    
                    while ((count = inputStream.read(bytes)) > 0) {
                        fileOut.write(bytes, 0, count);
                        size -= count;
                        System.err.println("QUEDA: " + size);
                    }
                    
                    
                    
                    System.err.println("********");

                    System.err.print("EL SERVIDOR FINALIZA GUARDADO DE ARCHIVO");
                    
                    
                    //se terminó de recibir y crear el archivo dado por el cliente
                    numeroArchivosRecibidos++;
                    estadoActual = 0;
                    out.println(estadoActual);
                    out.flush();
                }
            }

        } catch (IOException e) {
            System.out.println("Exception caught when trying to listen on port "
                    + portNumber + " or listening for a connection");
            System.out.println(e.getMessage());
        }
    }
}
