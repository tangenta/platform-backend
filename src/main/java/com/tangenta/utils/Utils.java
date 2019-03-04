package com.tangenta.utils;

import graphql.schema.DataFetchingEnvironment;
import graphql.servlet.GraphQLContext;

import java.util.Optional;

public class Utils {
    public static Optional<String> getAuthToken(DataFetchingEnvironment env) {
        GraphQLContext graphQLContext = env.getContext();
        return graphQLContext.getHttpServletRequest().map(req -> req.getHeader("Authorization"));
    }
}
