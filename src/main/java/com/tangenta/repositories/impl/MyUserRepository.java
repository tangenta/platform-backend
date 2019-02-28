package com.tangenta.repositories.impl;

import com.tangenta.data.mapper.UserMapper;
import com.tangenta.repositories.UserRepository;
import com.tangenta.data.pojo.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MyUserRepository implements UserRepository {
    private final UserMapper mUserMapper;

    public MyUserRepository(UserMapper userMapper) {
        mUserMapper = userMapper;
    }

    @Override
    public List<User> getAllUsers() {
        return mUserMapper.getAllUsers();
    }
}
