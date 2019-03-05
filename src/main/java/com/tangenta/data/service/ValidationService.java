package com.tangenta.data.service;

import com.tangenta.data.pojo.User;
import com.tangenta.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ValidationService {
    private static Logger logger = LoggerFactory.getLogger(ValidationService.class);
    private UserRepository userRepository;

    public ValidationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean isDuplicateUsername(String username) {
        User u1 = userRepository.findByUsername(username);
        logger.info("username " + username + (u1 == null ? "not exists" : "exist"));
        return u1 != null;
    }

    public boolean isDuplicateEmail(String email) {
        User u2 = userRepository.findByEmail(email);
        return u2 != null;
    }
}
