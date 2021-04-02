package com.kwetter.tweetservice.dal.context;

import com.kwetter.tweetservice.dal.interfaces.AbstractTweetContext;
import com.kwetter.tweetservice.models.returnModels.SendTweetReturnModel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class TweetDatabaseContext extends AbstractTweetContext {

    private final String connectionUrl = "jdbc:sqlserver://kwetter.database.windows.net:1433;DatabaseName=tweet;user=kevints@kwetter;password=k98p&ewgt^64!wk2;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
    private static Statement statement;

    public SendTweetReturnModel sendTweet(int user_id, String message) {
        SendTweetReturnModel returnModel = new SendTweetReturnModel();
        try (Connection connection = DriverManager.getConnection(connectionUrl)) {
            try {
                CallableStatement cstmnt = connection.prepareCall("{CALL sendTweet(?,?)}");
                cstmnt.setInt(1, user_id);
                cstmnt.setString(2, message);

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
