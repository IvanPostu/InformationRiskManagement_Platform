package com.irme.server.webapp.graphql.query;

import java.util.Optional;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.irme.server.dal.UserDAL;
import com.irme.server.webapp.graphql.model.SuccessAuthResult;
import com.irme.server.webapp.jwt.JWTTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

@Component
public class AuthQuery implements GraphQLQueryResolver {


    @Autowired
    private UserDAL userDAL;

    @Autowired
    private JWTTokenProvider tokenProvider;

    @PreAuthorize("isAnonymous()")
    public Optional<SuccessAuthResult> authUser(final String email, final String password) {
        SuccessAuthResult result = new SuccessAuthResult();

        userDAL.selectUserByEmail(email).ifPresent(u -> {
            String token = tokenProvider.createToken(email, u.getRoles());

            result.setEmail(email);
            result.setToken(token);
        });

        return Optional.ofNullable(result);
    }
}
