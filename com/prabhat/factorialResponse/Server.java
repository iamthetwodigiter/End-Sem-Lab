package com.prabhat.factorialResponse;

import java.net.*;
import java.io.*;

public class Server {
    static int PORT = 2222;

    private static double factorial(int n) {
        if (n <= 1) {
            return 1;
        }
        return n * factorial(n - 1);
    }

    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(PORT);
        System.out.println("Serve waiting for connections on PORT " + PORT);

        Socket socket = server.accept();
        System.out.println("Client connected successfully");

        DataInputStream din = new DataInputStream(socket.getInputStream());
        DataOutputStream dout = new DataOutputStream(socket.getOutputStream());

        try {

            while (true) {
                int receive = din.readInt();
                if (receive == -999) {
                    System.out.println("Client disconnected from the chat");
                    break;
                }

                double fact = factorial(receive);
                System.out.println(receive + " factorial is calculated and sent to the client");
                dout.writeDouble(fact);
                dout.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            din.close();
            dout.close();
            socket.close();
            server.close();
            System.out.println("Server connection closed successfully");
        }
    }
}
