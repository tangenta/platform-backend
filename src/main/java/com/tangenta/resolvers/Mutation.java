package com.tangenta.resolvers;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.tangenta.data.service.LoginService;
import com.tangenta.types.LoginResult;
import com.tangenta.utils.Utils;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.stereotype.Component;

@Component
public class Mutation implements GraphQLMutationResolver {
    private LoginService loginService;

    public Mutation(LoginService loginService) {
        this.loginService = loginService;
    }

    public LoginResult login(String username, String password) {
        return loginService.login(username, password);
    }

    public boolean logout(Long studentId, DataFetchingEnvironment env) {
        return loginService.logout(studentId, env);
    }
}
