package com.demo.jwt.service.interfaces;

import com.demo.jwt.model.qdo.AccountQdo;

public interface AuthService {

    String login(String username, String password);

    void signUp(AccountQdo accountQdo);
}
