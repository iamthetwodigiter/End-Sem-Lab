package com.prabhat.powerCalculator;

import java.net.*;
import java.io.*;

public class ClientB {
    static int PORT = 9091;
    static String ADDRESS = "localhost";

    public static void main(String[] args) throws IOException, EOFException {
        Socket socket = new Socket(ADDRESS, PORT);
        System.out.println("Client B connected successfully");

        DataInputStream din = new DataInputStream(socket.getInputStream());
        try {
            while (true) {
                double a = din.readDouble();
                String operator = din.readUTF();
                double b = din.readDouble();
                double result = din.readDouble();
                System.out.println(a + " " + operator + " " + b + " = " + result);
            }
        } catch (EOFException e) {
            System.out.println("Client disconnected");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            din.close();
            socket.close();
        }
    }
}
