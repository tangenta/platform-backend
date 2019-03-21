package com.tangenta;

import com.tangenta.exceptions.BusinessException;
import graphql.ExceptionWhileDataFetching;
import graphql.GraphQLError;
import graphql.servlet.DefaultGraphQLErrorHandler;
import graphql.servlet.GenericGraphQLError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class MyGraphqlErrorHandler extends DefaultGraphQLErrorHandler {
    private static Logger logger = LoggerFactory.getLogger(MyGraphqlErrorHandler.class);
    @Override
    public List<GraphQLError> processErrors(List<GraphQLError> errors) {
        logger.info("{}", errors);
        List<GraphQLError> customErrors =  errors.stream()
                .filter(e -> e instanceof ExceptionWhileDataFetching)
                .map(e -> ((ExceptionWhileDataFetching)e).getException())
                .filter(e -> e instanceof BusinessException)
                .map(e -> new CustomGenericGraphQLError(e.getMessage()))
                .collect(Collectors.toList());
        if (!customErrors.isEmpty()) return customErrors;
        return super.processErrors(errors);
    }

    class CustomGenericGraphQLError extends GenericGraphQLError {
        CustomGenericGraphQLError(String message) {
            super(message);
        }

        @Override
        public Map<String, Object> getExtensions() {
            Map<String, Object> extMap = new HashMap<String, Object>();
            extMap.put("customError", "");
            return extMap;
        }
    }
}
