package com.kwetter.notificationservice.models.returnModels;

import com.kwetter.notificationservice.models.Notification;

import java.util.ArrayList;
import java.util.List;

public class GetNotificationsReturnModel {
    private List<Notification> notifications = new ArrayList();
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

    public List<Notification> getNotifications() {
        return notifications;
    }

    public void addNotifications(Notification notification) {
        this.notifications.add(notification);
    }
}
