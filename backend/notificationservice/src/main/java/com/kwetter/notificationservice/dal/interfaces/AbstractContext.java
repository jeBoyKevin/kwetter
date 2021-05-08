package com.kwetter.notificationservice.dal.interfaces;


import com.kwetter.notificationservice.models.returnModels.GetNotificationsReturnModel;
import com.kwetter.notificationservice.models.returnModels.SetReadNotificationsReturnModel;

public abstract class AbstractContext {

    public abstract GetNotificationsReturnModel getNotifications(int user_id);

    public abstract void addNotification(String message, int user_id);

    public abstract SetReadNotificationsReturnModel readNotifications(int user_id);
}
