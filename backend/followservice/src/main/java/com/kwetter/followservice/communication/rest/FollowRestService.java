package com.kwetter.followservice.communication.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.kwetter.followservice.manager.FollowManager;
import com.kwetter.followservice.models.returnModels.GetFollowedReturnModel;
import com.kwetter.followservice.models.returnModels.GetFollowersReturnModel;
import com.kwetter.followservice.models.returnModels.SendFollowReturnModel;
import com.kwetter.followservice.models.submitModels.GetFollowedSubmitModel;
import com.kwetter.followservice.models.submitModels.SendFollowSubmitModel;

import com.kwetter.followservice.models.submitModels.GetFollowersSubmitModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FollowRestService {

    @Autowired
    private FollowManager manager = new FollowManager();

    private ObjectMapper objectMapper = new ObjectMapper();

    @RequestMapping(value =  "/follow",
                    method = RequestMethod.POST,
                    consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity followUser(@RequestBody String json) throws JsonProcessingException {
        SendFollowSubmitModel submitModel = objectMapper.readValue(json, SendFollowSubmitModel.class);


        int user_id = submitModel.getUser_id();
        int followed_user_id = submitModel.getFollowed_user_id();

        if (user_id == 0 || followed_user_id == 0) {
          return new ResponseEntity<>("", HttpStatus.BAD_REQUEST);
        }

        SendFollowReturnModel returnModel = manager.followUser(submitModel.getUser_id(), submitModel.getFollowed_user_id());

        if (returnModel.isSuccess()) {
            return ResponseEntity.status(HttpStatus.OK).body(objectMapper.writeValueAsString(returnModel));
        }
        else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectMapper.writeValueAsString(returnModel));
        }
    }

    @RequestMapping(value =  "/follower",
            method = RequestMethod.GET,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getFollowers(@RequestBody String json) throws JsonProcessingException {
        GetFollowersSubmitModel submitModel = objectMapper.readValue(json, GetFollowersSubmitModel.class);


        int user_id = submitModel.getUser_id();

        if (user_id == 0) {
            return new ResponseEntity<>("", HttpStatus.BAD_REQUEST);
        }

        GetFollowersReturnModel returnModel = manager.getFollowers(submitModel.getUser_id());

        if (returnModel.isSuccess()) {
            return ResponseEntity.status(HttpStatus.OK).body(objectMapper.writeValueAsString(returnModel));
        }
        else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectMapper.writeValueAsString(returnModel));
        }
    }

    @RequestMapping(value =  "/followed",
            method = RequestMethod.GET,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getFollowed(@RequestBody String json) throws JsonProcessingException {
        GetFollowedSubmitModel submitModel = objectMapper.readValue(json, GetFollowedSubmitModel.class);


        int user_id = submitModel.getUser_id();

        if (user_id == 0) {
            return new ResponseEntity<>("", HttpStatus.BAD_REQUEST);
        }

        GetFollowedReturnModel returnModel = manager.getFollowed(submitModel.getUser_id());

        if (returnModel.isSuccess()) {
            return ResponseEntity.status(HttpStatus.OK).body(objectMapper.writeValueAsString(returnModel));
        }
        else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectMapper.writeValueAsString(returnModel));
        }
    }

}
