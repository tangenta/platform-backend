package com.tangenta.types;

public class RegisterPayload implements RegisterResult {
    private String token;

    public RegisterPayload(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
