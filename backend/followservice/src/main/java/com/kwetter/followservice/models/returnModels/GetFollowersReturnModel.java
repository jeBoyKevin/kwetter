package com.kwetter.followservice.models.returnModels;

import java.util.ArrayList;
import java.util.List;

public class GetFollowersReturnModel {

    private boolean success;
    private String errorMessage;
    private List<Integer> followers = new ArrayList<>();

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

    public List<Integer> getFollowers() {
        return followers;
    }

    public void addFollower(int follower) {
        this.followers.add(follower);
    }
}
