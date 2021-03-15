package com.kwetter.profileservice.dal.interfaces;

import com.kwetter.profileservice.models.returnModels.GetProfileReturnModel;
import com.kwetter.profileservice.models.returnModels.UpdateProfileReturnModel;
import com.kwetter.profileservice.models.returnModels.UploadPictureReturnModel;

public abstract class AbstractContext {
    public abstract GetProfileReturnModel getProfile(int user_id);
    public abstract UpdateProfileReturnModel updateProfile(int user_id, String bio, String location, String website);
    public abstract UploadPictureReturnModel uploadPicture(int user_id, String picture);
    public abstract String followProfile();
}
