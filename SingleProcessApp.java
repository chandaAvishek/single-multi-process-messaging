package com.assignment;

public class SingleProcessApp {
    public static void main(String[] args) {
        Player initiator = new Player("Initiator");
        Player responder = new Player("Responder");
        
        initiator.setOtherPlayer(responder);
        responder.setOtherPlayer(initiator);

        Thread initiatorThread = new Thread(initiator);
        Thread responderThread = new Thread(responder);
        initiatorThread.start();
        responderThread.start();

        
        initiator.send(new Message("Hello-0"));

        try {
            initiatorThread.join();
            responderThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        System.out.println("Program terminated gracefully.");
    }
}
