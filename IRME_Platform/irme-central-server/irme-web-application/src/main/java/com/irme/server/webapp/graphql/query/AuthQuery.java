package com.irme.server.webapp.graphql.query;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.irme.common.dto.AuthUserDto;
import com.irme.server.business_entities.UserBusinessLogic;
import com.irme.server.webapp.graphql.GraphQLDomainError;
import com.irme.server.webapp.graphql.GraphQLDomainErrorStatusCode;
import com.irme.server.webapp.graphql.model.SuccessAuthResult;
import com.irme.server.webapp.jwt.JwtTokenProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
public class AuthQuery implements GraphQLQueryResolver {

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private UserBusinessLogic userBusinessLogic;

    @PreAuthorize("isAnonymous()")
    public Optional<SuccessAuthResult> authUser(String email, String password) {
        SuccessAuthResult result = new SuccessAuthResult();

        AuthUserDto user = userBusinessLogic.getUserByEmail(email);

        if (!user.getPasswordHash().equals(password)) {
            throw new GraphQLDomainError("Password is invalid",
                    GraphQLDomainErrorStatusCode.ACCESS_DENIED);
        }

        String token = tokenProvider.createToken(email, user.getRoles());

        result.setFirstName(user.getFirstName());
        result.setLastName(user.getLastName());
        result.setEmail(email);
        result.setToken(token);

        return Optional.ofNullable(result);
    }

    @PreAuthorize("isAnonymous()")
    public Optional<String> extendToken(String oldToken) {
        Optional<String> result = Optional.ofNullable(null);

        try {
            if (tokenProvider.validateToken(oldToken)) {
                result = tokenProvider.extendToken(oldToken);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new GraphQLDomainError("Token is invalid",
                    GraphQLDomainErrorStatusCode.ACCESS_DENIED);
        }

        return result;
    }
}
