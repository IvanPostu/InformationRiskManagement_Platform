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

    @PreAuthorize("!isAnonymous()")
    public Boolean putAnswerToQuestion(int questionId, int answerId, int processId) {
        Boolean isSuccess = saBusinessLogic.putAnswerToQuestion(questionId, answerId, processId);
        return isSuccess;
    }

    @PreAuthorize("!isAnonymous()")
    public Boolean removeAnswerFromQuestion(int questionId, int answerId, int processId) {
        Boolean isSuccess = saBusinessLogic.removeAnswerFromQuestion(questionId, answerId, processId);
        return isSuccess;
    }

    @PreAuthorize("!isAnonymous()")
    public boolean finalizeEvaluation(int organisationId, int processId) {
        boolean isSuccess = saBusinessLogic.finalizeEvaluation(organisationId, processId);
        return isSuccess;
    }

    @PreAuthorize("!isAnonymous()")
    public boolean finalizeEvaluationForced(int organisationId, int processId) {
        boolean isSuccess = saBusinessLogic.finalizeEvaluationForced(organisationId, processId);
        return isSuccess;
    }
}
