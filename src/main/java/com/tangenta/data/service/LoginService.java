package com.tangenta.data.service;

import com.tangenta.data.pojo.User;
import com.tangenta.repositories.UserRepository;
import com.tangenta.types.ErrorContainer;
import com.tangenta.types.LoginPayload;
import com.tangenta.types.LoginResult;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class LoginService {
    private final UserRepository userRepository;
    private final AuthenticationService authenticationService;

    public LoginService(UserRepository userRepository, AuthenticationService authenticationService) {
        this.userRepository = userRepository;
        this.authenticationService = authenticationService;
    }

    public LoginResult login(String username, String password) {
        User user = userRepository.findByUsername(username);
        System.out.println(user);
        if (user == null) {
            List<String> messages = new LinkedList<>();
            messages.add("找不到该用户名");
            return new ErrorContainer(messages);
        }
        if (!user.getPassword().equals(password)) {
            List<String> messages = new LinkedList<>();
            messages.add("密码不正确");
            return new ErrorContainer(messages);
        }
        String token = authenticationService.allocateToken(user.getStudentId());
        return new LoginPayload(user, token);
    }
}
