package com.irme.server.dal.dao;

import com.irme.common.dto.EvaluationProcessDto;
import com.irme.common.dto.EvaluationReport;
import com.irme.common.dto.EvaluationResult;
import com.irme.common.dto.SACategoryDto;
import com.irme.common.dto.SAQuestionWithAnswers;
import com.irme.server.dal.exceptions.DataAccessLayerException;

import javax.sql.DataSource;

import java.util.List;

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
    public abstract int createEvaluationProcess();

    public abstract List<EvaluationProcessDto> getEvaluationProcesses(int userId, int organisationId);

    public abstract boolean putAnswerToQuestion(int questionId, int answerId, int processId);

    public abstract boolean removeAnswerFromQuestion(int questionId, int answerId, int processId);

    public abstract boolean finalizeEvaluation(int organisationId, int processId);

    public abstract boolean finalizeEvaluationForced(int organisationId, int processId);

    public abstract List<EvaluationResult> getEvaluationsResults(int organisationId);

    public abstract List<EvaluationReport> getEvaluationReport(int processId);

}
