/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author darcusfenix
 */
public class TCPServerFile {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;
        Long size;

        try {
            serverSocket = new ServerSocket(4444);
        } catch (IOException ex) {
            System.out.println("Can't setup server on this port number. ");
        }

        Socket socket = null;
        InputStream in = null;
        OutputStream out = null;

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

        //DataInputStream inFromClient = new DataInputStream(new InputStreamReader(socket.getInputStream()));
        DataInputStream inFromClient = new DataInputStream(socket.getInputStream());
        size = inFromClient.readLong();
        System.err.println("SERVIDOR: " + inFromClient.readLong());
        

        try {
            out = new FileOutputStream("/home/darcusfenix/Documentos/ESCOM/REDES-APPS/servidor/recibido.pdf");
        } catch (FileNotFoundException ex) {
            System.out.println("File not found. ");
        }

        byte[] bytes = new byte[16 * 1024];

        int count;
        while ((count = in.read(bytes)) > 0) {
            out.write(bytes, 0, count);
            size = size - count;
            System.err.println("QUEDA: " + size);
        }

        out.close();
        in.close();
        socket.close();
        serverSocket.close();
    }
}
