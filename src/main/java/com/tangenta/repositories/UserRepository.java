package com.tangenta.repositories;

import com.tangenta.data.pojo.User;

import java.util.List;

public interface UserRepository {
    List<User> getAllUsers();
    User findUserByUsername(String username);
}
