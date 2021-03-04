package com.kwetter.tweetservice.dal.context;

import com.kwetter.tweetservice.dal.interfaces.AbstractTweetContext;
import com.kwetter.tweetservice.models.returnModels.SendTweetReturnModel;

public class TweetLocalContext  extends AbstractTweetContext {

    public SendTweetReturnModel sendTweet(int user_id, String message) {
        return null;
    }

    public String getMentions() {
        return null;
    }

    public String deleteTweet() {
        return null;
    }

    public String likeTweet() {
        return null;
    }

    public String getTweets() {
        return null;
    }
}
