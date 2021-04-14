package com.kwetter.profileservice.dal.repository;

import com.kwetter.profileservice.dal.context.DatabaseContext;
import com.kwetter.profileservice.dal.interfaces.AbstractContext;
import com.kwetter.profileservice.models.returnModels.*;
import org.springframework.stereotype.Repository;

@Repository
public class ProfileRepository {
    private static AbstractContext tweetContext;

    public ProfileRepository() {
        this.tweetContext = new DatabaseContext();
    }

    public GetProfileReturnModel getProfile(String profile_name) {
        return tweetContext.getProfile(profile_name);
    }

    public UpdateProfileReturnModel updateProfile(int user_id, String bio, String location, String website) {
        return tweetContext.updateProfile(user_id, bio, location, website);
    }

    public UploadPictureReturnModel uploadPicture(int user_id, String picture) {
        return tweetContext.uploadPicture(user_id, picture);
    }
    public String followProfile() {
        return tweetContext.followProfile();
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
