package com.kwetter.profileservice.models.submitModels;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UpdateProfileSubmitModel {

    @JsonProperty
    private int  user_id;

    @JsonProperty
    private String location;

    @JsonProperty
    private String website;

    @JsonProperty
    private String bio;


    public int getUser_id() {
        return user_id;
    }

    public String getLocation() {
        return location;
    }


    public String getWebsite() {
        return website;
    }

    public String getBio() {
        return bio;
    }


}
