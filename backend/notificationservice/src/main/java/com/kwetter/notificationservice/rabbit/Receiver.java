package com.kwetter.notificationservice.rabbit;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kwetter.notificationservice.manager.Manager;
import com.kwetter.notificationservice.models.rabbitModels.FollowerModel;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

@RabbitListener(queues = "notification")
public class Receiver {

    private ObjectMapper objectMapper = new ObjectMapper();

    private Manager manager = new Manager();

    @RabbitHandler
    public void receive(String in) throws JsonProcessingException {

    }


}
