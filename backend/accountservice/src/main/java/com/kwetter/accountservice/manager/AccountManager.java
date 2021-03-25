package com.kwetter.accountservice.manager;

import com.kwetter.accountservice.dal.repository.AccountRepository;

public class AccountManager {

    private AccountRepository tweetRepo = new AccountRepository();

    public SendFollowReturnModel followUser(int user_id, int followed_user_id) {
        return tweetRepo.followUser(user_id, followed_user_id);
    }

    public GetFollowersReturnModel getFollowers(int user_id) {
        return tweetRepo.getFollowers(user_id);
    }

    public GetFollowedReturnModel getFollowed(int user_id) {
        return tweetRepo.getFollowed(user_id);
    }

    public GetStatsReturnModel getStats(int user_id) {return tweetRepo.getStats(user_id);}
}
