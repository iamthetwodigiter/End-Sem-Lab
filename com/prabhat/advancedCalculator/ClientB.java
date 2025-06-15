package com.prabhat.advancedCalculator;

import java.net.*;
import java.io.*;

public class ClientB {
    static int PORT = 8889;
    static String ADDRESS = "localhost";

    public static void main(String[] args) throws IOException, EOFException {
        Socket socket = new Socket(ADDRESS, PORT);
        System.out.println("Client B connected successfully");

        DataInputStream din = new DataInputStream(socket.getInputStream());
        DataOutputStream dout = new DataOutputStream(socket.getOutputStream());
        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));

        try {
            while (true) {
                double clientAResult = din.readDouble();
                System.out.println("Client A sent result: " + clientAResult);

                System.out.print("Enter exponent value: ");
                double exp = Double.parseDouble(buffer.readLine());
                dout.writeDouble(exp);

                double result = din.readDouble();
                System.out.println("Client B calculated result: " + result);
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
