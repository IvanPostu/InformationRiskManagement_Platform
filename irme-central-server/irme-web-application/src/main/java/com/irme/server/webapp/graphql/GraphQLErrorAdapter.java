package com.irme.server.webapp.graphql;

import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonIgnore;
import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.language.SourceLocation;

public class GraphQLErrorAdapter implements GraphQLError {

    private final GraphQLDomainError graphQLError;

    public GraphQLErrorAdapter(GraphQLDomainError graphQLError) {
        this.graphQLError = graphQLError;
    }

    @Override
    public String getMessage() {
        return this.graphQLError.getMessage();
    }

    public Integer getErrorCode() {
        return graphQLError.getErrorCode();
    }

    @JsonIgnore
    @Override
    public List<SourceLocation> getLocations() {
        return graphQLError.getLocations();
    }

    @JsonIgnore
    @Override
    public ErrorType getErrorType() {
        return graphQLError.getErrorType();
    }

    @JsonIgnore
    @Override
    public Map<String, Object> getExtensions() {
        return graphQLError.getExtensions();
    }

    @JsonIgnore
    @Override
    public List<Object> getPath() {
        return GraphQLError.super.getPath();
    }
}
