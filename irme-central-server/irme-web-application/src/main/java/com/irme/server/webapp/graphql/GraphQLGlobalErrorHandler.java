package com.irme.server.webapp.graphql;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;
import graphql.ExceptionWhileDataFetching;
import graphql.GraphQLError;
import graphql.servlet.GraphQLErrorHandler;

@Component
public class GraphQLGlobalErrorHandler implements GraphQLErrorHandler {

    @Override
    public List<GraphQLError> processErrors(List<GraphQLError> list) {
        List<GraphQLError> result =
                list.stream().map(err -> new GraphQLErrorAdapter(processNested(err)))
                        .collect(Collectors.toList());
        return result;
    }

    private static GraphQLDomainError processNested(GraphQLError error) {

        if (error instanceof ExceptionWhileDataFetching) {
            /**
             * Handle the situation with "AccessDeniedException"
             */
            ExceptionWhileDataFetching exceptionError = (ExceptionWhileDataFetching) error;
            Throwable e = exceptionError.getException();

            if (e instanceof AccessDeniedException) {
                return new GraphQLDomainError(exceptionError.getException().getMessage(),
                        GraphQLDomainErrorStatusCode.ACCESS_DENIED);
            }

            /**
             * Handle the situation with specific exception
             */
            if (e instanceof GraphQLDomainError) {
                return (GraphQLDomainError) e;
            }
        }
        /**
         * In any other case return unknown error
         */
        return new GraphQLDomainError(GraphQLDomainErrorStatusCode.UNKNOWN_ERROR);
    }
}
