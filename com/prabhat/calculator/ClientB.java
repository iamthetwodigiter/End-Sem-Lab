package com.prabhat.calculator;

import java.net.Socket;
import java.io.*;

public class ClientB {
    static int PORT = 2004;
    static String ADDRESS = "localhost";

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket(ADDRESS, PORT);
        System.out.println("ClientB connected to server");

        DataInputStream din = new DataInputStream(socket.getInputStream());

        try {
            while (true) {
                double result = din.readDouble();
                char operator = din.readChar();
                double x = din.readDouble();
                double y = din.readDouble();
                
                System.out.println("\nReceived calculation: " + x + " " + operator + " " + y);
                System.out.println("Result: " + result);
            }
        } catch (EOFException e) {
            System.out.println("Server disconnected");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            din.close();
            socket.close();
            System.out.println("Connection closed");
        }
    }
}
