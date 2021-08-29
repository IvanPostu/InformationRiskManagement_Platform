package com.irme.server.webapp.graphql;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.language.SourceLocation;
import lombok.Getter;

public class GraphQLDomainError extends RuntimeException implements GraphQLError {

    @Getter
    private final Integer errorCode;

    public GraphQLDomainError(GraphQLDomainErrorStatusCode statusCode) {
        super(statusCode.toString());
        this.errorCode = statusCode.ordinal();
    }

    public GraphQLDomainError(String message, GraphQLDomainErrorStatusCode statusCode) {
        super(message);
        this.errorCode = statusCode.ordinal();
    }

    @Override
    public List<SourceLocation> getLocations() {
        return new ArrayList<>(0);
    }

    @Override
    public ErrorType getErrorType() {
        return null;
    }

    @Override
    public Map<String, Object> getExtensions() {
        Map<String, Object> customAttributes = new LinkedHashMap<>();

        customAttributes.put("errorCode", this.errorCode);
        customAttributes.put("errorMessage", this.getMessage());

        return customAttributes;
    }
}
