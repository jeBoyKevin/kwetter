package com.kwetter.notificationservice.manager;


import com.kwetter.notificationservice.dal.repository.NotificationRepository;
import com.kwetter.notificationservice.models.rabbitModels.FollowerModel;
import com.kwetter.notificationservice.models.returnModels.GetNotificationsReturnModel;

public class Manager {

    private NotificationRepository profileRepo = new NotificationRepository();

    public GetNotificationsReturnModel getNotifications(int user_id) {
        return profileRepo.getNotifications(user_id);
    }

    public void addNotification(String message, int user_id) {
        profileRepo.addNotification(message, user_id);

    }
}
