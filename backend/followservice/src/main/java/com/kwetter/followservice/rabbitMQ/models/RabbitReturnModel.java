package com.kwetter.accountservice.rabbitMQ;

import com.kwetter.accountservice.model.Account;

import java.util.ArrayList;
import java.util.List;

public class sendObject {
    private List<Account> accounts = new ArrayList();

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public List<Account> getAccounts() {
        return accounts;
    }
}
