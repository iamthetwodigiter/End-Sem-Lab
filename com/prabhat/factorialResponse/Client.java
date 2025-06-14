package com.prabhat.factorialResponse;

import java.net.*;
import java.io.*;

public class Client {
    static int PORT = 2222;
    static String ADDRESS = "localhost";

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket(ADDRESS, PORT);
        System.out.println("Client connected successfully to the server with IP " + socket.getLocalAddress());

        DataInputStream din = new DataInputStream(socket.getInputStream());
        DataOutputStream dout = new DataOutputStream(socket.getOutputStream());
        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));

        String input = "";

        try {
            while (true) {
                System.out.print("Enter a number: ");
                input = buffer.readLine();

                if (input.equalsIgnoreCase("STOP")) {
                    dout.writeInt(-999);
                    dout.flush();
                    System.out.println("Client disconnected from the server");
                    break;
                }
                try {
                    int number = Integer.parseInt(input);
                    dout.writeInt(number);
                    dout.flush();
                    double result = din.readDouble();
                    System.out.println(number + "! = " + result);
                } catch (Exception e) {
                    System.out.println("Enter a valid");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            din.close();
            dout.close();
            socket.close();
            System.out.println("Socket connection closed successfully");
        }
    }
}
