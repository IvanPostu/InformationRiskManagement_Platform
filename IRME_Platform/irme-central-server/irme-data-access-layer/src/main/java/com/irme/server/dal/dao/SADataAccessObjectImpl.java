package com.irme.server.dal.dao;

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
import java.util.ArrayList;
import java.util.HashMap;
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

}
