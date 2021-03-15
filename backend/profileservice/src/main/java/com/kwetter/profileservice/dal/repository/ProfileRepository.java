package com.kwetter.profileservice.dal.repository;

import com.kwetter.profileservice.dal.context.DatabaseContext;
import com.kwetter.profileservice.dal.interfaces.AbstractContext;
import com.kwetter.profileservice.models.returnModels.GetProfileReturnModel;
import com.kwetter.profileservice.models.returnModels.UpdateProfileReturnModel;
import com.kwetter.profileservice.models.returnModels.UploadPictureReturnModel;
import org.springframework.stereotype.Repository;

@Repository
public class ProfileRepository {
    private static AbstractContext tweetContext;

    public ProfileRepository() {
        this.tweetContext = new DatabaseContext();
    }

    public GetProfileReturnModel sendTweet(int user_id) {
        return tweetContext.getProfile(user_id);
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

}
