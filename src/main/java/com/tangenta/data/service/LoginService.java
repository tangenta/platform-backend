package com.tangenta.data.service;

import com.tangenta.data.pojo.User;
import com.tangenta.repositories.UserRepository;
import com.tangenta.types.ErrorContainer;
import com.tangenta.types.LoginResult;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class LoginService {
    private final UserRepository mUserRepository;

    public LoginService(UserRepository userRepository) {
        mUserRepository = userRepository;
    }

    LoginResult login(String username, String password) {
        User user = mUserRepository.findUserByUsername(username);
        System.out.println(user);
        if (user == null) {
            List<String> messages = new LinkedList<>();
            messages.add("找不到该用户名");
            return new ErrorContainer(messages);
        }
        // TODO:
    }
}
