package com.prabhat.grade;

import java.net.*;
import java.io.*;

public class Server {
    static int PORT = 1234;

    private static String calculateGrade(String[] marks) {
        int sum = 0;
        double avg = 0;
        for (int i = 0; i < marks.length; i++) {
            sum += Integer.parseInt(marks[i]);
        }
        avg = sum / (marks.length);

        if (avg >= 90) {
            return "O";
        } else if (avg >= 80 && avg < 90) {
            return "E";
        } else if (avg >= 70 && avg < 80) {
            return "A";
        } else if (avg >= 60 && avg < 70) {
            return "B";
        } else if (avg >= 50 && avg < 60) {
            return "C";
        } else if (avg >= 40 && avg < 50) {
            return "D";
        } else {
            return "Fail";
        }
    }

    public static void main(String[] args) throws IOException, EOFException {
        ServerSocket server = new ServerSocket(PORT);
        System.out.println("Serve waiting for connections on PORT " + PORT);

        Socket socket = server.accept();
        System.out.println("Client connected successfully");

        DataInputStream din = new DataInputStream(socket.getInputStream());
        DataOutputStream dout = new DataOutputStream(socket.getOutputStream());

        String receive = "";

        try {
            while (true) {
                receive = din.readUTF();
                System.out.println("Received: " + receive);

                if (receive.equalsIgnoreCase("STOP")) {
                    System.out.println("Client disconnected from the chat");
                    break;
                }

                String[] marks = receive.split(",");
                String grade = calculateGrade(marks);
                System.out.println("Student's grade is calculated and response is sent back to the student");
                dout.writeUTF(grade);
                dout.flush();
            }
        } catch (EOFException e) {
            System.out.println("Client disconnected unexpectedly");
        } finally {
            din.close();
            dout.close();
            socket.close();
            server.close();
            System.out.println("Server connection closed successfully");
        }
    }
}
