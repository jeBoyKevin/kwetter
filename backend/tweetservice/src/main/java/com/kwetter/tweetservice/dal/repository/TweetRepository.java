package com.kwetter.tweetservice.dal.repository;

import com.kwetter.tweetservice.dal.context.TweetDatabaseContext;
import com.kwetter.tweetservice.dal.interfaces.AbstractTweetContext;
import com.kwetter.tweetservice.models.returnModels.GetTweetsFromReturnModel;
import com.kwetter.tweetservice.models.returnModels.SendTweetReturnModel;

import org.springframework.stereotype.Repository;

@Repository
public class TweetRepository {
    private static AbstractTweetContext tweetContext;

    public TweetRepository() {
        this.tweetContext = new TweetDatabaseContext();
    }

    public SendTweetReturnModel sendTweet(int user_id, String message) {
        return tweetContext.sendTweet(user_id, message);
    }

    public String getMentions() {
        return tweetContext.getMentions();
    }

    public SendTweetReturnModel deleteTweet(int tweet_id) {
        return tweetContext.deleteTweet(tweet_id);
    }
    public String likeTweet() {
        return tweetContext.likeTweet();
    }
    public String getTweets() {
        return tweetContext.getTweets();
    }
    public GetTweetsFromReturnModel getTweetsFromUser(int user_id) {return tweetContext.getTweetsFromUser(user_id);}

}
