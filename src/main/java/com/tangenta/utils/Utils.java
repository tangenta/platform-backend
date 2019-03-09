package com.tangenta.utils;

import graphql.schema.DataFetchingEnvironment;
import graphql.servlet.GraphQLContext;

import java.util.Optional;

public class Utils {
    public static Optional<String> getAuthToken(DataFetchingEnvironment env) {
        GraphQLContext graphQLContext = env.getContext();
        return graphQLContext.getHttpServletRequest().map(req -> req.getHeader("Authorization"));
    }

    public static <T, U> T mapToSameIndex(U[] src, U obj, T[] dest) {
        assert src.length == dest.length;
        for (int i = 0; i != src.length; ++i) {
            if (src[i].equals(obj)) {
                return dest[i];
            }
        }
        throw new RuntimeException("impossible to reach here");
    }
}
