package com.irme.server.dal.dao;

import com.irme.common.dto.EvaluationProcessDto;
import com.irme.common.dto.EvaluationReport;
import com.irme.common.dto.EvaluationResult;
import com.irme.common.dto.SACategoryDto;
import com.irme.common.dto.SAProcessAnsweredQuestion;
import com.irme.common.dto.SAQuestionWithAnswers;
import com.irme.server.dal.exceptions.DataAccessLayerException;

import javax.sql.DataSource;

import java.util.List;
import java.util.Optional;

public abstract class SADataAccessObject extends BaseDataAccessObject {

    protected SADataAccessObject(DataSource dataSource) throws Exception {
        super(dataSource);
    }

    public abstract List<SACategoryDto> getCategories() throws DataAccessLayerException;

    public abstract List<SAQuestionWithAnswers> getQuestionsDataByCategory(int categoryId)
            throws DataAccessLayerException;

    /**
     * 
     * @return -1 on fail or evaluation process id from db
     */
    public abstract int createEvaluationProcess(int userId, int organisationId, int categoryId)
            throws DataAccessLayerException;

    public abstract List<EvaluationProcessDto> getEvaluationProcesses(int userId, int organisationId)
            throws DataAccessLayerException;

    public abstract boolean putAnswerToQuestion(int questionId, int answerId, int processId)
            throws DataAccessLayerException;

    public abstract boolean removeAnswerFromQuestion(int questionId, int answerId, int processId)
            throws DataAccessLayerException;

    public abstract boolean finalizeEvaluation(int organisationId, int processId)
            throws DataAccessLayerException;

    public abstract boolean finalizeEvaluationForced(int organisationId, int processId)
            throws DataAccessLayerException;

    public abstract List<EvaluationResult> getEvaluationsResults(int organisationId, int categoryId)
            throws DataAccessLayerException;

    public abstract List<EvaluationResult> getEvaluationsResults(int organisationId)
            throws DataAccessLayerException;

    public abstract Optional<EvaluationReport> getEvaluationReport(int processId)
            throws DataAccessLayerException;

    public abstract Optional<List<SAProcessAnsweredQuestion>> getProcessAnsweredQuestions(int processId)
            throws DataAccessLayerException;

}
