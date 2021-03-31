package com.kwetter.accountservice.manager;

import com.kwetter.accountservice.dal.repository.AccountRepository;
import com.kwetter.accountservice.models.returnModels.AccountReturnModel;

public class AccountManager {

    private AccountRepository tweetRepo = new AccountRepository();

    public AccountReturnModel register(String username, String password) {
        return tweetRepo.register(username, password);
    }
    public AccountReturnModel login(String username, String password) {
        return tweetRepo.login(username, password);
    }

}
