package com.prabhat.calculator;

import java.net.*;
import java.io.*;

public class ClientA {
    static int PORT = 2003;
    static String ADDRESS = "localhost";

    private static char operator(int choice) {
        return choice == 1 ? '+' : choice == 2 ? '-' : choice == 3 ? '*' : '/';
    }

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket(ADDRESS, PORT);
        System.out.println("ClientA connected to server");

        DataOutputStream dout = new DataOutputStream(socket.getOutputStream());
        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));

        try {
            while (true) {
                System.out.println("\n1. Addition\n2. Subtraction\n3. Multiplication\n4. Division\n5. Exit");
                System.out.print("Enter your choice: ");

                int choice = Integer.parseInt(buffer.readLine());

                if (choice == 5) {
                    System.out.println("Exiting...");
                    break;
                }

                if (choice < 1 || choice > 4) {
                    System.out.println("Invalid choice! Please enter a number between 1 and 5.");
                    continue;
                }

                System.out.print("Enter operand x: ");
                double x = Double.parseDouble(buffer.readLine());

                System.out.print("Enter operand y: ");
                double y = Double.parseDouble(buffer.readLine());

                char op = operator(choice);
                dout.writeChar(op);
                dout.writeDouble(x);
                dout.writeDouble(y);
                dout.flush();

                System.out.println("Sent: " + x + " " + op + " " + y);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dout.close();
            socket.close();
            System.out.println("Connection closed");
        }
    }
}
