package com.irme.server.webapp.graphql.query;

import java.util.Optional;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.irme.server.bl.UserBusinessLogic;
import com.irme.server.webapp.graphql.model.SuccessAuthResult;
import com.irme.server.webapp.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

@Component
public class AuthQuery implements GraphQLQueryResolver {

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private UserBusinessLogic userBL;

    @PreAuthorize("isAnonymous()")
    public Optional<SuccessAuthResult> authUser(final String email, final String password) {
        SuccessAuthResult result = new SuccessAuthResult();

        // userBL.getUserByEmail(email);
        // userAccessObject.selectUserByEmail(email).ifPresent(u -> {
        //     String token = tokenProvider.createToken(email, u.getRoles());

        //     result.setEmail(email);
        //     result.setToken(token);
        // });

        return Optional.ofNullable(result);
    }
}
