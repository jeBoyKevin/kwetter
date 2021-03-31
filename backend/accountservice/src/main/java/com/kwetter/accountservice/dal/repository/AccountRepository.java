package com.kwetter.accountservice.dal.repository;


import com.kwetter.accountservice.dal.context.AccountDatabaseContext;
import com.kwetter.accountservice.dal.interfaces.AbstractAccountContext;
import com.kwetter.accountservice.models.returnModels.AccountReturnModel;
import org.springframework.stereotype.Repository;

@Repository
public class AccountRepository {
    private static AbstractAccountContext tweetContext;

    public AccountRepository() {this.tweetContext = new AccountDatabaseContext();
    }

    public AccountReturnModel register(String username, String password) {
        return tweetContext.register(username, password);
    }
    public AccountReturnModel login(String username, String password) {
        return tweetContext.login(username, password);
    }


}
