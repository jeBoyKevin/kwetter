package com.kwetter.tweetservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource("classpath:application_config.xml")
@SpringBootApplication
public class TweetserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(TweetserviceApplication.class, args);
    }

}
