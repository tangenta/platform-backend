package com.tangenta.data.service;

import com.tangenta.data.pojo.User;
import com.tangenta.exceptions.BusinessException;
import com.tangenta.repositories.UserRepository;
import com.tangenta.types.LoginPayload;
import com.tangenta.utils.Utils;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginService {
    private final UserRepository userRepository;
    private final AuthenticationService authenticationService;

    public LoginService(UserRepository userRepository, AuthenticationService authenticationService) {
        this.userRepository = userRepository;
        this.authenticationService = authenticationService;
    }

    public LoginPayload login(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new BusinessException("找不到该用户名");
        }
        if (!user.getPassword().equals(password)) {
            throw new BusinessException("密码不正确");
        }
        if (authenticationService.hasLoggedIn(user.getStudentId())) {
            throw new BusinessException("该用户已经登录");
        }
        String token = authenticationService.allocateToken(user.getStudentId());
        return new LoginPayload(user, token);
    }

    public boolean logout(Long studentId, DataFetchingEnvironment env) {
        Optional<String> oToken = Utils.getAuthToken(env);
        return oToken.map(token -> authenticationService.logout(studentId, token)).orElse(false);
    }
}
