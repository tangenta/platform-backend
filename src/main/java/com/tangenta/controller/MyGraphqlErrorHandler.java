package com.tangenta.controller;

import graphql.GraphQLError;
import graphql.servlet.GraphQLErrorHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GraphqlErrorHandler implements GraphQLErrorHandler {
    Logger logger = LoggerFactory.getLogger(GraphQLErrorHandler.class);

    @Override
    public boolean errorsPresent(List<GraphQLError> errors) {
        return true;
    }

    @Override
    public List<GraphQLError> processErrors(List<GraphQLError> errors) {
        logger.info("processErrors");
        return errors;
    }
}
