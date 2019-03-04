package com.tangenta.data.service;

import com.tangenta.data.pojo.User;
import com.tangenta.repositories.UserRepository;
import com.tangenta.types.ErrorContainer;
import com.tangenta.types.LoginPayload;
import com.tangenta.types.LoginResult;
import com.tangenta.utils.Utils;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

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
        if (authenticationService.hasLoggedIn(user.getStudentId())) {
            List<String> messages = new LinkedList<>();
            messages.add("该用户已经登录");
            return new ErrorContainer(messages);
        }
        String token = authenticationService.allocateToken(user.getStudentId());
        return new LoginPayload(user, token);
    }

    public boolean logout(Long studentId, DataFetchingEnvironment env) {
        Optional<String> oToken = Utils.getAuthToken(env);
        return oToken.map(token -> authenticationService.logout(studentId, token)).orElse(false);
    }
}
