package com.irme.server.dal.dao;

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

}
