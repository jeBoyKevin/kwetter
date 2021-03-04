package com.kwetter.tweetservice.models.submitModels;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SendTweetSubmitModel {

    @JsonProperty("user_id")
    private int user_id;
    @JsonProperty("message")
    private String message;

    public int getUser_id() {
        return user_id;
    }

    public String getMessage() {
        return message;
    }
}
