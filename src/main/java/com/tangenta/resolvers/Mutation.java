package com.tangenta.resolvers;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.tangenta.types.ErrorContainer;
import com.tangenta.types.LoginResult;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class Mutation implements GraphQLMutationResolver {
    LoginResult login(String username, String password) {
        List<String> errMsg = new LinkedList<>();
        errMsg.add("test1");
        return new ErrorContainer(errMsg);
    }
}
