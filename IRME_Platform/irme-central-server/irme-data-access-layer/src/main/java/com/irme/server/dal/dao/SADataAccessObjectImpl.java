package com.irme.server.dal.dao;

import com.irme.common.dto.EvaluationProcessDto;
import com.irme.common.dto.EvaluationReport;
import com.irme.common.dto.EvaluationResult;
import com.irme.common.dto.SAAnswerDto;
import com.irme.common.dto.SACategoryDto;
import com.irme.common.dto.SAQuestionWithAnswers;
import com.irme.server.dal.exceptions.DataAccessErrorCode;
import com.irme.server.dal.exceptions.DataAccessLayerException;
import org.javatuples.Pair;

import javax.sql.DataSource;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;

public class SADataAccessObjectImpl extends SADataAccessObject {

    public SADataAccessObjectImpl(DataSource dataSource) throws Exception {
        super(dataSource);
    }

    @Override
    public List<SACategoryDto> getCategories() throws DataAccessLayerException {
        String sql = "{ call dbo.sa_categories_get() }";
        List<SACategoryDto> result = new ArrayList<>(30);

        try (CallableStatement statement = super.getConnection().prepareCall(sql)) {
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                SACategoryDto category = new SACategoryDto();
                category.setCategroyId(rs.getInt("category_id"));
                category.setName(rs.getString("name"));
                category.setDescription(rs.getString("description"));
                category.setImageUrl(rs.getString("image_url"));
                result.add(category);
            }

        } catch (SQLException ex) {
            throw new DataAccessLayerException(ex.getMessage(), DataAccessErrorCode.UNKNOWN_ERROR);
        }

