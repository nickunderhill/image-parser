package com.agileengine.imageparser.dto;

public class TokenDto {

    private boolean auth;
    private String token;

    public TokenDto() {
    }

    public boolean isAuth() {
        return auth;
    }

    public void setAuth(boolean auth) {
        this.auth = auth;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}