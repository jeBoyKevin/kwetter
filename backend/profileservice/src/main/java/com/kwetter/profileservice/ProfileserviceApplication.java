package com.kwetter.profileservice;

import org.apache.catalina.filters.CorsFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

@Configuration
@ImportResource("classpath:application_config.xml")
@SpringBootApplication
public class ProfileserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProfileserviceApplication.class, args);
    }


}
