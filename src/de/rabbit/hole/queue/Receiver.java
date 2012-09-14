package de.rabbit.hole.queue;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.QueueingConsumer;

public final class Receiver extends QueueUser {

	public Receiver(String queue, String host) {
		super(queue, host);
	}	
	
	public void receive() throws Exception {
		Connection connection = createConnection();
		Channel channel = connection.createChannel();
		channel.queueDeclare(getQueue(), false, false, false, null);
			
		QueueingConsumer consumer = new QueueingConsumer(channel);
		channel.basicConsume(getQueue(), true, consumer);
		
		while (true) {
			QueueingConsumer.Delivery delivery = consumer.nextDelivery();
			String message = new String(delivery.getBody());
			
			System.out.println(message);
		}
	}	
}
