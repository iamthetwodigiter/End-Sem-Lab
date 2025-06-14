package com.prabhat.factorial;

import java.net.*;
import java.io.*;

public class Server {
    static int PORT = 5555;

    private static double factorial(int n) {
        if (n < 1) {
            return 1;
        }
        return n * factorial(n - 1);
    }

    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(PORT);
            System.out.println("Server waiting for clients on port " + PORT);

            Socket socket = server.accept();
            System.out.println("Client connected successfully");

            DataInputStream din = new DataInputStream(socket.getInputStream());
            int n = din.readInt();
            double fact = factorial(n);

            System.out.println("Factorial of " + n + " is: " + fact);

            din.close();
            socket.close();
            server.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
