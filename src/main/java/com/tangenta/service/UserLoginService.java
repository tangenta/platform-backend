package com.tangenta.service;

import com.tangenta.data.pojo.User;
import com.tangenta.exceptions.BusinessException;
import com.tangenta.repositories.UserRepository;
import com.tangenta.types.LoginPayload;
import com.tangenta.utils.Utils;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserLoginService {
    private final UserRepository userRepository;
    private final AuthenticationService authenticationService;
    private final ValidationService validationService;

    public UserLoginService(UserRepository userRepository, AuthenticationService authenticationService, ValidationService validationService) {
        this.userRepository = userRepository;
        this.authenticationService = authenticationService;
        this.validationService = validationService;
    }

    public LoginPayload login(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new BusinessException("找不到该用户名");
        }
        if (!user.getPassword().equals(password)) {
            throw new BusinessException("密码不正确");
        }
        String token = authenticationService.allocateToken(user.getStudentId());
        return new LoginPayload(user, token);
    }

    public boolean logout(Long studentId, DataFetchingEnvironment env) {
        Optional<String> oToken = Utils.getAuthToken(env);
        return oToken.map(token -> authenticationService.logout(studentId, token)).orElse(false);
    }


    public void changePassword(Long studentId, String password) {
        validationService.ensureUserExistence(studentId);
        User u = userRepository.findById(studentId);
        userRepository.updateUser(new User(studentId, u.getUsername(), password, u.getEmail(), u.getCreationDate()));
    }

    public boolean authMatched(Long studentId, String password) {
        User user = userRepository.findById(studentId);
        return user != null && user.getPassword().equals(password);
    }
}
