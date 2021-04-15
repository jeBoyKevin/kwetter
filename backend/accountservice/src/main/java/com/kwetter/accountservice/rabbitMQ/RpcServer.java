package com.kwetter.accountservice.rabbitMQ;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

public class RpcServer {

    @RabbitListener(queues = "jwt.rpc.request")
    public int test(int n) {
        System.out.println("Received request with number " + n);

        int result = n * 2;

        return result;
    }
}
