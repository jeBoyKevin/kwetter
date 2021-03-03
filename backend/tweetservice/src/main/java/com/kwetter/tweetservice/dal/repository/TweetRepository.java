package com.kwetter.tweetservice.dal.repository;

import com.kwetter.tweetservice.dal.context.TweetLocalContext;
import com.kwetter.tweetservice.dal.interfaces.AbstractTweetContext;

public class TweetRepository {
    private static AbstractTweetContext tweetContext;

    public TweetRepository() {
        this.tweetContext = new TweetLocalContext();
    }

    public boolean tweet() {
        return tweetContext.tweet();
    }
}
