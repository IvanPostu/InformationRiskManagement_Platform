package com.irme.server.webapp.graphql.query;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.irme.server.business_entities.SABusinessLogic;
import com.irme.server.webapp.jwt.JwtUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class SAMutation implements GraphQLMutationResolver {

    @Autowired
    private SABusinessLogic saBusinessLogic;

    @PreAuthorize("!isAnonymous()")
    public int createEvaluationProcess(int organisationId, int categoryId) {

        AbstractAuthenticationToken auth = (AbstractAuthenticationToken) SecurityContextHolder.getContext()
                .getAuthentication();
        JwtUser user = (JwtUser) auth.getPrincipal();
        int userId = user.getId();
        int evaluationProcessId = saBusinessLogic.createEvaluationProcess(userId, organisationId, categoryId);

        return evaluationProcessId;
    }
}
