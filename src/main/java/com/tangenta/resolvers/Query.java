package com.tangenta.resolvers;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.tangenta.repositories.UserRepository;
import com.tangenta.types.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Query implements GraphQLQueryResolver {

    private final UserRepository mUserRepository;

    public Query(UserRepository mUserRepository) {
        this.mUserRepository = mUserRepository;
    }

    public List<User> users() {
        return mUserRepository.getAllUsers();
    }

}
