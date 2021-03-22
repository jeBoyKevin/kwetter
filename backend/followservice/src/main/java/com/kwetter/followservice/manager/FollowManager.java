package com.kwetter.followservice.manager;

import com.kwetter.followservice.dal.repository.FollowRepository;
import com.kwetter.followservice.models.returnModels.GetStatsReturnModel;
import com.kwetter.followservice.models.returnModels.SendFollowReturnModel;
import com.kwetter.followservice.models.returnModels.GetFollowedReturnModel;
import com.kwetter.followservice.models.returnModels.GetFollowersReturnModel;

public class FollowManager {

    private FollowRepository tweetRepo = new FollowRepository();

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
