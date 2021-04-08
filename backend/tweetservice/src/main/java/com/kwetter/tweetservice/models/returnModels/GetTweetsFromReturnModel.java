package com.kwetter.tweetservice.models.returnModels;

import com.kwetter.tweetservice.models.Tweet;

import java.util.ArrayList;
import java.util.List;

public class GetTweetsFromReturnModel {
    private List<Tweet> tweets = new ArrayList<>();
    private boolean success;
    private String errorMessage;


    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public List<Tweet> getTweets() {
        return tweets;
    }

    public void addTweet(Tweet tweet) {
        this.tweets.add(tweet);
    }
}
