package com.irme.server.business_entities;

import com.irme.common.dto.EvaluationProcessDto;
import com.irme.common.dto.EvaluationReport;
import com.irme.common.dto.EvaluationResult;
import com.irme.common.dto.SACategoryDto;
import com.irme.common.dto.SAProcessAnsweredQuestion;
import com.irme.common.dto.SAQuestionWithAnswers;
import com.irme.server.dal.DataAccessObjectFactory;
import com.irme.server.dal.dao.SADataAccessObject;
import com.irme.server.dal.dao.SADataAccessObjectImpl;
import com.irme.server.dal.exceptions.DataAccessLayerException;
import com.irme.server.utils.ReportWeightUtils;
import lombok.extern.slf4j.Slf4j;

import javax.sql.DataSource;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
public class SABusinessLogic implements BusinessLogicEntity {
    private SADataAccessObject sADataAccessObject;

    public SABusinessLogic(DataSource dataSource) {
        DataAccessObjectFactory daoFactory = new DataAccessObjectFactory(dataSource);

        sADataAccessObject = daoFactory.createDataAccessObject(SADataAccessObjectImpl.class);
    }

    public List<SACategoryDto> getCategories() {
        List<SACategoryDto> result;

        try {
            result = sADataAccessObject.getCategories();
        } catch (DataAccessLayerException e) {
            log.error(e.getMessage());
            result = new ArrayList<>();
        }
        return result;
    }

    public List<SAQuestionWithAnswers> getQuestionsDataByCategory(int categoryId) {
        try {
            return sADataAccessObject.getQuestionsDataByCategory(categoryId);
        } catch (DataAccessLayerException e) {
            log.error(e.getMessage());
            return new ArrayList<>();
        }
    }

    public int createEvaluationProcess(int userId, int organisationId, int categoryId) {
        try {
            return sADataAccessObject.createEvaluationProcess(userId, organisationId, categoryId);
        } catch (DataAccessLayerException e) {
            log.error(e.getMessage());
            return -1;
        }
    }

    public List<EvaluationProcessDto> getEvaluationProcesses(Optional<Integer> userId,
            Optional<Integer> organisationId) {
        try {
            return sADataAccessObject.getEvaluationProcesses(userId, organisationId);
        } catch (DataAccessLayerException e) {
            log.error(e.getMessage());
            return new ArrayList<>();
        }
    }

    public boolean putAnswerToQuestion(int questionId, int answerId, int processId) {
        try {
            return sADataAccessObject.putAnswerToQuestion(questionId, answerId, processId);
        } catch (DataAccessLayerException e) {
            log.error(e.getMessage());
            return false;
        }
    }

    public boolean removeAnswerFromQuestion(int questionId, int answerId, int processId) {
        try {
            return sADataAccessObject.removeAnswerFromQuestion(questionId, answerId, processId);
        } catch (DataAccessLayerException e) {
            log.error(e.getMessage());
            return false;
        }
    }

    public boolean finalizeEvaluation(int organisationId, int processId) {
        try {
            return sADataAccessObject.finalizeEvaluation(organisationId, processId);
        } catch (DataAccessLayerException e) {
            log.error(e.getMessage());
            return false;
        }
    }

    public boolean finalizeEvaluationForced(int organisationId, int processId) {
        try {
            return sADataAccessObject.finalizeEvaluationForced(organisationId, processId);
        } catch (DataAccessLayerException e) {
            log.error(e.getMessage());
            return false;
        }
    }

    public List<EvaluationResult> getEvaluationsResults(int organisationId, Optional<Integer> categoryId,
            Optional<Integer> limitsPerCategory) {
        try {

            List<EvaluationResult> evaluationResults = sADataAccessObject
                    .getEvaluationsResults(organisationId, categoryId, limitsPerCategory);

            evaluationResults.forEach(r -> {
                int expectedWeight = ReportWeightUtils
                        .RandomByValue(r.getAnswerMaxWeight(), r.getAnswerTotalWeight());

                r.setAnswerExpectedWeight(expectedWeight);
            });

            return evaluationResults;
        } catch (DataAccessLayerException e) {
            log.error(e.getMessage());
            return new ArrayList<>();
        }
    }

    public Optional<EvaluationReport> getEvaluationReport(int processId) {
        try {
            Optional<EvaluationReport> result = sADataAccessObject.getEvaluationReport(processId);

            if (result.isPresent()) {
                EvaluationReport r = result.get();
                int expectedWeight = ReportWeightUtils
                        .RandomByValue(r.getMaxCategoryWeight(), r.getTotalProcessWeight());

                r.setExpectedProcessWeight(expectedWeight);
            }

            return result;
        } catch (DataAccessLayerException e) {
            log.error(e.getMessage());
            return Optional.ofNullable(null);
        }
    }

    public Optional<List<SAProcessAnsweredQuestion>> getProcessAnsweredQuestions(int processId) {
        try {
            return sADataAccessObject.getProcessAnsweredQuestions(processId);
        } catch (DataAccessLayerException e) {
            log.error(e.getMessage());
            return Optional.ofNullable(null);
        }
    }

}
