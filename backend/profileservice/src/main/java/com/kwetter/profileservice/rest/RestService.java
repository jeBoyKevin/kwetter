package com.kwetter.profileservice.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kwetter.profileservice.manager.Manager;
import com.kwetter.profileservice.models.returnModels.*;
import com.kwetter.profileservice.models.submitModels.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.PathParam;

@RestController
public class RestService {

    @Autowired
    private Manager manager = new Manager();

    private ObjectMapper objectMapper = new ObjectMapper();

    @RequestMapping(value =  "/picture",
                    method = RequestMethod.POST,
                    consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity uploadPicture(@RequestBody String json) throws JsonProcessingException {
        UploadPictureSubmitModel submitModel = objectMapper.readValue(json, UploadPictureSubmitModel.class);

        int user_id = submitModel.getUser_id();
        String picture = submitModel.getPicture();

        if (user_id == 0 || picture.isEmpty()) {
            return new ResponseEntity<>("", HttpStatus.BAD_REQUEST);
        }

        UploadPictureReturnModel returnModel = manager.uploadPicture(user_id, picture);
        if (returnModel.isSuccess()) {
            return ResponseEntity.status(HttpStatus.OK).body(objectMapper.writeValueAsString(returnModel));
        }
        else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectMapper.writeValueAsString(returnModel));
        }

    }

    @RequestMapping(value = "",
                    method = RequestMethod.POST,
                    consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createProfile(@RequestBody String json) throws JsonProcessingException {
        CreateProfileSubmitModel submitModel = objectMapper.readValue(json, CreateProfileSubmitModel.class);

        String username = submitModel.getUsername();

        if (username.isEmpty()) {
            return new ResponseEntity<>("", HttpStatus.BAD_REQUEST);
        }

        UploadPictureReturnModel returnModel = manager.createProfile(username);
        if (returnModel.isSuccess()) {
            return ResponseEntity.status(HttpStatus.OK).body(objectMapper.writeValueAsString(returnModel));
        }
        else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectMapper.writeValueAsString(returnModel));
        }
    }

    @RequestMapping(value =  "",
                    method = RequestMethod.PUT,
                    consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateProfile(@RequestBody String json) throws JsonProcessingException {
        UpdateProfileSubmitModel submitModel = objectMapper.readValue(json, UpdateProfileSubmitModel.class);

        int user_id = submitModel.getUser_id();
        String bio = submitModel.getBio();
        String location = submitModel.getLocation();
        String website = submitModel.getWebsite();



        UpdateProfileReturnModel returnModel = manager.updateProfile(user_id, bio, location, website);

        if (returnModel.isSuccess()) {
            return ResponseEntity.status(HttpStatus.OK).body(objectMapper.writeValueAsString(returnModel));
        }
        else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectMapper.writeValueAsString(returnModel));
        }
    }

    @RequestMapping(value =  "/{profile_name}", method = RequestMethod.GET)
    public ResponseEntity getProfile(@PathVariable("profile_name") String profile_name) throws JsonProcessingException {

        if (profile_name.isEmpty()) {
            return new ResponseEntity<>("", HttpStatus.BAD_REQUEST);
        }

        GetProfileReturnModel returnModel = manager.getProfile(profile_name);
        if (returnModel.isSuccess()) {
            return ResponseEntity.status(HttpStatus.OK).body(objectMapper.writeValueAsString(returnModel));
        }
        else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectMapper.writeValueAsString(returnModel));
        }
    }

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

        SendFollowReturnModel returnModel = manager.followUser(user_id, followed_user_id);

        if (returnModel.isSuccess()) {
            return ResponseEntity.status(HttpStatus.OK).body(objectMapper.writeValueAsString(returnModel));
        }
        else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectMapper.writeValueAsString(returnModel));
        }
    }

    @RequestMapping(value =  "/follower/{profile_name}", method = RequestMethod.GET)
    public ResponseEntity getFollowers(@PathVariable("profile_name") String profile_name) throws JsonProcessingException {
        if (profile_name.isEmpty()) {
            return new ResponseEntity<>("", HttpStatus.BAD_REQUEST);
        }

        GetFollowersReturnModel returnModel = manager.getFollowers(profile_name);

        if (returnModel.isSuccess()) {
            return ResponseEntity.status(HttpStatus.OK).body(objectMapper.writeValueAsString(returnModel));
        }
        else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectMapper.writeValueAsString(returnModel));
        }
    }

    @RequestMapping(value =  "/followed/{profile_name}", method = RequestMethod.GET)
    public ResponseEntity getFollowed(@PathVariable("profile_name") String profile_name) throws JsonProcessingException {
        if (profile_name.isEmpty()) {
            return new ResponseEntity<>("", HttpStatus.BAD_REQUEST);
        }

        GetFollowedReturnModel returnModel = manager.getFollowed(profile_name);


        if (returnModel.isSuccess()) {
            return ResponseEntity.status(HttpStatus.OK).body(objectMapper.writeValueAsString(returnModel));
        }
        else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectMapper.writeValueAsString(returnModel));
        }
    }

    @RequestMapping(value =  "/stats/{profile_name}", method = RequestMethod.GET)
    public ResponseEntity getStats(@PathVariable("profile_name") String profile_name) throws JsonProcessingException {
        if (profile_name.isEmpty()) {
            return new ResponseEntity<>("", HttpStatus.BAD_REQUEST);
        }

        GetStatsReturnModel returnModel = manager.getStats(profile_name);

        if (returnModel.isSuccess()) {
            return ResponseEntity.status(HttpStatus.OK).body(objectMapper.writeValueAsString(returnModel));
        }
        else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectMapper.writeValueAsString(returnModel));
        }
    }

}
