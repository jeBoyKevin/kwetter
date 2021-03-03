package com.kwetter.tweetservice.dal.context;

import com.kwetter.tweetservice.dal.interfaces.AbstractTweetContext;

public class TweetLocalContext  extends AbstractTweetContext {

    public boolean tweet() {
        return true;
    }
}
