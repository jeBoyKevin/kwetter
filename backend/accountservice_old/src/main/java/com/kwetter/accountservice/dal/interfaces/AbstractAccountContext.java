package com.kwetter.accountservice.dal.interfaces;


import com.kwetter.accountservice.models.returnModels.AccountReturnModel;

public abstract class AbstractAccountContext {

    public abstract AccountReturnModel register(String username, String password);
    public abstract AccountReturnModel login(String username, String password);

}
