package com.kwetter.accountservice.rabbitMQ;

import java.util.concurrent.TimeUnit;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kwetter.accountservice.JwtAuthServiceApp;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Runner implements CommandLineRunner {

    private final RabbitTemplate rabbitTemplate;
    private final Recv receiver;
    private ObjectMapper objectMapper = new ObjectMapper();

    public Runner(Recv receiver, RabbitTemplate rabbitTemplate) {
        this.receiver = receiver;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Sending message...");
        receiveObject object = new receiveObject();
        object.addUserids(1);
        object.addUserids(4);
        object.setToken("");
        rabbitTemplate.convertAndSend(JwtAuthServiceApp.topicExchangeName, "foo.bar.baz", objectMapper.writeValueAsString(object));
        receiver.getLatch().await(10000, TimeUnit.MILLISECONDS);
    }

}