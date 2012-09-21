package de.rabbit.hole.main;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import de.rabbit.hole.queue.Receiver;
import de.rabbit.hole.queue.Sender;

public final class RabbitHole {

    private static final String HOST = "localhost";

    private static final String QUEUE_NAME = "hole_q";

    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            printUsage();
            System.exit(1);
        }

        String modus = args[0];

        if ("sender".equals(modus)) {
            System.out.println("Enter Message: ");
            Sender sender = new Sender(QUEUE_NAME, HOST);

            while (true) {
              String message = readMessage();
              sender.send(message);
            }

        } else if ("receiver".equals(modus)) {
            System.out.println("Waiting for messages");
            new Receiver(QUEUE_NAME, HOST).receive();

        } else {
            printUsage();
        }
    }

    private static String readMessage() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        return br.readLine();
    }

    private static void printUsage() {
        System.out.println("Usage: RabbitHole [sender | receiver]");
    }
}
