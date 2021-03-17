package com.kwetter.followservice.models.returnModels;

import java.util.ArrayList;
import java.util.List;

public class GetFollowedReturnModel {

    private boolean success;
    private String errorMessage;
    private List<Integer> followed = new ArrayList<>();

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

    public List<Integer> getFollowed() {
        return followed;
    }

    public void addFollowed(int followed) {
        this.followed.add(followed);
    }
}
