package com.tangenta.repositories;

import com.tangenta.types.User;

import java.util.List;

public interface UserRepository {
    List<User> getAllUsers();
}
