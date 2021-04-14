package com.kwetter.followservice.communication.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.kwetter.followservice.manager.FollowManager;
import com.kwetter.followservice.models.returnModels.GetFollowedReturnModel;
import com.kwetter.followservice.models.returnModels.GetFollowersReturnModel;
import com.kwetter.followservice.models.returnModels.GetStatsReturnModel;
import com.kwetter.followservice.models.returnModels.SendFollowReturnModel;
import com.kwetter.followservice.models.submitModels.SendFollowSubmitModel;

import com.kwetter.followservice.rabbitMQ.Recv;
import com.kwetter.followservice.rabbitMQ.models.RabbitSubmitModel;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

@RestController
public class FollowRestService {

    @Autowired
    private FollowManager manager = new FollowManager();

    private ObjectMapper objectMapper = new ObjectMapper();
    private final RabbitTemplate rabbitTemplate;
    private final Recv receiver;

    public FollowRestService(Recv receiver, RabbitTemplate rabbitTemplate) {
        this.receiver = receiver;
        this.rabbitTemplate = rabbitTemplate;
    }

    @RequestMapping(value =  "",
                    method = RequestMethod.POST,
                    consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity followUser(@RequestBody String json) throws JsonProcessingException {
        SendFollowSubmitModel submitModel = objectMapper.readValue(json, SendFollowSubmitModel.class);


        int user_id = submitModel.getUser_id();
        int followed_user_id = submitModel.getFollowed_user_id();

        if (user_id == 0 || followed_user_id == 0) {
          return new ResponseEntity<>("", HttpStatus.BAD_REQUEST);
        }

        SendFollowReturnModel returnModel = manager.followUser(user_id, followed_user_id);

        if (returnModel.isSuccess()) {
            return ResponseEntity.status(HttpStatus.OK).body(objectMapper.writeValueAsString(returnModel));
        }
        else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectMapper.writeValueAsString(returnModel));
        }
    }

    @RequestMapping(value =  "/follower/{user_id}", method = RequestMethod.GET)
    public ResponseEntity getFollowers(@PathVariable("user_id") int user_id) throws JsonProcessingException {
        if (user_id == 0) {
            return new ResponseEntity<>("", HttpStatus.BAD_REQUEST);
        }

        GetFollowersReturnModel returnModel = manager.getFollowers(user_id);

        if (returnModel.isSuccess()) {
            return ResponseEntity.status(HttpStatus.OK).body(objectMapper.writeValueAsString(returnModel));
        }
        else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectMapper.writeValueAsString(returnModel));
        }
    }

    @RequestMapping(value =  "/followed/{user_id}", method = RequestMethod.GET)
    public ResponseEntity getFollowed(@PathVariable("user_id") int user_id) throws JsonProcessingException {
        if (user_id == 0) {
            return new ResponseEntity<>("", HttpStatus.BAD_REQUEST);
        }

        GetFollowedReturnModel returnModel = manager.getFollowed(user_id);

        RabbitSubmitModel submitModel = new RabbitSubmitModel();
        submitModel.setUserids(returnModel.getFollowed());
        submitModel.setQueue_name("follow-service-exchange");
        rabbitTemplate.convertAndSend("account-service-exchange", "foo.bar.baz",
                objectMapper.writeValueAsString(submitModel));


        if (returnModel.isSuccess()) {
            return ResponseEntity.status(HttpStatus.OK).body(objectMapper.writeValueAsString(returnModel));
        }
        else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectMapper.writeValueAsString(returnModel));
        }
    }

    @RequestMapping(value =  "/stats/{user_id}", method = RequestMethod.GET)
    public ResponseEntity getStats(@PathVariable("user_id") int user_id) throws JsonProcessingException {
        if (user_id == 0) {
            return new ResponseEntity<>("", HttpStatus.BAD_REQUEST);
        }

        GetStatsReturnModel returnModel = manager.getStats(user_id);

        if (returnModel.isSuccess()) {
            return ResponseEntity.status(HttpStatus.OK).body(objectMapper.writeValueAsString(returnModel));
        }
        else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectMapper.writeValueAsString(returnModel));
        }
    }


}
