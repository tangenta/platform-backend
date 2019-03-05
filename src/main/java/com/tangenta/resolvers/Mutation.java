package com.tangenta.resolvers;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.tangenta.data.pojo.User;
import com.tangenta.data.service.LoginService;
import com.tangenta.data.service.RegisterService;
import com.tangenta.data.service.ValidationService;
import com.tangenta.types.ErrorContainer;
import com.tangenta.types.LoginResult;
import com.tangenta.types.RegisterPayload;
import com.tangenta.types.RegisterResult;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;

@Component
public class Mutation implements GraphQLMutationResolver {
    private LoginService loginService;
    private RegisterService registerService;
    private ValidationService validationService;

    public Mutation(LoginService loginService, RegisterService registerService, ValidationService validationService) {
        this.loginService = loginService;
        this.registerService = registerService;
        this.validationService = validationService;
    }

    public LoginResult login(String username, String password) {
        return loginService.login(username, password);
    }

    public boolean logout(Long studentId, DataFetchingEnvironment env) {
        return loginService.logout(studentId, env);
    }

    public RegisterResult register(String username, String password, String email) {
        if (validationService.isDuplicateUsername(username)) {
            new ErrorContainer("用户名已存在");
        }
        if (validationService.isDuplicateEmail(email)) {
            new ErrorContainer("该邮箱已注册");
        }
        String token;
        try {
            token = registerService.beginRegisterProcess(username, password, email);
        } catch (MessagingException e) {
            e.printStackTrace();
            return new ErrorContainer("邮件发送失败");
        }
        return new RegisterPayload(token);
    }
}
