package com.kwetter.followservice.rabbitMQ.models;


import com.kwetter.followservice.models.Account;

import java.util.ArrayList;
import java.util.List;

public class RabbitReturnModel {
    private List<Account> accounts = new ArrayList();

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public List<Account> getAccounts() {
        return accounts;
    }
}
