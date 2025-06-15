package com.prabhat.advancedCalculator;

import java.net.*;
import java.io.*;

public class ClientA {
    static int PORT = 8888;
    static String ADDRESS = "localhost";

    private static String operator(int choice) {
        switch (choice) {
            case 1:
                return "+";
            case 2:
                return "-";
            case 3:
                return "*";
            case 4:
                return "/";
            case 5:
                return "^";
            default:
                return "+";
        }
    }

    public static void main(String[] args) throws IOException, EOFException {
        Socket socket = new Socket(ADDRESS, PORT);
        System.out.println("Client A connected successfully");

        DataInputStream din = new DataInputStream(socket.getInputStream());
        DataOutputStream dout = new DataOutputStream(socket.getOutputStream());
        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));

        try {
            while (true) {
                System.out.print(
                        "\n1. Addition\n2. Subtraction\n3. Multiplication\n4. Division\n5. Exponent a to the power b\n6. Exit\nEnter your choice: ");
                int choice = Integer.parseInt(buffer.readLine());

                if (choice == 6) {
                    System.out.println("Exiting");
                    break;
                }

                if (choice < 1 || choice > 6) {
                    System.out.println("Invalid choice try again");
                    continue;
                }

                System.out.print("Enter a: ");
                double a = Double.parseDouble(buffer.readLine());
                System.out.print("Enter b: ");
                double b = Double.parseDouble(buffer.readLine());

                dout.writeDouble(a);
                dout.writeUTF(operator(choice));
                dout.writeDouble(b);
                dout.flush();

                System.out.println("Sent: " + a + " " + operator(choice) + " " + b);

                double result = din.readDouble();
                System.out.println("Result: " + result);
            }
        } catch (EOFException e) {
            System.out.println("Client disconnected");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dout.close();
            buffer.close();
            socket.close();
            System.out.println("Client A connection closed");
        }
    }
}
