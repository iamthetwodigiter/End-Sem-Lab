package com.prabhat.powerCalculator;

import java.net.*;
import java.io.*;

public class Server {
    static int PORT_A = 9090;
    static int PORT_B = 9091;

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

        DataInputStream din = new DataInputStream(socketA.getInputStream());
        DataOutputStream dout = new DataOutputStream(socketB.getOutputStream());
        try {
            while (true) {
                double a = din.readDouble();
                String operator = din.readUTF();
                double b = din.readDouble();

                System.out.println("Received: " + a + " " + operator + " " + b);
                double result = calculate(a, b, operator);

                dout.writeDouble(a);
                dout.writeUTF(operator);
                dout.writeDouble(b);
                dout.writeDouble(result);
                dout.flush();

                System.out.println("Calculated result is sent to Client B");
            }
        } catch (EOFException e) {
            System.out.println("Client disconnected");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            din.close();
            dout.close();
            socketA.close();
            socketB.close();
            serverA.close();
            serverB.close();
            System.out.println("Server shut down successfully");
        }
    }
}
