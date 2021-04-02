package com.kwetter.accountservice.dal.repository;


import com.kwetter.accountservice.dal.context.AccountDatabaseContext;
import com.kwetter.accountservice.dal.interfaces.AbstractAccountContext;
import com.kwetter.accountservice.models.returnModels.AccountReturnModel;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

@Repository
public class AccountRepository implements UserDetailsService {
    private static AbstractAccountContext tweetContext;

    public AccountRepository() {this.tweetContext = new AccountDatabaseContext();
    }

    public AccountReturnModel register(String username, String password) {
        AccountReturnModel returnModel = tweetContext.register(username, password);
        returnModel.setToken();
    }
    public AccountReturnModel login(String username, String password) {
        return tweetContext.login(username, password);
    }


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return null;
    }
}
