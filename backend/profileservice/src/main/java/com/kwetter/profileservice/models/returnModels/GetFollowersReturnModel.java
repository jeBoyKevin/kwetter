package com.kwetter.profileservice.models.returnModels;

import java.util.ArrayList;
import java.util.List;

public class GetFollowersReturnModel {

    private boolean success;
    private String errorMessage;
    private List<String> followers = new ArrayList<>();

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public List<String> getFollowers() {
        return followers;
    }

    public void addFollower(String follower) {
        this.followers.add(follower);
    }
}
