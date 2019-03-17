package com.tangenta.repositories.impl;

import com.tangenta.data.mapper.UserMapper;
import com.tangenta.repositories.UserRepository;
import com.tangenta.data.pojo.User;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
@Profile("dev")
public class MyUserRepository implements UserRepository {
    private final UserMapper userMapper;

    public MyUserRepository(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public List<User> getAllUsers() {
        return userMapper.getAllUsers();
    }

    @Override
    public User findById(Long studentId) {
        return userMapper.findById(studentId);
    }

    @Override
    public User findByUsername(String username) {
        return userMapper.findByUserName(username);
    }

    @Override
    public User findByEmail(String email) {
        return userMapper.findByEmail(email);
    }

    @Override
    public void createUser(User partialUser) {
        userMapper.createUser(partialUser.getUsername(),
                partialUser.getPassword(),
                partialUser.getEmail(),
                new Date().toString());
    }

}
