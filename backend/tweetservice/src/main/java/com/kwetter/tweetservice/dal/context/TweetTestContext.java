package com.kwetter.tweetservice.dal.context;

import com.kwetter.tweetservice.dal.interfaces.AbstractTweetContext;
import com.kwetter.tweetservice.models.Tweet;
import com.kwetter.tweetservice.models.returnModels.GetTweetsFromReturnModel;
import com.kwetter.tweetservice.models.returnModels.SendTweetReturnModel;

import java.sql.*;

public class TweetTestContext extends AbstractTweetContext {


    @Override
    public SendTweetReturnModel sendTweet(int user_id, String message) {
        SendTweetReturnModel tweetReturnModel = new SendTweetReturnModel();
        tweetReturnModel.setSuccess(true);
        return tweetReturnModel;
    }

    @Override
    public String getMentions() {
        throw new UnsupportedOperationException();
    }

    @Override
    public SendTweetReturnModel deleteTweet(int tweet_id) {
        SendTweetReturnModel tweetReturnModel = new SendTweetReturnModel();
        tweetReturnModel.setSuccess(true);
        return tweetReturnModel;
    }

    @Override
    public String likeTweet() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String getTweets() {
        throw new UnsupportedOperationException();
    }

    @Override
    public GetTweetsFromReturnModel getTweetsFromUser(int user_id) {
        GetTweetsFromReturnModel getTweetsFromReturnModel = new GetTweetsFromReturnModel();

        Tweet tweet = new Tweet();
        tweet.setTweet_id(0);
        tweet.setUser_id(0);
        tweet.setDate("01-06-2021");
        tweet.setLikes(5);
        tweet.setMessage("Dit is een test tweet");
        getTweetsFromReturnModel.addTweet(tweet);

        getTweetsFromReturnModel.setSuccess(true);

        return getTweetsFromReturnModel;
    }
}
