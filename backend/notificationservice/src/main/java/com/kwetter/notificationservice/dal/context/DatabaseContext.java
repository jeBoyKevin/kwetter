package com.kwetter.notificationservice.dal.context;


import com.kwetter.notificationservice.dal.interfaces.AbstractContext;
import com.kwetter.notificationservice.models.Notification;
import com.kwetter.notificationservice.models.returnModels.GetNotificationsReturnModel;
import com.kwetter.notificationservice.models.returnModels.SetReadNotificationsReturnModel;

import java.sql.*;

public class DatabaseContext extends AbstractContext {

    private final String connectionUrl = "jdbc:sqlserver://kwetter.database.windows.net:1433;DatabaseName=notification;user=kevints@kwetter;password=k98p&ewgt^64!wk2;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
    private static Statement statement;

    public void closeConnection() {
        // TODO Auto-generated method stub

    }

    @Override
    public GetNotificationsReturnModel getNotifications(int user_id) {
        GetNotificationsReturnModel returnModel = new GetNotificationsReturnModel();
        try (Connection connection = DriverManager.getConnection(connectionUrl)) {
            try {
                CallableStatement cstmnt = connection.prepareCall("{CALL getNotifications(?)}");
                cstmnt.setInt(1, user_id);

                cstmnt.execute();
                ResultSet rs = cstmnt.getResultSet();
                while (rs.next()) {
                    Notification notification = new Notification();
                    notification.setMessage(rs.getString("message"));
                    notification.setIs_read(rs.getBoolean("is_read"));
                    returnModel.addNotifications(notification);
                }
                returnModel.setSuccess(true);
            } catch (SQLException e) {
                returnModel.setSuccess(false);
                returnModel.setErrorMessage(e.toString());
            }
        } catch (SQLException e) {
            returnModel.setErrorMessage(e.getMessage());
            returnModel.setSuccess(false);
        }
        return returnModel;
    }

    @Override
    public void addNotification(String message, int user_id) {
        try (Connection connection = DriverManager.getConnection(connectionUrl)) {
            try {
                CallableStatement cstmnt = connection.prepareCall("{CALL addNotification(?,?)}");
                cstmnt.setInt(1, user_id);
                cstmnt.setString(2, message);

                cstmnt.executeUpdate();

            } catch (SQLException e) {
                System.out.println(e);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    @Override
    public SetReadNotificationsReturnModel readNotifications(int user_id) {
        SetReadNotificationsReturnModel returnModel = new SetReadNotificationsReturnModel();
        try (Connection connection = DriverManager.getConnection(connectionUrl)) {
            try {
                CallableStatement cstmnt = connection.prepareCall("{CALL readNotifications(?)}");
                cstmnt.setInt(1, user_id);

                cstmnt.executeUpdate();

                returnModel.setSuccess(true);
            } catch (SQLException e) {
                returnModel.setSuccess(false);
                returnModel.setErrorMessage(e.toString());
            }
        } catch (SQLException e) {
            returnModel.setErrorMessage(e.getMessage());
            returnModel.setSuccess(false);
        }
        return returnModel;
    }
}
