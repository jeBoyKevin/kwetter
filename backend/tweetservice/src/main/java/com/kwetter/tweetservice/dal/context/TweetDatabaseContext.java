package com.kwetter.tweetservice.dal.context;

import com.kwetter.tweetservice.dal.interfaces.AbstractTweetContext;
import com.kwetter.tweetservice.models.returnModels.SendTweetReturnModel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class TweetDatabaseContext extends AbstractTweetContext {

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

    public SendTweetReturnModel sendTweet(int user_id, String message) {
        SendTweetReturnModel returnModel = new SendTweetReturnModel();
        try {
            if (con == null || con.isClosed()) {
                prepareDatabase();
            }
            PreparedStatement stmt = con.prepareStatement("INSERT INTO `tweet` (`user_id`, `message`, `likes`) VALUES (?,?,0)");
            stmt.setInt(1, user_id);
            stmt.setString(2, message);

            stmt.execute();

            con.close();

            returnModel.setSuccess(true);
        } catch (SQLException e) {
            returnModel.setErrorMessage(e.getMessage());
            returnModel.setSuccess(false);
        }
        return returnModel;
    }

    public String getMentions() {
        return null;
    }

    public String deleteTweet() {
        return null;
    }

    public String likeTweet() {
        return null;
    }

    public String getTweets() {
        return null;
    }
}
