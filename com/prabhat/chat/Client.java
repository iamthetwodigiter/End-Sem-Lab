package com.prabhat.chat;

import java.net.*;
import java.io.*;

public class Client {
    static int PORT = 1234;
    static String ADDRESS = "localhost";

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket(ADDRESS, PORT);
        System.out.println("Client connected successfully to the server with IP " + socket.getLocalAddress());

        DataInputStream din = new DataInputStream(socket.getInputStream());
        DataOutputStream dout = new DataOutputStream(socket.getOutputStream());
        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));

        String send = "";
        String receive = "";

        while (true) {
            System.out.print("Client: ");
            send = buffer.readLine();
            dout.writeUTF(send);
            dout.flush();

            if (receive.equalsIgnoreCase("STOP")) {
                System.out.println("Client disconnected from the chat");
                break;
            }

            receive = din.readUTF();
            System.out.println("Server: " + receive);
        }
        din.close();
        dout.close();
        socket.close();
        System.out.println("Socket connection closed successfully");
    }
}
