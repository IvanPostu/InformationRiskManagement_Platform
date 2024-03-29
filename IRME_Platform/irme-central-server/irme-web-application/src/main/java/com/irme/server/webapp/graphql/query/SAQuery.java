package com.irme.server.webapp.graphql.query;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.irme.common.dto.EvaluationProcessDto;
import com.irme.common.dto.EvaluationReport;
import com.irme.common.dto.EvaluationResult;
import com.irme.common.dto.SACategoryDto;
import com.irme.common.dto.SAProcessAnsweredQuestion;
import com.irme.common.dto.SAQuestionWithAnswers;
import com.irme.server.business_entities.SABusinessLogic;
import com.irme.server.webapp.graphql.model.SACategoryResult;
import com.irme.server.webapp.jwt.JwtUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class SAQuery implements GraphQLQueryResolver {

    @Autowired
    private SABusinessLogic sABusinessLogic;

    @PreAuthorize("!isAnonymous()")
    public List<SACategoryResult> getSecurityAssessmentCategories() {
        List<SACategoryResult> categories = new ArrayList<>();

        List<SACategoryDto> categoryDtos = sABusinessLogic.getCategories();

        if (categoryDtos != null && categoryDtos.size() > 0) {
            categoryDtos.forEach(c -> {
                SACategoryResult item = new SACategoryResult();
                item.setCategroyId(c.getCategroyId());
                item.setDescription(c.getDescription());
                item.setName(c.getName());
                item.setImageUrl(c.getImageUrl());
                categories.add(item);
            });
        }

        return categories;
    }

    @PreAuthorize("!isAnonymous()")
    public List<SAQuestionWithAnswers> getSecurityAssessmentQuestionsDataByCategory(int categoryId) {
        List<SAQuestionWithAnswers> questions = sABusinessLogic.getQuestionsDataByCategory(categoryId);
        return questions;
    }

    @PreAuthorize("!isAnonymous()")
    public List<EvaluationProcessDto> getEvaluationProcesses(Optional<Integer> organisationId) {
        AbstractAuthenticationToken auth = (AbstractAuthenticationToken) SecurityContextHolder.getContext()
                .getAuthentication();
        JwtUser user = (JwtUser) auth.getPrincipal();
        int userId = user.getId();

        return sABusinessLogic.getEvaluationProcesses(Optional.of(userId), organisationId);
    }

    @PreAuthorize("!isAnonymous()")
    public List<EvaluationResult> getEvaluationsResults(int organisationId, Optional<Integer> categoryId,
            Optional<Integer> limitsPerCategory) {
        List<EvaluationResult> result;

        result = sABusinessLogic.getEvaluationsResults(organisationId, categoryId, limitsPerCategory);

        return result;
    }

    @PreAuthorize("!isAnonymous()")
    public Optional<EvaluationReport> getEvaluationReport(int processId) {
        Optional<EvaluationReport> result = sABusinessLogic.getEvaluationReport(processId);
        return result;
    }

    @PreAuthorize("!isAnonymous()")
    public Optional<List<SAProcessAnsweredQuestion>> getProcessAnsweredQuestions(int processId) {
        Optional<List<SAProcessAnsweredQuestion>> result = sABusinessLogic.getProcessAnsweredQuestions(processId);
        return result;
    }
}
