package com.kwetter.profileservice.dal.interfaces;

import com.kwetter.profileservice.models.returnModels.*;

public abstract class AbstractContext {
    public abstract GetProfileReturnModel getProfile(String user_id);
    public abstract UpdateProfileReturnModel updateProfile(int user_id, String bio, String location, String website);
    public abstract UploadPictureReturnModel uploadPicture(int user_id, String picture);
    public abstract SendFollowReturnModel followUser(int user_id, int followed_user_id);
    public abstract GetFollowersReturnModel getFollowers(String profile_name);
    public abstract GetFollowedReturnModel getFollowed(String profile_name);
    public abstract GetStatsReturnModel getStats(String profile_name);

    public abstract UploadPictureReturnModel createProfile(String username);

    public abstract getByIdReturnModel getById(int id);
    public abstract UpdateProfileReturnModel UnfollowUser(int user_id, int followed_user_id);
}
