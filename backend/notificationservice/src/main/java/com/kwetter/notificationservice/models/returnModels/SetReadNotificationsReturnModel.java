package com.kwetter.notificationservice.models.returnModels;

public class SetReadNotificationsReturnModel {
    private boolean isSuccess;
    private String errorMessage;

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
