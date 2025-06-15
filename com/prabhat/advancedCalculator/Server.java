package com.prabhat.advancedCalculator;

import java.net.*;
import java.io.*;

public class Server {
    static int PORT_A = 8888;
    static int PORT_B = 8889;

    private static double calculate(double a, double b, String operator) {
        switch (operator) {
            case "+":
                return a + b;
            case "-":
                return a - b;
            case "*":
                return a * b;
            case "/":
                return a / b;
            case "^":
                return Math.pow(a, b);
            default:
                return Double.POSITIVE_INFINITY;
        }
    }

    public static void main(String[] args) throws IOException, EOFException {
        System.out.println("Server booted up");

        System.out.println("Listening for Client A on port " + PORT_A);
        ServerSocket serverA = new ServerSocket(PORT_A);
        System.out.println("Listening for Client B on port " + PORT_B);
        ServerSocket serverB = new ServerSocket(PORT_B);

        Socket socketA = serverA.accept();
        System.out.println("Client A connected to the server successfully");

        Socket socketB = serverB.accept();
        System.out.println("Client B connected to the server successfully");

        DataInputStream dinA = new DataInputStream(socketA.getInputStream());
        DataInputStream dinB = new DataInputStream(socketB.getInputStream());
        DataOutputStream doutA = new DataOutputStream(socketA.getOutputStream());
        DataOutputStream doutB = new DataOutputStream(socketB.getOutputStream());
        try {
            while (true) {
                double a = dinA.readDouble();
                String operator = dinA.readUTF();
                double b = dinA.readDouble();

                System.out.println("Received: " + a + " " + operator + " " + b);
                double result = calculate(a, b, operator);

                doutA.writeDouble(result);
                doutB.writeDouble(result);
                doutA.flush();
                System.out.println("First calculated result is sent to both results");

                double c = dinB.readDouble();
                double secondResult = Math.pow(result, c);
                doutB.writeDouble(secondResult);
                System.out.println("Client B calculated result is sent to Client B");
            }
        } catch (EOFException e) {
            System.out.println("Client disconnected");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dinA.close();
            dinB.close();
            doutA.close();
            doutB.close();
            socketA.close();
            socketB.close();
            serverA.close();
            serverB.close();
            System.out.println("Server shut down successfully");
        }
    }
}
