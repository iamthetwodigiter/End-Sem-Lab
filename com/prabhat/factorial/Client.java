package com.prabhat.factorial;

import java.io.*;
import java.net.*;

public class Client {
    static int PORT = 5555;
    static String ADDRESS = "localhost";

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket(ADDRESS, PORT);
        System.out.println("Client connected to the server successfully");

        DataOutputStream dout = new DataOutputStream(socket.getOutputStream());
        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("Enter n: ");
        dout.writeInt(Integer.parseInt(buffer.readLine()));

        System.out.println("Request sent to server successfully");
        socket.close();
    }
}
