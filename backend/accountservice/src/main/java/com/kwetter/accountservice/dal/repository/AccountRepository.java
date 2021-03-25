package com.kwetter.accountservice.dal.repository;


import com.kwetter.accountservice.dal.interfaces.AbstractAccountContext;
import org.springframework.stereotype.Repository;

@Repository
public class AccountRepository {
    private static AbstractAccountContext tweetContext;

    public AccountRepository() {
        this.tweetContext = new FollowDatabaseContext();
    }

    public SendFollowReturnModel followUser(int user_id, int followed_user_id) {
        return tweetContext.followUser(user_id, followed_user_id);
    }

    public GetFollowersReturnModel getFollowers(int user_id) {
        return tweetContext.getFollowers(user_id);
    }

    public GetFollowedReturnModel getFollowed(int user_id) {
        return tweetContext.getFollowed(user_id);
    }

    public GetStatsReturnModel getStats(int user_id) { return tweetContext.getStats(user_id);}


}
