package com.kwetter.profileservice.manager;

import com.kwetter.profileservice.dal.repository.ProfileRepository;
import com.kwetter.profileservice.models.returnModels.*;

public class Manager {

    private ProfileRepository profileRepo = new ProfileRepository();

    public GetProfileReturnModel getProfile(String profile_name) {
        return profileRepo.getProfile(profile_name);
    }
    public UploadPictureReturnModel uploadPicture(int user_id, String picture) {return profileRepo.uploadPicture(user_id, picture);}
    public String followProfile() {return profileRepo.followProfile();}

    public UpdateProfileReturnModel updateProfile(int user_id, String bio, String location,
                                                  String website) {
        return profileRepo.updateProfile(user_id, bio, location, website);
    }

    public SendFollowReturnModel followUser(int user_id, int followed_user_id) {
        return profileRepo.followUser(user_id, followed_user_id);
    }

    public GetFollowersReturnModel getFollowers(int user_id) {
        return profileRepo.getFollowers(user_id);
    }

    public GetFollowedReturnModel getFollowed(int user_id) {
        return profileRepo.getFollowed(user_id);
    }

    public GetStatsReturnModel getStats(int user_id) {return profileRepo.getStats(user_id);}
}
