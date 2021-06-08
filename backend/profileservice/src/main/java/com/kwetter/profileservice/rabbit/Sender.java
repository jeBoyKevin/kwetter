package com.kwetter.profileservice.rabbit;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kwetter.profileservice.models.rabbitModels.FollowerSubmitModel;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;


public class Sender {

    @Autowired
    private RabbitTemplate template;

    @Autowired
    private Queue queue;


    private ObjectMapper objectMapper = new ObjectMapper();

    @Scheduled(fixedDelay = 1000, initialDelay = 500)
    public void send(int user_id) throws JsonProcessingException, URISyntaxException, NoSuchAlgorithmException, KeyManagementException {
        FollowerSubmitModel submitModel = new FollowerSubmitModel();
        submitModel.setUser_id(user_id);

        ConnectionFactory factory = new ConnectionFactory();
        factory.setUri("amqps://faxuffrx:RDMd_S02-J7U60jOiIrrJzQWzaen2YKa@kangaroo.rmq.cloudamqp.com/faxuffrx");

        RabbitTemplate rabbitTemplate = new RabbitTemplate(new CachingConnectionFactory(factory));
        rabbitTemplate.convertAndSend("notification", objectMapper.writeValueAsString(submitModel));
        System.out.println(" [x] Sent new follow message");
    }

}