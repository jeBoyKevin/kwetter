package com.kwetter.profileservice.dal.context;

import com.kwetter.profileservice.dal.interfaces.AbstractContext;
import com.kwetter.profileservice.models.returnModels.GetProfileReturnModel;
import com.kwetter.profileservice.models.returnModels.UpdateProfileReturnModel;
import com.kwetter.profileservice.models.returnModels.UploadPictureReturnModel;

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


    public GetProfileReturnModel getProfile(int user_id) {
        GetProfileReturnModel returnModel = new GetProfileReturnModel();
        try (Connection connection = DriverManager.getConnection(connectionUrl)) {
            try {
                CallableStatement cstmnt = connection.prepareCall("{CALL getProfile(?)}");
                cstmnt.setInt(1, user_id);

                cstmnt.execute();
                ResultSet rs = cstmnt.getResultSet();
                if (rs.next()) {
                    returnModel.setBio(rs.getString("bio"));
                    returnModel.setLocation(rs.getString("location"));
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
}
