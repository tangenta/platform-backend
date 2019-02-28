package com.tangenta.types;

import java.util.Date;

public class User {
    private final String username;
    private final String password;
    private final String email;
    private final Date creationDate;

    public User(String username, String password, String email, Date date) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.creationDate = date;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getCreationDate() {
        return creationDate.toString();
    }
}
