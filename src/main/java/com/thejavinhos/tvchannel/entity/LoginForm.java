package com.thejavinhos.tvchannel.entity;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class LoginForm {

    private String username;
    private String password;


    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UsernamePasswordAuthenticationToken converter() {
        return new UsernamePasswordAuthenticationToken(username, password);
    }
}
