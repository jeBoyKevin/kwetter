package com.kwetter.profileservice.rabbit;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class Config {
    @Bean
    public Queue hello() {
        return new Queue("notification", true, false, false);
    }

    @Bean
    public Sender sender() {
        return new Sender();
    }
}
