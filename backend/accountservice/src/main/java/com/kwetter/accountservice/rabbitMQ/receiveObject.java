package com.kwetter.accountservice.rabbitMQ;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class receiveObject implements Serializable {
    private List<Integer> userids = new ArrayList();
    private String queue_name = "";
    private String token = "";

    public List<Integer> getUserids() {
        return userids;
    }
    public void addUserids(int userid) {
        userids.add(userid);
    }

    public String getQueue_name() {
        return queue_name;
    }

    public void setToken(String token) {
        this.token = token;
    }
    public String getToken() {
        return token;
    }
}
