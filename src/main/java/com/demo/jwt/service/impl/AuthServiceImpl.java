package com.demo.jwt.service.impl;

import com.demo.jwt.entity.LoginEntity;
import com.demo.jwt.exception.AuthenticationException;
import com.demo.jwt.model.qdo.AccountQdo;
import com.demo.jwt.repository.LoginRepository;
import com.demo.jwt.service.interfaces.AuthService;
import com.demo.jwt.utilities.ErrorMessages;
import com.demo.jwt.utilities.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private LoginRepository loginRepository;

    @Override
    public String login(String username, String password) {

        LoginEntity loginEntity = loginRepository.findByUserNameAndPassword(username,password).orElseThrow(()->new AuthenticationException(ErrorMessages.LOGIN_DETAILS_NOT_FOUND));
        return JwtUtil.generateToken(username);

    }

    @Override
    public void signUp(AccountQdo accountQdo) {

        LoginEntity loginEntity = new LoginEntity(accountQdo);

        loginRepository.save(loginEntity);

    }
}
