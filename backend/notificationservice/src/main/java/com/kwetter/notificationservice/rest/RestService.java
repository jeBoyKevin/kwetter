package com.kwetter.notificationservice.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kwetter.notificationservice.manager.Manager;
import com.kwetter.notificationservice.models.returnModels.GetNotificationsReturnModel;
import com.kwetter.notificationservice.models.returnModels.SetReadNotificationsReturnModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class RestService {

    @Autowired
    private Manager manager = new Manager();

    private ObjectMapper objectMapper = new ObjectMapper();

    @RequestMapping(value = "/{user_id}",
                    method = RequestMethod.GET)
    public ResponseEntity getNotifications(@PathVariable("user_id") int user_id) throws JsonProcessingException {
        if (user_id == 0) {
            return new ResponseEntity<>("", HttpStatus.BAD_REQUEST);
        }
        GetNotificationsReturnModel returnModel = manager.getNotifications(user_id);

        if (returnModel.isSuccess()) {
            return ResponseEntity.status(HttpStatus.OK).body(objectMapper.writeValueAsString(returnModel));
        }
        else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectMapper.writeValueAsString(returnModel));
        }
    }

    @RequestMapping(value =  "/{user_id}",
                    method = RequestMethod.PUT)
    public ResponseEntity readNotifications(@PathVariable("user_id") int user_id) throws JsonProcessingException {

        if (user_id == 0) {
            return new ResponseEntity<>("", HttpStatus.BAD_REQUEST);
        }

        SetReadNotificationsReturnModel returnModel = manager.readNotifications(user_id);

        if (returnModel.isSuccess()) {
            return ResponseEntity.status(HttpStatus.OK).body(objectMapper.writeValueAsString(returnModel));
        }
        else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectMapper.writeValueAsString(returnModel));
        }
    }

}
