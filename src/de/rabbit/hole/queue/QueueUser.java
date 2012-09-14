package de.rabbit.hole.queue;

import java.io.IOException;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

abstract class QueueUser {

    private final String queue;

    private final String host;

    public QueueUser(String queue, String host) {
        this.host = host;
        this.queue = queue;
    }

    Connection createConnection() throws IOException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(host);
        return factory.newConnection();
    }

    String getQueue() {
        return queue;
    }
}
