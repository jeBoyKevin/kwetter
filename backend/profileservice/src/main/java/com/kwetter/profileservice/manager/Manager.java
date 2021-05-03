package com.kwetter.profileservice.manager;

import com.kwetter.profileservice.rabbit.Sender;
import com.kwetter.profileservice.dal.repository.ProfileRepository;
import com.kwetter.profileservice.models.returnModels.*;

public class Manager {

    private Sender sender = new Sender();

    private ProfileRepository profileRepo = new ProfileRepository();

    public GetProfileReturnModel getProfile(String profile_name) {
        return profileRepo.getProfile(profile_name);
    }
    public UploadPictureReturnModel uploadPicture(int user_id, String picture) {return profileRepo.uploadPicture(user_id, picture);}

    public UpdateProfileReturnModel updateProfile(int user_id, String bio, String location,
                                                  String website) {
        return profileRepo.updateProfile(user_id, bio, location, website);
    }

    public SendFollowReturnModel followUser(int user_id, int followed_user_id) {
        try {
            sender.send(followed_user_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return profileRepo.followUser(user_id, followed_user_id);
    }

    public GetFollowersReturnModel getFollowers(String profile_name) {
        return profileRepo.getFollowers(profile_name);
    }

    public GetFollowedReturnModel getFollowed(String profile_name) {
        return profileRepo.getFollowed(profile_name);
    }

    public GetStatsReturnModel getStats(String profile_name) {return profileRepo.getStats(profile_name);}

    public UploadPictureReturnModel createProfile(String username) {
        return profileRepo.createProfile(username);
    }

    public getByIdReturnModel getById(int id) {
        return profileRepo.getById(id);
    }

    public UpdateProfileReturnModel UnfollowUser(int user_id, int followed_user_id) {
        return profileRepo.UnfollowUser(user_id, followed_user_id);
    }
}
