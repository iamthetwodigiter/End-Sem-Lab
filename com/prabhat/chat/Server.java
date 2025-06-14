package com.prabhat.chat;

import java.net.*;
import java.io.*;

public class Server {
    static int PORT = 1234;

    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(PORT);
        System.out.println("Serve waiting for connections on PORT " + PORT);

        Socket socket = server.accept();
        System.out.println("Client connected successfully");

        DataInputStream din = new DataInputStream(socket.getInputStream());
        DataOutputStream dout = new DataOutputStream(socket.getOutputStream());
        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));

        String send = "";
        String receive = "";
        while (true) {
            receive = din.readUTF();
            System.out.println("Client: " + receive);

            if (receive.equalsIgnoreCase("STOP")) {
                System.out.println("Client disconnected from the chat");
                break;
            }

            System.out.print("Server: ");
            send = buffer.readLine();
            dout.writeUTF(send);
            dout.flush();
        }
        din.close();
        dout.close();
        socket.close();
        server.close();
        System.out.println("Server connection closed successfully");
    }
}
