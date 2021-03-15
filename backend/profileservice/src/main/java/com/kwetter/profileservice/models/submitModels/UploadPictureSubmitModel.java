package com.kwetter.profileservice.models.submitModels;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UploadPictureSubmitModel {
    @JsonProperty
    private int user_id;

    @JsonProperty
    private String picture;

    public String getPicture() {
        return picture;
    }

    public int getUser_id() {
        return user_id;
    }
}
