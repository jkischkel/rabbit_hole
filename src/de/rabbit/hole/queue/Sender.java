package de.rabbit.hole.queue;

import java.io.IOException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

public final class Sender extends QueueUser {
	
	public Sender(String queue, String host) {
		super(queue, host);
	}
	
	public void send(String message) throws IOException {
		Connection connection = createConnection();
		Channel channel = connection.createChannel();
		
		try {
			channel.basicPublish("", getQueue(), null, message.getBytes());
		} finally {
			channel.close();
			connection.close();
		}
	}
	
}

