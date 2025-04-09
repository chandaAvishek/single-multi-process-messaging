package com.assignment;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Player implements Runnable {
    private final String name;
    private final BlockingQueue<Message> inbox = new LinkedBlockingQueue<>();
    private Player otherPlayer;
    private int sentCounter = 0;
    private int receivedCounter = 0;

    public Player(String name) {
        this.name = name;
    }

    public void setOtherPlayer(Player otherPlayer) {
        this.otherPlayer = otherPlayer;
    }

    public void send(Message message) {
        if (message != null) {
            sentCounter++;
            System.out.printf("[%s] Sent: %s (Total Sent: %d)\n", 
                              name, message.getContent(), sentCounter);
            if (otherPlayer != null) {
                otherPlayer.receive(message);
            }
        } else {
            System.out.printf("[%s] Tried to send a null message\n", name);
        }
    }

    public void receive(Message message) {
        if (message != null) {
            inbox.add(message);
            receivedCounter++; // Increment received counter here
        } else {
            System.out.printf("[%s] Received a null message\n", name);
        }
    }

    @Override
    public void run() {
        try {
            while (receivedCounter < 10) {
                Message message = inbox.take();
                System.out.printf("[%s] Received: %s (Total Received: %d)\n", 
                                  name, message.getContent(), receivedCounter);

                if (sentCounter < 10) {
                    Message reply = new Message("Reply-" + receivedCounter);
                    send(reply);
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public int getReceivedCounter() {
        return receivedCounter;
    }

    public int getSentCounter() {
        return sentCounter;
    }
}
