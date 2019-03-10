package com.tangenta.service;

import com.tangenta.data.pojo.User;
import com.tangenta.data.pojo.mybatis.MQuestion;
import com.tangenta.exceptions.BusinessException;
import com.tangenta.repositories.QuestionRepository;
import com.tangenta.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

@Service
public class ValidationService {
    private static Logger logger = LoggerFactory.getLogger(ValidationService.class);
    private UserRepository userRepository;
    private QuestionRepository questionRepository;

    public ValidationService(UserRepository userRepository, QuestionRepository questionRepository) {
        this.userRepository = userRepository;
        this.questionRepository = questionRepository;
    }

    public void ensureValidEmailAddress(String email) {
        try {
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
        } catch (AddressException ex) {
            throw new BusinessException("邮箱地址无效");
        }
    }

    public void ensureNoDuplication(String username, String email) {
        if (isDuplicateUsername(username)) throw new BusinessException("用户名已存在");
        if (isDuplicateEmail(email)) throw new BusinessException("该邮箱已注册");
    }

    private boolean isDuplicateUsername(String username) {
        User u1 = userRepository.findByUsername(username);
        logger.info("username " + username + (u1 == null ? " not exists" : "exist"));
        return u1 != null;
    }

    private boolean isDuplicateEmail(String email) {
        User u2 = userRepository.findByEmail(email);
        return u2 != null;
    }

    public void ensureQuestionExistence(Long questionId) {
        MQuestion q = questionRepository.findQuestionById(questionId);
        if (q == null) throw new BusinessException("该题目不存在");
    }

    public void ensureNonEmptyString(String obj, String itemDescription) {
        if (obj.isEmpty()) throw new BusinessException(itemDescription + "不能为空");
    }

    public void ensureUserExistence(String username) {
        if (userRepository.findByUsername(username) == null) throw new BusinessException("找不到该用户名");
    }
}
