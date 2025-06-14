package com.prabhat.socket;

import java.io.*;
import java.net.*;

public class Client {
    static int PORT = 9999;
    static String ADDRESS = "localhost";
    public static void main(String[] args) {
        try {
            Socket socket = new Socket(ADDRESS, PORT);

            DataOutputStream dout = new DataOutputStream(socket.getOutputStream());
            BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));

            System.out.print("Enter a message: ");
            String message = buffer.readLine();

            dout.writeUTF(message);
            System.out.println("Message forwarded to Server");

            dout.close();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
