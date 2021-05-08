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

    public SendFollowReturnModel followUser(int user_id, int followed_user_id) {
        return tweetContext.followUser(user_id, followed_user_id);
    }

    public GetFollowersReturnModel getFollowers(String profile_name) {
        return tweetContext.getFollowers(profile_name);
    }

    public GetFollowedReturnModel getFollowed(String profile_name) {
        return tweetContext.getFollowed(profile_name);
    }

    public GetStatsReturnModel getStats(String profile_name) { return tweetContext.getStats(profile_name);}

    public UploadPictureReturnModel createProfile(String username, int user_id) {
        return tweetContext.createProfile(username, user_id);
    }

    public getByIdReturnModel getById(int id) {
        return tweetContext.getById(id);
    }

    public UpdateProfileReturnModel UnfollowUser(int user_id, int followed_user_id) {
        return tweetContext.UnfollowUser(user_id, followed_user_id);
    }
}
