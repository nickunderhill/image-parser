package com.agileengine.imageparser.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.StringJoiner;

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

    @Override
    public String toString() {
        return new StringJoiner(", ", TokenDto.class.getSimpleName() + "[", "]")
                .add("auth=" + auth)
                .add("token='" + token + "'")
                .toString();
    }
}