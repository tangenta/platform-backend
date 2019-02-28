package com.tangenta.repositories.impl;

import com.tangenta.repositories.UserRepository;
import com.tangenta.types.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class MyUserRepository implements UserRepository {

    private static final List<User> users = new ArrayList<>();

    static {
        users.add(new User("tan", "password", "a@test.com",  new Date()));
        users.add(new User("tan2", "password1", "a@test.com", new Date()));
    }

    @Override
    public List<User> getAllUsers() {
        return users;
    }
}
