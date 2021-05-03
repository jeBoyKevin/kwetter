package com.kwetter.notificationservice.rabbit;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kwetter.notificationservice.manager.Manager;
import com.kwetter.notificationservice.models.rabbitModels.FollowerModel;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;

@RabbitListener(queues = "notification")
public class Receiver {

    private ObjectMapper objectMapper = new ObjectMapper();

    private Manager manager = new Manager();


    @RabbitHandler
    public void receive(String in) throws JsonProcessingException {
        FollowerModel followerModel = objectMapper.readValue(in, FollowerModel.class);
        System.out.println(" [x] Received '" + followerModel.getMessage() + "' for user_id =" + followerModel.getUser_id());

        if (followerModel.getUser_id() == 0 || followerModel.getMessage() == null) {
            System.out.println("Bad request, failed to add notification to database");
        }
        manager.addNotification(followerModel.getMessage(), followerModel.getUser_id());
    }


}
