package com.kwetter.followservice.dal.context;

import com.kwetter.followservice.dal.interfaces.AbstractFollowContext;
import com.kwetter.followservice.models.returnModels.GetStatsReturnModel;
import com.kwetter.followservice.models.returnModels.SendFollowReturnModel;
import com.kwetter.followservice.models.returnModels.GetFollowedReturnModel;
import com.kwetter.followservice.models.returnModels.GetFollowersReturnModel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class FollowDatabaseContext extends AbstractFollowContext {

    private final String connectionUrl = "jdbc:sqlserver://kwetter.database.windows.net:1433;DatabaseName=follow;user=kevints@kwetter;password=k98p&ewgt^64!wk2;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";


    public SendFollowReturnModel followUser(int user_id, int followed_user_id) {
        SendFollowReturnModel returnModel = new SendFollowReturnModel();
        try (Connection connection = DriverManager.getConnection(connectionUrl)) {
            try {
                CallableStatement cstmnt = connection.prepareCall("{CALL followUser(?,?)}");
                cstmnt.setInt(1, user_id);
                cstmnt.setInt(2, followed_user_id);

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

    @Override
    public GetFollowersReturnModel getFollowers(int user_id) {
        GetFollowersReturnModel returnModel = new GetFollowersReturnModel();
        try (Connection connection = DriverManager.getConnection(connectionUrl)) {
            try {
                CallableStatement cstmnt = connection.prepareCall("{CALL getFollowers(?)}");
                cstmnt.setInt(1, user_id);
                cstmnt.execute();
                ResultSet rs = cstmnt.getResultSet();
                while (rs.next()) {
                    returnModel.addFollower(rs.getInt("user_id"));
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
    public GetFollowedReturnModel getFollowed(int user_id) {
        GetFollowedReturnModel returnModel = new GetFollowedReturnModel();
        try (Connection connection = DriverManager.getConnection(connectionUrl)) {
            try {
                CallableStatement cstmnt = connection.prepareCall("{CALL getFollowed(?)}");
                cstmnt.setInt(1, user_id);
                cstmnt.execute();
                ResultSet rs = cstmnt.getResultSet();
                while (rs.next()) {
                    returnModel.addFollowed(rs.getInt("followed_user_id"));
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
    public GetStatsReturnModel getStats(int user_id) {
        GetStatsReturnModel returnModel = new GetStatsReturnModel();
        try (Connection connection = DriverManager.getConnection(connectionUrl)) {
            try {
                CallableStatement cstmnt = connection.prepareCall("{CALL getCountFollowed(?)}");
                cstmnt.setInt(1, user_id);

                cstmnt.execute();
                ResultSet rs = cstmnt.getResultSet();
                while (rs.next()) {
                    returnModel.setFollowed(rs.getInt("followed"));
                }

                CallableStatement cstmnt2 = connection.prepareCall("{CALL getCountFollowers(?)}");
                cstmnt2.setInt(1, user_id);

                cstmnt2.execute();
                ResultSet rs2 = cstmnt2.getResultSet();
                while (rs2.next()) {
                    returnModel.setFollowers(rs2.getInt("followers"));
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
}
