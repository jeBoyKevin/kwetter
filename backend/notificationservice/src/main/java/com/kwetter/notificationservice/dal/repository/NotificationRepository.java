package com.kwetter.notificationservice.dal.repository;

import com.kwetter.notificationservice.dal.context.DatabaseContext;
import com.kwetter.notificationservice.dal.interfaces.AbstractContext;
import com.kwetter.notificationservice.models.returnModels.GetNotificationsReturnModel;
import com.kwetter.notificationservice.models.returnModels.SetReadNotificationsReturnModel;
import org.springframework.stereotype.Repository;

@Repository
public class NotificationRepository {
    private static AbstractContext notificationContext = new DatabaseContext();


    public GetNotificationsReturnModel getNotifications(int user_id) {
        return notificationContext.getNotifications(user_id);
    }

    public void addNotification(String message, int user_id) {
        notificationContext.addNotification(message, user_id);
    }

    public SetReadNotificationsReturnModel readNotifications(int user_id) {
        return notificationContext.readNotifications(user_id);
    }
}
