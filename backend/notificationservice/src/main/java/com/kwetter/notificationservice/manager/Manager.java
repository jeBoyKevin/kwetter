package com.kwetter.notificationservice.manager;


import com.kwetter.notificationservice.dal.repository.NotificationRepository;
import com.kwetter.notificationservice.models.returnModels.GetNotificationsReturnModel;
import com.kwetter.notificationservice.models.returnModels.SetReadNotificationsReturnModel;

public class Manager {

    private NotificationRepository notificationRepository = new NotificationRepository();

    public GetNotificationsReturnModel getNotifications(int user_id) {
        return notificationRepository.getNotifications(user_id);
    }

    public void addNotification(String message, int user_id) {
        notificationRepository.addNotification(message, user_id);

    }

    public SetReadNotificationsReturnModel readNotifications(int user_id) {
       return notificationRepository.readNotifications(user_id);
    }
}
