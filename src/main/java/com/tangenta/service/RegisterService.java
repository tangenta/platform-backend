package com.tangenta.service;

import com.tangenta.data.pojo.User;
import com.tangenta.exceptions.BusinessException;
import com.tangenta.repositories.UserRepository;
import com.tangenta.utils.generator.IdGenerator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Service
public class RegisterService {
    @Value("${register-link-with-param}")
    private String registerLink;

    // TODO: clear register tokens in a time period
    private static ConcurrentMap<String, User> registeringGuys = new ConcurrentHashMap<>();
    private UserRepository userRepository;
    private MailService mailService;
    private IdGenerator studentIdGenerator;

    public RegisterService(UserRepository userRepository, MailService mailService,
                           @Qualifier("studentIdGenerator") IdGenerator studentIdGenerator) {
        this.userRepository = userRepository;
        this.mailService = mailService;
        this.studentIdGenerator = studentIdGenerator;
    }

    public void beginRegisterProcess(String username, String password, String email) {
        User fakeUser = new User(-1L, username, password, email, "not-set");
        String token = UUID.randomUUID().toString();
        try {
            mailService.send_email(email, registerLink + token);
        } catch (MessagingException e) {
            e.printStackTrace();
            throw new BusinessException("邮件发送失败");
        }
        registeringGuys.put(token, fakeUser);
    }

    public boolean validateRegisterToken(String token) {
        if (!registerTokenIsValid(token)) return false;

        User fakeUser = registeringGuys.get(token);
        User realUser = new User(studentIdGenerator.generateId(), fakeUser.getUsername(),
                fakeUser.getPassword(), fakeUser.getEmail(), "");

        userRepository.createUser(realUser);
        registeringGuys.remove(token);
        return true;
    }

    private boolean registerTokenIsValid(String token) {
        return registeringGuys.containsKey(token);
    }
}
