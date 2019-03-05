package com.tangenta.data.service;

import com.tangenta.data.pojo.User;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class SecurityService {
    private AuthenticationService authenticationService;

    public SecurityService(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    public User filterUserByToken(User user, String token) {
        boolean isAuthenticated = authenticationService.authenticate(user.getStudentId(), token);
        if (isAuthenticated) return user;
        else {
            return new User(user.getStudentId(), user.getUsername(), "", "", "");
        }
    }

    public List<User> filterUsersByToken(List<User> users, String token) {
        List<User> resultUsers = new LinkedList<>();
        users.forEach(user -> resultUsers.add(filterUserByToken(user, token)));
        return resultUsers;
    }
}
