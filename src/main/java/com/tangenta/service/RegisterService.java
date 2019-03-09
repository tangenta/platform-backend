package com.tangenta.service;

import com.tangenta.data.pojo.User;
import com.tangenta.exceptions.BusinessException;
import com.tangenta.repositories.UserRepository;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Service
public class RegisterService {
    private static final String REGISTER_LINK = "http://localhost:4000/register?token=";
    // TODO: clear register tokens in a time period
    private static ConcurrentMap<String, User> registeringGuys = new ConcurrentHashMap<>();
    private UserRepository userRepository;
    private MailService mailService;

    public RegisterService(UserRepository userRepository, MailService mailService) {
        this.userRepository = userRepository;
        this.mailService = mailService;
    }

    public void beginRegisterProcess(String username, String password, String email) {
        User fakeUser = new User(-1L, username, password, email, "not-set");
        String token = UUID.randomUUID().toString();
        try {
            mailService.send_email(email, REGISTER_LINK + token);
        } catch (MessagingException e) {
            e.printStackTrace();
            throw new BusinessException("邮件发送失败");
        }
        registeringGuys.put(token, fakeUser);
    }

    public boolean validateRegisterToken(String token) {
        if (!registerTokenIsValid(token)) return false;
        userRepository.createUser(registeringGuys.get(token));
        registeringGuys.remove(token);
        return true;
    }

    private boolean registerTokenIsValid(String token) {
        return registeringGuys.containsKey(token);
    }
}
