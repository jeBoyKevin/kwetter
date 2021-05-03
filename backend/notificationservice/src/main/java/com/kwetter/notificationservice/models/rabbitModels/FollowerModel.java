package com.kwetter.notificationservice.models.rabbitModels;

public class FollowerModel {
    private String message;
    private int user_id;

    public int getUser_id() {
        return user_id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
