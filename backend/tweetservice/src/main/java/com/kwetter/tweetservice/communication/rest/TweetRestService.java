package com.kwetter.tweetservice.communication.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.kwetter.tweetservice.manager.TweetManager;
import com.kwetter.tweetservice.models.returnModels.GetTweetsFromReturnModel;
import com.kwetter.tweetservice.models.returnModels.SendTweetReturnModel;
import com.kwetter.tweetservice.models.submitModels.SendTweetSubmitModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TweetRestService {

    @Autowired
    private TweetManager manager = new TweetManager();

    private ObjectMapper objectMapper = new ObjectMapper();

    @RequestMapping(value =  "",
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

    @RequestMapping(value =  "", method = RequestMethod.GET)
    public ResponseEntity getTweets() throws JsonProcessingException {
        return ResponseEntity.status(HttpStatus.OK)
                .body(objectMapper.writeValueAsString(manager.getTweets()));
    }

    @RequestMapping(value =  "/{tweet_id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteTweet(@PathVariable("tweet_id") int tweet_id) throws JsonProcessingException {
        if (tweet_id == 0) {
            return new ResponseEntity<>("", HttpStatus.BAD_REQUEST);
        }

        SendTweetReturnModel returnModel = manager.deleteTweet(tweet_id);

        if (returnModel.isSuccess()) {
            return ResponseEntity.status(HttpStatus.OK).body(objectMapper.writeValueAsString(returnModel));
        }
        else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(objectMapper.writeValueAsString(returnModel));
        }
    }

    @RequestMapping(value =  "/like", method = RequestMethod.POST)
    public ResponseEntity likeTweet() throws JsonProcessingException {
        return ResponseEntity.status(HttpStatus.OK)
                .body(objectMapper.writeValueAsString(manager.likeTweet()));
    }

    @RequestMapping(value =  "/mentions", method = RequestMethod.GET)
    public ResponseEntity getMentions() throws JsonProcessingException {
        return ResponseEntity.status(HttpStatus.OK)
                .body(objectMapper.writeValueAsString(manager.getMentions()));
    }

    @RequestMapping(value = "/{user_id}", method = RequestMethod.GET)
    public ResponseEntity getTweetsFromUser(@PathVariable("user_id") int user_id) throws JsonProcessingException {
        if (user_id == 0) {
            return new ResponseEntity<>("", HttpStatus.BAD_REQUEST);
        }

        GetTweetsFromReturnModel returnModel = manager.getTweetsFromUser(user_id);

        if (returnModel.isSuccess()) {
            return ResponseEntity.status(HttpStatus.OK).body(objectMapper.writeValueAsString(returnModel));
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(objectMapper.writeValueAsString(returnModel));
        }
    }
}
