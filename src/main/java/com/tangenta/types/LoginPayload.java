package com.tangenta.types;

public class LoginPayload implements LoginResult {
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
