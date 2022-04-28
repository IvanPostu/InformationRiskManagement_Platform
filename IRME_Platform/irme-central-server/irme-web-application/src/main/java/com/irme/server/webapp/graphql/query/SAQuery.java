package com.irme.server.webapp.graphql.query;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.irme.common.dto.EvaluationProcessDto;
import com.irme.common.dto.SACategoryDto;
import com.irme.common.dto.SAQuestionWithAnswers;
import com.irme.server.business_entities.SABusinessLogic;
import com.irme.server.webapp.graphql.model.SAAnswerResult;
import com.irme.server.webapp.graphql.model.SACategoryResult;
import com.irme.server.webapp.graphql.model.SAQuestionDataResult;
import com.irme.server.webapp.jwt.JwtUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    public List<SAQuestionDataResult> getSecurityAssessmentQuestionsDataByCategory(int categoryId) {
        List<SAQuestionWithAnswers> questions = sABusinessLogic.getQuestionsDataByCategory(categoryId);
        List<SAQuestionDataResult> questionDataResults;

        questionDataResults = questions
                .stream()
                .map(q -> {
                    List<SAAnswerResult> answers = q.getAnswers()
                            .stream()
                            .map(a -> new SAAnswerResult(a.getAnswerId(), a.getAnswer()))
                            .collect(Collectors.toList());

                    SAQuestionDataResult questionData = SAQuestionDataResult.builder()
                            .questionId(q.getQuestionId())
                            .question(q.getQuestion())
                            .parentQuestionId(q.getParentQuestionId())
                            .answers(answers)
                            .hasMultipleAnswers(q.isHasMultipleAnswers())
                            .questionWeight(q.getQuestionWeight())
                            .build();

                    return questionData;
                })
                .collect(Collectors.toList());

        return questionDataResults;
    }

    @PreAuthorize("!isAnonymous()")
    public List<EvaluationProcessDto> getEvaluationProcesses(int organisationId) {
        AbstractAuthenticationToken auth = (AbstractAuthenticationToken) SecurityContextHolder.getContext()
                .getAuthentication();
        JwtUser user = (JwtUser) auth.getPrincipal();
        int userId = user.getId();

        return sABusinessLogic.getEvaluationProcesses(userId, organisationId);
    }
}
