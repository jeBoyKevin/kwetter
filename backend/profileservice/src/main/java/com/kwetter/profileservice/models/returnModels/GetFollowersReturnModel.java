package com.kwetter.profileservice.models.returnModels;

import java.util.ArrayList;
import java.util.List;

public class GetFollowersReturnModel {

    private boolean success;
    private String errorMessage;
    private int user_id;
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

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
}
