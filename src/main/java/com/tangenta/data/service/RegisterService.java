package com.tangenta.data.service;

import com.tangenta.data.pojo.User;
import com.tangenta.repositories.UserRepository;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Service
public class RegisterService {
    private static final String REGISTER_LINK = "http://localhost:4000/register?token=";
    private static ConcurrentMap<String, User> registeringGuys = new ConcurrentHashMap<>();
    private UserRepository userRepository;
    private MailService mailService;

    public RegisterService(UserRepository userRepository, MailService mailService) {
        this.userRepository = userRepository;
        this.mailService = mailService;
    }

    public String beginRegisterProcess(String username, String password, String email)
        throws MessagingException {
        User fakeUser = new User(-1L, username, password, email, "not-set");
        String token = UUID.randomUUID().toString();
        mailService.send_email(email, REGISTER_LINK + token);
        registeringGuys.put(token, fakeUser);
        return token;
    }

    public boolean registerTokenIsValid(String token) {
        return registeringGuys.containsKey(token);
    }

    public boolean validateRegisterToken(String token) {
        if (!registeringGuys.containsKey(token)) return false;
        userRepository.createUser(registeringGuys.get(token));
        registeringGuys.remove(token);
        return true;
    }
}
