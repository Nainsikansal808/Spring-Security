package com.demo.jwt.entity;

import com.demo.jwt.model.qdo.AccountQdo;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "login")
public class LoginEntity extends BaseEntity{

    @Column(name = "user_name")
    private String userName;

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getUserName() {
        return userName;
    }

    @Column(name = "password")
    private String password;

    public LoginEntity(String email, String password, String userName) {
        this.email = email;
        this.password = password;
        this.userName = userName;
    }

    @Column(name = "email")
    private String email;

    public LoginEntity(AccountQdo accountQdo) {

        this.userName = accountQdo.getUsername();
        this.password = accountQdo.getPassword();
        this.email = accountQdo.getEmail();

    }
    public LoginEntity() {
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
