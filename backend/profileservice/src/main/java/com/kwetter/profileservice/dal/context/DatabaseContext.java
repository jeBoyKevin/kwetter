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

    private Connection con;

    private void prepareDatabase() {
        Properties props = new Properties();
        FileInputStream in = null;
        try {
            in = new FileInputStream("./db.properties");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            props.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String driver = props.getProperty("DB_DRIVER_CLASS");
        if (driver != null) {
            try {
                Class.forName(driver);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        String url = props.getProperty("DB_URL");
        String username = props.getProperty("DB_USERNAME");
        String password = props.getProperty("DB_PASSWORD");

        try {
            con = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public GetProfileReturnModel getProfile(int user_id) {
        GetProfileReturnModel returnModel = new GetProfileReturnModel();
        try {
            if (con == null || con.isClosed()) {
                prepareDatabase();
            }
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM `profile` where  `user_id` = ?");
            stmt.setInt(1, user_id);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()){
                returnModel.setBio(rs.getString("bio"));
                returnModel.setLocation(rs.getString("location"));
                returnModel.setPicture(rs.getString("picture"));
                returnModel.setWebsite(rs.getString("website"));
            }
            con.close();

            returnModel.setSuccess(true);
        } catch (SQLException e) {
            returnModel.setErrorMessage(e.getMessage());
            returnModel.setSuccess(false);
        }
        return returnModel;
    }

    @Override
    public UpdateProfileReturnModel updateProfile(int user_id, String bio, String location, String website) {
        UpdateProfileReturnModel returnModel = new UpdateProfileReturnModel();
        try {
            if (con == null || con.isClosed()) {
                prepareDatabase();
            }
            PreparedStatement stmt = con.prepareStatement("UPDATE `profile` SET `bio` = ?, `location` = ?, `website` = ? WHERE `user_id` = ?");
            stmt.setString(1, bio);
            stmt.setString(2, location);
            stmt.setString(3, website);
            stmt.setInt(4, user_id);

            stmt.execute();
            con.close();

            returnModel.setSuccess(true);
        } catch (SQLException e) {
            returnModel.setErrorMessage(e.getMessage());
            returnModel.setSuccess(false);
        }
        return returnModel;
    }

    @Override
    public UploadPictureReturnModel uploadPicture(int user_id, String picture) {

        UploadPictureReturnModel returnModel = new UploadPictureReturnModel();
        try {
            if (con == null || con.isClosed()) {
                prepareDatabase();
            }
            PreparedStatement stmt = con.prepareStatement("UPDATE `profile` SET `picture` = ? WHERE `user_id` = ?");
            stmt.setString(1, picture);
            stmt.setInt(2, user_id);

            stmt.execute();
            con.close();

            returnModel.setSuccess(true);
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