        return result;
    }

    @Override
    public List<SAQuestionWithAnswers> getQuestionsDataByCategory(int categoryId) throws DataAccessLayerException {
        String sql = "{ call dbo.sa_get_questions_by_category(?) }";
        List<SAQuestionWithAnswers> result;

        HashMap<Integer, Pair<SAQuestionWithAnswers, String>> questions = new HashMap<>();
        HashMap<Integer, SAAnswerDto> answers = new HashMap<>();
        ResultSet rs;

        try (CallableStatement statement = super.getConnection().prepareCall(sql)) {
            statement.setInt(1, categoryId);
            rs = statement.executeQuery();
            while (rs.next()) {
                SAQuestionWithAnswers questionData = new SAQuestionWithAnswers();

                int questionId = rs.getInt("question_id");
                String answersIds = rs.getString("answers_ids");

                questionData.setAnswers(new ArrayList<>());
                questionData.setHasMultipleAnswers(rs.getInt("has_multiple_answers") == 1);
                questionData.setQuestion(rs.getString("question"));
                questionData.setQuestionId(questionId);
                questionData.setQuestionWeight(rs.getInt("question_max_weight"));

                int parentQuestionId = rs.getInt("question_max_weight");
                questionData.setParentQuestionId(rs.wasNull() ? -1 : parentQuestionId);

                questions.put(questionId, Pair.with(questionData, answersIds));
            }
            rs.close();

            if (!statement.getMoreResults()) {
                throw new SQLException("The second result set is missing or empty");
            }

            rs = statement.getResultSet();
            while (rs.next()) {
                int answerId = rs.getInt("answer_id");
                answers.put(answerId, new SAAnswerDto(answerId, rs.getString("answer")));
            }
            rs.close();

            result = joinAnswersToQuestions(questions, answers);

        } catch (SQLException | IllegalArgumentException ex) {
            throw new DataAccessLayerException(ex.getMessage(),
                    DataAccessErrorCode.UNKNOWN_ERROR);
        }

        return result;
    }

    private List<SAQuestionWithAnswers> joinAnswersToQuestions(
            Map<Integer, Pair<SAQuestionWithAnswers, String>> questions, Map<Integer, SAAnswerDto> answers) {

        try {
            return questions.entrySet()
                    .stream()
                    .collect(Collector.of(ArrayList<SAQuestionWithAnswers>::new,
                            (acc, entry) -> {
                                String joinedAnswersIds = entry.getValue().getValue1();
                                SAQuestionWithAnswers currentQuestion = entry.getValue().getValue0();
                                List<SAAnswerDto> questionAnswers = currentQuestion.getAnswers();

                                if (joinedAnswersIds != null && joinedAnswersIds.length() > 0) {
                                    String[] ids = joinedAnswersIds.split(",");
                                    for (String id : ids) {
                                        int answerId = Integer.parseInt(id);
                                        SAAnswerDto answer = answers.get(answerId);
                                        if (answer != null) {
                                            questionAnswers.add(answer);
                                        }
                                    }
                                }

                                acc.add(currentQuestion);
                            },
                            (m1, m2) -> {
                                return m1;
                            }));

        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * 
     * @return -1 on fail or evaluation process id from db
     */
    @Override
    public int createEvaluationProcess(int userId, int organisationId, int categoryId) throws DataAccessLayerException {

        String sql = "{ call dbo.sa_create_evaluation_process( ?,?,?,? ) }";
        int evaluationPrecessId = -1;

        try (CallableStatement statement = super.getConnection().prepareCall(sql)) {

            statement.setInt(1, organisationId);
            statement.setInt(2, userId);
            statement.setInt(3, categoryId);
            statement.registerOutParameter(4, Types.INTEGER);

            statement.executeUpdate();

            evaluationPrecessId = statement.getInt(4);

            if (evaluationPrecessId == -1) {
                throw new DataAccessLayerException("INSERT_FAILED",
                        DataAccessErrorCode.INSERT_FAILED);
            }

            return evaluationPrecessId;
        } catch (SQLException ex) {
            throw new DataAccessLayerException(ex.getMessage(),
                    DataAccessErrorCode.INSERT_FAILED);
        }
    }

    @Override
    public List<EvaluationProcessDto> getEvaluationProcesses(int userId, int organisationId)
            throws DataAccessLayerException {

        String sql = "{ call dbo.sa_get_evaluation_processes( ?, ? ) }";
        List<EvaluationProcessDto> result = new LinkedList<>();
        ResultSet rs = null;

        try (CallableStatement statement = super.getConnection().prepareCall(sql)) {
            statement.setInt(1, userId);
            statement.setInt(2, organisationId);

            rs = statement.executeQuery();
            while (rs.next()) {
                EvaluationProcessDto processDto = new EvaluationProcessDto();

                processDto.setProcessId(rs.getInt("process_id"));
                processDto.setOrganisationId(rs.getInt("organisation_id"));
                processDto.setOrganisationName(rs.getString("organisation_name"));
                processDto.setCreated(rs.getDate("created"));
                processDto.setUserId(rs.getInt("author_user_id"));
                processDto.setUserEmail(rs.getString("user_email"));
                processDto.setStatusCode(rs.getInt("status"));
                processDto.setCategoryId(rs.getInt("process_id"));
                processDto.setCategoryName(rs.getString("category_name"));

                result.add(processDto);
            }
            rs.close();

        } catch (Exception ex) {
            throw new DataAccessLayerException(ex.getMessage(),
                    DataAccessErrorCode.UNKNOWN_ERROR);
        }

        return result;
    }

    @Override
    public boolean putAnswerToQuestion(int questionId, int answerId, int processId) throws DataAccessLayerException {
        return manipulateWithAnswerToQuestion(questionId, answerId, processId, false);
    }

    @Override
    public boolean removeAnswerFromQuestion(int questionId, int answerId, int processId)
            throws DataAccessLayerException {
        return manipulateWithAnswerToQuestion(questionId, answerId, processId, true);
    }

    @Override
    public boolean finalizeEvaluation(int organisationId, int processId) throws DataAccessLayerException {
        return finalizeEvaluation(organisationId, processId, false);
    }

    @Override
    public boolean finalizeEvaluationForced(int organisationId, int processId) throws DataAccessLayerException {
        return finalizeEvaluation(organisationId, processId, true);
    }

    @Override
    public List<EvaluationResult> getEvaluationsResults(int organisationId, int categoryId)
            throws DataAccessLayerException {

        String sql = "{ call dbo.sa_get_evaluations_results( ?, ? ) }";
        List<EvaluationResult> result = new LinkedList<>();
        ResultSet rs = null;

        try (CallableStatement statement = super.getConnection().prepareCall(sql)) {
            statement.setInt(1, organisationId);
            statement.setInt(2, categoryId);

            rs = statement.executeQuery();
            while (rs.next()) {
                EvaluationResult evaluationResult = new EvaluationResult();

                evaluationResult.setProcessId(rs.getInt("process_id"));
                evaluationResult.setCategoryId(rs.getInt("category_id"));
                evaluationResult.setCreated(rs.getDate("created"));
                evaluationResult.setAnswerTotalWeight(rs.getInt("created"));
                evaluationResult.setAnswerMaxWeight(rs.getInt("created"));
                evaluationResult.setStatusCode(rs.getInt("status"));

                result.add(evaluationResult);
            }
            rs.close();

        } catch (SQLException | IllegalArgumentException ex) {
            throw new DataAccessLayerException(ex.getMessage(),
                    DataAccessErrorCode.UNKNOWN_ERROR);
        }

        return result;
    }

    @Override
    public List<EvaluationResult> getEvaluationsResults(int organisationId)
            throws DataAccessLayerException {

        return getEvaluationsResults(organisationId, -1);
    }

    @Override
    public EvaluationReport getEvaluationReport(int processId) throws DataAccessLayerException {
        String sql = "{ call dbo.sa_get_evaluation_report( ? ) }";
        EvaluationReport report = new EvaluationReport();
        ResultSet rs;

        try (CallableStatement statement = super.getConnection().prepareCall(sql)) {
            statement.setInt(1, processId);
            rs = statement.executeQuery();
            while (rs.next()) {
                EvaluationReport.EvaluationReportItem reportItem = new EvaluationReport.EvaluationReportItem();
                reportItem.setAnswerId(rs.getInt("answer_id"));
                reportItem.setQuestionId(rs.getInt("question_id"));
                reportItem.setQuestion(rs.getString("question"));
                reportItem.setAnswer(rs.getString("answer"));
                reportItem.setDescription(rs.getString("description"));

                report.getItems().add(reportItem);
            }
            rs.close();

            if (!statement.getMoreResults()) {
                throw new SQLException("The second result set is missing or empty");
            }

            rs = statement.getResultSet();
            while (rs.next()) {
                int maxCategoryWeight = rs.getInt("maxc_ategory_weight");
                int totalProcessWeight = rs.getInt("total_process_weight");
                report.setMaxCategoryWeight(maxCategoryWeight);
                report.setTotalProcessWeight(totalProcessWeight);
            }
            rs.close();

        } catch (SQLException | IllegalArgumentException ex) {
            throw new DataAccessLayerException(ex.getMessage(),
                    DataAccessErrorCode.UNKNOWN_ERROR);
        }

        return report;
    }

    /**
     * 
     * 
     * @param questionId
     * @param answerId
     * @param processId
     * @param remove     If is true, remove current question-answer link, otherwise
     *                   lint it
     * @return success or fail
     * @throws DataAccessLayerException
     */
    private boolean manipulateWithAnswerToQuestion(int questionId, int answerId, int processId, boolean remove)
            throws DataAccessLayerException {
        String sql = "{ call dbo.sa_put_answer( ?,?,?,?,? ) }";
        boolean isSuccess = false;

        try (CallableStatement statement = super.getConnection().prepareCall(sql)) {

            statement.setInt(1, questionId);
            statement.setInt(2, answerId);
            statement.setInt(3, processId);
            statement.setBoolean(4, remove);
            statement.registerOutParameter(5, Types.BIT);

            statement.executeUpdate();

            isSuccess = statement.getBoolean(5);

        } catch (SQLException ex) {
            throw new DataAccessLayerException(ex.getMessage(),
                    DataAccessErrorCode.INSERT_FAILED);
        }

        return isSuccess;

    }

    private boolean finalizeEvaluation(int organisationId, int processId, boolean finalizeForced)
            throws DataAccessLayerException {
        String sql = "{ call dbo.sa_finish_evaluation( ?,?,?,? ) }";
        boolean isSuccess = false;

        try (CallableStatement statement = super.getConnection().prepareCall(sql)) {

            statement.setInt(1, processId);
            statement.setInt(2, organisationId);
            statement.setBoolean(3, finalizeForced);
            statement.registerOutParameter(4, Types.BIT);

            statement.executeUpdate();

            isSuccess = statement.getBoolean(4);

        } catch (SQLException ex) {
            throw new DataAccessLayerException(ex.getMessage(),
                    DataAccessErrorCode.INSERT_FAILED);
        }

        return isSuccess;
    }

}
