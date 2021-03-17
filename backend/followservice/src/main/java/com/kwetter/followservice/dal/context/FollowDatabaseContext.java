package com.kwetter.followservice.dal.context;

import com.kwetter.followservice.dal.interfaces.AbstractFollowContext;
import com.kwetter.followservice.models.returnModels.SendFollowReturnModel;
import com.kwetter.followservice.models.returnModels.GetFollowedReturnModel;
import com.kwetter.followservice.models.returnModels.GetFollowersReturnModel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class FollowDatabaseContext extends AbstractFollowContext {

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

    public SendFollowReturnModel followUser(int user_id, int followed_user_id) {
        SendFollowReturnModel returnModel = new SendFollowReturnModel();
        try {
            if (con == null || con.isClosed()) {
                prepareDatabase();
            }
            PreparedStatement stmt = con.prepareStatement("INSERT INTO `follow` (`user_id`, `followed_user_id`) VALUES (?,?)");
            stmt.setInt(1, user_id);
            stmt.setInt(2, followed_user_id);

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
    public GetFollowersReturnModel getFollowers(int user_id) {
        GetFollowersReturnModel returnModel = new GetFollowersReturnModel();
        try {
            if (con == null || con.isClosed()) {
                prepareDatabase();
            }
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM `follow` WHERE `followed_user_id` = ?");
            stmt.setInt(1, user_id);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                returnModel.addFollower(rs.getInt("user_id"));
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
    public GetFollowedReturnModel getFollowed(int user_id) {
        GetFollowedReturnModel returnModel = new GetFollowedReturnModel();
        try {
            if (con == null || con.isClosed()) {
                prepareDatabase();
            }
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM `follow` WHERE `user_id` = ?");
            stmt.setInt(1, user_id);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                returnModel.addFollowed(rs.getInt("followed_user_id"));
            }

            con.close();
            returnModel.setSuccess(true);
        } catch (SQLException e) {
            returnModel.setErrorMessage(e.getMessage());
            returnModel.setSuccess(false);
        }
        return returnModel;
    }
}
