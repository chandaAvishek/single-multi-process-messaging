package com.assignment;

import java.io.*;
import java.net.*;

public class MultiProcessApp {
    public static void main(String[] args) throws IOException {
        String role = args[0];
        Player player = new Player(role);

        if (role.equals("initiator")) {
            initiateCommunication(player);
        } else {
            respondToCommunication(player);
        }
    }

    private static void initiateCommunication(Player player) throws IOException {
        try (Socket socket = new Socket("localhost", 8080);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            for (int i = 0; i < 10; i++) {
                Message msg = new Message("Hello " + (i + 1));
                player.send(msg); // Simulate sending locally
                out.println(msg.getContent()); // Send message content via socket

                String replyContent = in.readLine();
                if (replyContent != null) {
                    Message reply = new Message(replyContent);
                    player.receive(reply); // Simulate receiving locally
                } else {
                    System.out.println("[Initiator] Received null reply from Responder");
                }
            }
        }
    }

    private static void respondToCommunication(Player player) throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(8080);
             Socket socket = serverSocket.accept();
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            String input;
            while ((input = in.readLine()) != null) {
                // Receive message
                player.receive(new Message(input));

                // Create and send reply
                Message reply = new Message("Reply " + player.getReceivedCounter());
                player.send(null); // Simulate sending locally
                out.println(reply.getContent());

                if (player.getReceivedCounter() >= 10) break; // Stop condition
            }
        }
    }
}
