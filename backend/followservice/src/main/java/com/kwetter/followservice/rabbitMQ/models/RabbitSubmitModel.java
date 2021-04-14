package com.kwetter.followservice.rabbitMQ.models;

import java.util.ArrayList;
import java.util.List;

public class RabbitSubmitModel {
    private List<Integer> userids = new ArrayList();
    private String queue_name = "";

    public void setUserids(List<Integer> userids) {
        this.userids = userids;
    }

    public void setQueue_name(String queue_name) {
        this.queue_name = queue_name;
    }

    public List<Integer> getUserids() {
        return userids;
    }

    public String getQueue_name() {
        return queue_name;
    }
}
