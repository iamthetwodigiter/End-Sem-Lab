package com.prabhat.socket;

import java.io.*;
import java.net.*;

public class Server {
    static int PORT = 9999;

    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(PORT);
            System.out.println("Server waiting for clients on PORT 9999");

            Socket client = server.accept();
            System.out.println("Client connected");

            DataInputStream dis = new DataInputStream(client.getInputStream());
            String message = dis.readUTF();

            System.out.println("Message received from client: " + message);
            dis.close();
            
            client.close();
            server.close();
            System.out.println("Closed socket connection");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
