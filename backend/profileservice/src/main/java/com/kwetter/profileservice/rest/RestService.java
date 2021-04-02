package com.kwetter.profileservice.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kwetter.profileservice.manager.Manager;
import com.kwetter.profileservice.models.returnModels.GetProfileReturnModel;
import com.kwetter.profileservice.models.returnModels.UpdateProfileReturnModel;
import com.kwetter.profileservice.models.returnModels.UploadPictureReturnModel;
import com.kwetter.profileservice.models.submitModels.GetProfileSubmitModel;
import com.kwetter.profileservice.models.submitModels.UpdateProfileSubmitModel;
import com.kwetter.profileservice.models.submitModels.UploadPictureSubmitModel;
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

    @RequestMapping(value =  "/{user_id}", method = RequestMethod.GET)
    public ResponseEntity getProfile(@PathVariable("user_id") int user_id) throws JsonProcessingException {

        if (user_id == 0) {
            return new ResponseEntity<>("", HttpStatus.BAD_REQUEST);
        }

        GetProfileReturnModel returnModel = manager.getProfile(user_id);
        if (returnModel.isSuccess()) {
            return ResponseEntity.status(HttpStatus.OK).body(objectMapper.writeValueAsString(returnModel));
        }
        else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectMapper.writeValueAsString(returnModel));
        }
    }
}
