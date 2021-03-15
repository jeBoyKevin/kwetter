package com.kwetter.profileservice.manager;

import com.kwetter.profileservice.dal.repository.ProfileRepository;
import com.kwetter.profileservice.models.returnModels.GetProfileReturnModel;
import com.kwetter.profileservice.models.returnModels.UpdateProfileReturnModel;
import com.kwetter.profileservice.models.returnModels.UploadPictureReturnModel;

public class Manager {

    private ProfileRepository profileRepo = new ProfileRepository();

    public GetProfileReturnModel getProfile(int user_id) {
        return profileRepo.sendTweet(user_id);
    }
    public UploadPictureReturnModel uploadPicture(int user_id, String picture) {return profileRepo.uploadPicture(user_id, picture);}
    public String followProfile() {return profileRepo.followProfile();}

    public UpdateProfileReturnModel updateProfile(int user_id, String bio, String location,
                                                  String website) {
        return profileRepo.updateProfile(user_id, bio, location, website);
    }
}
