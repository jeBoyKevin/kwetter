package com.kwetter.accountservice.rabbitMQ;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kwetter.accountservice.model.Account;
import com.kwetter.accountservice.service.UserService;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CountDownLatch;

@Component
public class Recv {

    private CountDownLatch latch = new CountDownLatch(1);
    private ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    private UserService userService;

    public void receiveMessage(String message) {
        receiveObject obj = null;
        try {
            obj = objectMapper.readValue(message, receiveObject.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        latch.countDown();
        System.out.println("Received " + obj);
        List<Account> accounts = userService.getUsernames(obj.getToken(), obj.getUserids());
        System.out.println(accounts);
    }

    public CountDownLatch getLatch() {
        return latch;
    }
}