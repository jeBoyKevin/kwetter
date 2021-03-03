package com.kwetter.tweetservice.manager;

import com.kwetter.tweetservice.dal.repository.TweetRepository;

public class TweetManager {

    private TweetRepository tweetRepo = new TweetRepository();
    public boolean tweet() {
        return tweetRepo.tweet();
    }
}
