package com.kwetter.profileservice.dal.context;

import com.kwetter.profileservice.dal.interfaces.AbstractContext;
import com.kwetter.profileservice.models.returnModels.*;

import java.sql.*;

public class LocalContext extends AbstractContext {


    public GetProfileReturnModel getProfile(String profile_name) {
        GetProfileReturnModel returnModel = new GetProfileReturnModel();
        returnModel.setUser_id(4);
        returnModel.setBio("Dit is een bio");
        returnModel.setProfile_name("testUser");
        returnModel.setLocation("Nederweert");
        returnModel.setWebsite("www.google.com");
        returnModel.setSuccess(true);
        return returnModel;
    }

    @Override
    public UpdateProfileReturnModel updateProfile(int user_id, String bio, String location, String website) {
        UpdateProfileReturnModel returnModel = new UpdateProfileReturnModel();
        return returnModel;
    }

    @Override
    public UploadPictureReturnModel uploadPicture(int user_id, String picture) {
        UploadPictureReturnModel returnModel = new UploadPictureReturnModel();
        return returnModel;
    }

    @Override
    public String followProfile() {
        return null;
    }

    public SendFollowReturnModel followUser(int user_id, int followed_user_id) {
        SendFollowReturnModel returnModel = new SendFollowReturnModel();

        return returnModel;
    }

    @Override
    public GetFollowersReturnModel getFollowers(String profile_name) {
        GetFollowersReturnModel returnModel = new GetFollowersReturnModel();

        return returnModel;
    }

    @Override
    public GetFollowedReturnModel getFollowed(String profile_name) {
        GetFollowedReturnModel returnModel = new GetFollowedReturnModel();

        return returnModel;
    }

    @Override
    public GetStatsReturnModel getStats(String profile_name) {
        GetStatsReturnModel returnModel = new GetStatsReturnModel();

        return returnModel;
    }

    @Override
    public UploadPictureReturnModel createProfile(String username) {
        UploadPictureReturnModel returnModel = new UploadPictureReturnModel();

        return returnModel;
    }
}
