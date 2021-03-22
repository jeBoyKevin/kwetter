package com.kwetter.followservice.dal.interfaces;

import com.kwetter.followservice.models.returnModels.GetStatsReturnModel;
import com.kwetter.followservice.models.returnModels.SendFollowReturnModel;
import com.kwetter.followservice.models.returnModels.GetFollowedReturnModel;
import com.kwetter.followservice.models.returnModels.GetFollowersReturnModel;

public abstract class AbstractFollowContext {
    public abstract SendFollowReturnModel followUser(int user_id, int followed_user_id);
    public abstract GetFollowersReturnModel getFollowers(int user_id);
    public abstract GetFollowedReturnModel getFollowed(int user_id);
    public abstract GetStatsReturnModel getStats(int user_id);
}
