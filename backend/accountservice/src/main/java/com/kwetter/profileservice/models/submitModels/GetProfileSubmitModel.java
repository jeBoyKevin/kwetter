package com.kwetter.profileservice.models.submitModels;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GetProfileSubmitModel {
    @JsonProperty
    private int user_id;

    public int getUser_id() {
        return user_id;
    }
}
