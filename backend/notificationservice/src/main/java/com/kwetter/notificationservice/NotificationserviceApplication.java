package com.kwetter.notificationservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kwetter.notificationservice.manager.Manager;
import com.kwetter.notificationservice.models.rabbitModels.FollowerModel;
import com.kwetter.notificationservice.rabbit.Receiver;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeoutException;


@Configuration
@ImportResource("classpath:application_config.xml")
@SpringBootApplication
public class NotificationserviceApplication {


	public static void main(String[] args) {
		SpringApplication.run(NotificationserviceApplication.class, args);
		ObjectMapper objectMapper = new ObjectMapper();

		Manager manager = new Manager();
		CachingConnectionFactory connectionFactory=new CachingConnectionFactory("kangaroo.rmq.cloudamqp.com");
		connectionFactory.setUsername("faxuffrx");
		connectionFactory.setPassword("RDMd_S02-J7U60jOiIrrJzQWzaen2YKa");
		connectionFactory.setVirtualHost("faxuffrx");

		SimpleMessageListenerContainer container =
				new SimpleMessageListenerContainer(connectionFactory);
		Object listener = new Object() {
			public void handleMessage(String foo) throws JsonProcessingException {
				FollowerModel followerModel = objectMapper.readValue(foo, FollowerModel.class);
				System.out.println(" [x] Received '" + followerModel.getMessage() + "' for user_id =" + followerModel.getUser_id());

				if (followerModel.getUser_id() == 0 || followerModel.getMessage() == null) {
					System.out.println("Bad request, failed to add notification to database");
				}
				manager.addNotification(followerModel.getMessage(), followerModel.getUser_id());
			}
		};

		//Send a message
		MessageListenerAdapter adapter = new MessageListenerAdapter(listener);
		container.setMessageListener(adapter);
		container.setQueueNames("notification");
		container.start();

	}

}
