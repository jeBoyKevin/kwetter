package com.kwetter.tweetservice.dal.interfaces;

import com.kwetter.tweetservice.models.returnModels.GetTweetsFromReturnModel;
import com.kwetter.tweetservice.models.returnModels.SendTweetReturnModel;

public abstract class AbstractTweetContext {
    public abstract SendTweetReturnModel sendTweet(int user_id, String message);
    public abstract String getMentions();
    public abstract SendTweetReturnModel deleteTweet(int tweet_id);
    public abstract String likeTweet();
    public abstract String getTweets();
    public abstract GetTweetsFromReturnModel getTweetsFromUser(int user_id);
}
