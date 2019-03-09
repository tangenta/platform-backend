package com.tangenta.types;

import com.tangenta.data.pojo.User;

public class LoginPayload {
    private final User user;
    private final String token;

    public LoginPayload(User user, String token) {
        this.user = user;
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public String getToken() {
        return token;
    }
}
