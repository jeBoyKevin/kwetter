package com.kwetter.profileservice.dal.context;

import com.kwetter.profileservice.dal.interfaces.AbstractContext;
import com.kwetter.profileservice.models.returnModels.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DatabaseContext extends AbstractContext {

    private final String connectionUrl = "jdbc:sqlserver://kwetter.database.windows.net:1433;DatabaseName=profile;user=kevints@kwetter;password=k98p&ewgt^64!wk2;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
    private static Statement statement;

    public void closeConnection() {
        // TODO Auto-generated method stub

    }


    public GetProfileReturnModel getProfile(String profile_name) {
        GetProfileReturnModel returnModel = new GetProfileReturnModel();
        try (Connection connection = DriverManager.getConnection(connectionUrl)) {
            try {
                CallableStatement cstmnt = connection.prepareCall("{CALL getProfile(?)}");
                cstmnt.setString(1, profile_name);

                cstmnt.execute();
                ResultSet rs = cstmnt.getResultSet();
                if (rs.next()) {
                    returnModel.setUser_id(rs.getInt("user_id"));
                    returnModel.setBio(rs.getString("bio"));
                    returnModel.setLocation(rs.getString("location"));
                    returnModel.setProfile_name(rs.getString("profile_name"));
                    returnModel.setPicture(rs.getString("picture"));
                    returnModel.setWebsite(rs.getString("website"));
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
    public UpdateProfileReturnModel updateProfile(int user_id, String bio, String location, String website) {
        UpdateProfileReturnModel returnModel = new UpdateProfileReturnModel();
        try (Connection connection = DriverManager.getConnection(connectionUrl)) {
            try {
                CallableStatement cstmnt = connection.prepareCall("{CALL uploadProfile(?,?,?,?)}");
                cstmnt.setInt(1, user_id);
                cstmnt.setString(2, bio);
                cstmnt.setString(3, location);
                cstmnt.setString(4, website);

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
    public UploadPictureReturnModel uploadPicture(int user_id, String picture) {

        UploadPictureReturnModel returnModel = new UploadPictureReturnModel();
        try (Connection connection = DriverManager.getConnection(connectionUrl)) {
            try {
                CallableStatement cstmnt = connection.prepareCall("{CALL uploadPicture(?,?)}");
                cstmnt.setInt(1, user_id);
                cstmnt.setString(2, picture);

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
    public String followProfile() {
        return null;
    }

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
    public GetFollowersReturnModel getFollowers(String profile_name) {
        GetFollowersReturnModel returnModel = new GetFollowersReturnModel();
        try (Connection connection = DriverManager.getConnection(connectionUrl)) {
            try {
                CallableStatement cstmnt = connection.prepareCall("{CALL getFollowers(?)}");
                cstmnt.setString(1, profile_name);
                cstmnt.execute();
                ResultSet rs = cstmnt.getResultSet();
                while (rs.next()) {
                    returnModel.addFollower(rs.getString("profile_name"));
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
    public GetFollowedReturnModel getFollowed(String profile_name) {
        GetFollowedReturnModel returnModel = new GetFollowedReturnModel();
        try (Connection connection = DriverManager.getConnection(connectionUrl)) {
            try {
                CallableStatement cstmnt = connection.prepareCall("{CALL getFollowed(?)}");
                cstmnt.setString(1, profile_name);
                cstmnt.execute();
                ResultSet rs = cstmnt.getResultSet();
                while (rs.next()) {
                    returnModel.addFollowed(rs.getString("profile_name"));
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
    public GetStatsReturnModel getStats(String profile_name) {
        GetStatsReturnModel returnModel = new GetStatsReturnModel();
        try (Connection connection = DriverManager.getConnection(connectionUrl)) {
            try {
                CallableStatement cstmnt = connection.prepareCall("{CALL getCountFollowed(?)}");
                cstmnt.setString(1, profile_name);

                cstmnt.execute();
                ResultSet rs = cstmnt.getResultSet();
                while (rs.next()) {
                    returnModel.setFollowed(rs.getInt("followed"));
                }

                CallableStatement cstmnt2 = connection.prepareCall("{CALL getCountFollowers(?)}");
                cstmnt2.setString(1, profile_name);

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

    @Override
    public UploadPictureReturnModel createProfile(String username) {
        UploadPictureReturnModel returnModel = new UploadPictureReturnModel();
        try (Connection connection = DriverManager.getConnection(connectionUrl)) {
            try {
                CallableStatement cstmnt = connection.prepareCall("{CALL createProfile(?)}");
                cstmnt.setString(1, username);

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
