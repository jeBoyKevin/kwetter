package com.kwetter.tweetservice.manager;

import com.kwetter.tweetservice.dal.repository.TweetRepository;
import com.kwetter.tweetservice.models.returnModels.SendTweetReturnModel;

public class TweetManager {

    private TweetRepository tweetRepo = new TweetRepository();

    public SendTweetReturnModel sendTweet(int user_id, String message) {
        return tweetRepo.sendTweet(user_id, message);
    }
    public String getMentions() {return tweetRepo.getMentions();}
    public SendTweetReturnModel deleteTweet(int tweet_id) {return tweetRepo.deleteTweet(tweet_id);}
    public String likeTweet() {return tweetRepo.likeTweet();}
    public String getTweets() {return tweetRepo.getTweets();}
}
