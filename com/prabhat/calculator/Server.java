package com.prabhat.calculator;

import java.net.*;
import java.io.*;

public class Server {
    static int PORT_A = 2003;
    static int PORT_B = 2004;

    private static double calculate(double x, double y, char operator) {
        switch (operator) {
            case '+':
                return x + y;
            case '-':
                return x - y;
            case '*':
                return x * y;
            case '/':
                return x / y;
            default:
                return Double.POSITIVE_INFINITY;
        }
    }

    public static void main(String[] args) throws IOException {
        System.out.println("Server starting...");

        ServerSocket serverA = new ServerSocket(PORT_A);
        ServerSocket serverB = new ServerSocket(PORT_B);

        System.out.println("Waiting for ClientA on port " + PORT_A);
        Socket socketA = serverA.accept();
        System.out.println("ClientA connected successfully");

        System.out.println("Waiting for ClientB on port " + PORT_B);
        Socket socketB = serverB.accept();
        System.out.println("ClientB connected successfully");

        DataInputStream dinA = new DataInputStream(socketA.getInputStream());
        DataOutputStream doutB = new DataOutputStream(socketB.getOutputStream());

        try {
            while (true) {

                char operator = dinA.readChar();
                double x = dinA.readDouble();
                double y = dinA.readDouble();

                System.out.println("Received: " + x + " " + operator + " " + y);

                double result = calculate(x, y, operator);
                System.out.println("Result: " + result);

                doutB.writeDouble(result);
                doutB.writeChar(operator);
                doutB.writeDouble(x);
                doutB.writeDouble(y);
                doutB.flush();
            }
        } catch (EOFException e) {
            System.out.println("Client disconnected");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            dinA.close();
            doutB.close();
            socketA.close();
            socketB.close();
            serverA.close();
            serverB.close();
            System.out.println("Server shut down");
        }
    }
}
