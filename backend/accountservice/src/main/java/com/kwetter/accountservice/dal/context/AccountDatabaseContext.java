package com.kwetter.accountservice.dal.context;

import com.kwetter.accountservice.dal.interfaces.AbstractAccountContext;
import com.kwetter.accountservice.models.returnModels.AccountReturnModel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class AccountDatabaseContext extends AbstractAccountContext {

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


    @Override
    public AccountReturnModel register(String username, String password) {
        AccountReturnModel returnModel = new AccountReturnModel();

        try {
            if (con == null || con.isClosed()) {
                prepareDatabase();
            }
            PreparedStatement stmt = con.prepareStatement("INSERT INTO `user` (`username`, `password`) VALUES (?,?)", 1);
            stmt.setString(1, username);
            stmt.setString(2, password);

            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    returnModel.setId(Math.toIntExact(generatedKeys.getLong(1)));
                    returnModel.setSuccess(true);
                }
                else {
                    returnModel.setSuccess(false);
                    returnModel.setError("Creating user failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            returnModel.setError(e.getMessage());
            returnModel.setSuccess(false);
        }
        return returnModel;
    }

    @Override
    public AccountReturnModel login(String username, String password) {
        AccountReturnModel returnModel = new AccountReturnModel();

        try {
            if (con == null || con.isClosed()) {
                prepareDatabase();
            }
            PreparedStatement stmt = con.prepareStatement("SELECT * from `user` WHERE `username` = ? AND `password` = ?");
            stmt.setString(1, username);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                returnModel.setSuccess(true);
                returnModel.setId(rs.getInt("id"));
            }
            else {
                returnModel.setError("Username or Password is invalid");
                returnModel.setSuccess(false);
            }
        } catch (SQLException e) {
            returnModel.setError(e.getMessage());
            returnModel.setSuccess(false);
        }
        return returnModel;
    }
}
