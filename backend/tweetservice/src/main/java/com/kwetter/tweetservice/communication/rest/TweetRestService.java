package com.kwetter.tweetservice.communication.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.kwetter.tweetservice.manager.TweetManager;
import com.kwetter.tweetservice.models.returnModels.SendTweetReturnModel;
import com.kwetter.tweetservice.models.submitModels.SendTweetSubmitModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TweetRestService {

    @Autowired
    private TweetManager manager = new TweetManager();

    private ObjectMapper objectMapper = new ObjectMapper();

    @RequestMapping(value =  "/tweet",
                    method = RequestMethod.POST,
                    consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity sendTweet(@RequestBody String json) throws JsonProcessingException {
    SendTweetSubmitModel submitModel = objectMapper.readValue(json, SendTweetSubmitModel.class);
        if (submitModel.getUser_id() == 0 || submitModel.getMessage().isEmpty()) {
          return new ResponseEntity<>("", HttpStatus.BAD_REQUEST);
        }

        SendTweetReturnModel returnModel = manager.sendTweet(submitModel.getUser_id(), submitModel.getMessage());

        if (returnModel.isSuccess()) {
            return ResponseEntity.status(HttpStatus.OK).body(objectMapper.writeValueAsString(returnModel));
        }
        else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectMapper.writeValueAsString(returnModel));
        }
    }

    @RequestMapping(value =  "/tweet", method = RequestMethod.GET)
    public ResponseEntity getTweets() throws JsonProcessingException {
        return ResponseEntity.status(HttpStatus.OK)
                .body(objectMapper.writeValueAsString(manager.getTweets()));
    }

    @RequestMapping(value =  "/tweet", method = RequestMethod.DELETE)
    public ResponseEntity deleteTweet() throws JsonProcessingException {
        return ResponseEntity.status(HttpStatus.OK)
                .body(objectMapper.writeValueAsString(manager.deleteTweet()));
    }

    @RequestMapping(value =  "/tweet/like", method = RequestMethod.POST)
    public ResponseEntity likeTweet() throws JsonProcessingException {
        return ResponseEntity.status(HttpStatus.OK)
                .body(objectMapper.writeValueAsString(manager.likeTweet()));
    }

    @RequestMapping(value =  "/tweet/mentions", method = RequestMethod.GET)
    public ResponseEntity getMentions() throws JsonProcessingException {
        return ResponseEntity.status(HttpStatus.OK)
                .body(objectMapper.writeValueAsString(manager.getMentions()));
    }
}
