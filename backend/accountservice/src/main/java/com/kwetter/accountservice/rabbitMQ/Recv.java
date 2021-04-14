package com.kwetter.accountservice.rabbitMQ;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kwetter.accountservice.model.Account;
import com.kwetter.accountservice.service.UserService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@Component
public class Recv {

    private CountDownLatch latch = new CountDownLatch(1);
    private ObjectMapper objectMapper = new ObjectMapper();
    private final RabbitTemplate rabbitTemplate;

    public Recv(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Autowired
    private UserService userService;

    public void receiveMessage(String message) {
        ReceiveObject obj = null;
        try {
            obj = objectMapper.readValue(message, ReceiveObject.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return;
        }
        latch.countDown();
        System.out.println("Received " + obj);
        List<Account> accounts = userService.getUsernames(obj.getUserids());
        SendObject sendObject = new SendObject();
        sendObject.setAccounts(accounts);
        System.out.println(accounts);
        try {
            rabbitTemplate.convertAndSend(obj.getQueue_name(), "foo.bar.baz",
                    objectMapper.writeValueAsString(sendObject));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        try {
            this.getLatch().await(10000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public CountDownLatch getLatch() {
        return latch;
    }


}