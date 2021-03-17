package com.kwetter.followservice.models.submitModels;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SendFollowSubmitModel {

    @JsonProperty("user_id")
    private int user_id;
    @JsonProperty("followed_user_id")
    private int followed_user_id;

    public int getUser_id() {
        return user_id;
    }

    public int getFollowed_user_id() {
        return followed_user_id;
    }
}
