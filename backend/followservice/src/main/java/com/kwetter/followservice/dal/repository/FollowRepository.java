package com.kwetter.followservice.dal.repository;

import com.kwetter.followservice.dal.context.FollowDatabaseContext;
import com.kwetter.followservice.dal.interfaces.AbstractFollowContext;
import com.kwetter.followservice.models.returnModels.GetStatsReturnModel;
import com.kwetter.followservice.models.returnModels.SendFollowReturnModel;

import com.kwetter.followservice.models.returnModels.GetFollowedReturnModel;
import com.kwetter.followservice.models.returnModels.GetFollowersReturnModel;
import org.springframework.stereotype.Repository;

@Repository
public class FollowRepository {
    private static AbstractFollowContext tweetContext;

    public FollowRepository() {
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
