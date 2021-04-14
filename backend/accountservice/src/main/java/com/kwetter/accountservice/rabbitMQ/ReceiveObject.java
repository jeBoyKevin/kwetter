package com.kwetter.accountservice.rabbitMQ;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ReceiveObject implements Serializable {
    private List<Integer> userids = new ArrayList();
    private String queue_name = "";

    public List<Integer> getUserids() {
        return userids;
    }
    public void addUserids(int userid) {
        userids.add(userid);
    }

    public String getQueue_name() {
        return queue_name;
    }

    public void setQueue_name(String queue_name) {
        this.queue_name = queue_name;
    }
}
