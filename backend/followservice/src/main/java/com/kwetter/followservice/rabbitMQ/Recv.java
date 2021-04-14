package com.kwetter.followservice.rabbitMQ;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kwetter.followservice.rabbitMQ.models.RabbitReturnModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CountDownLatch;

@Component
public class Recv {

    private CountDownLatch latch = new CountDownLatch(1);
    private ObjectMapper objectMapper = new ObjectMapper();
    public RabbitReturnModel obj = null;

    public void receiveMessage(String message) {
        try {
            obj = objectMapper.readValue(message, RabbitReturnModel.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        latch.countDown();
        System.out.println("Received " + obj);
    }

    public CountDownLatch getLatch() {
        return latch;
    }
}