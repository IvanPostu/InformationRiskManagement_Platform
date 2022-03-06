package com.irme.server.business_entities;

import com.irme.common.dto.SACategoryDto;
import com.irme.server.dal.DataAccessObjectFactory;
import com.irme.server.dal.dao.SADataAccessObject;
import com.irme.server.dal.dao.SADataAccessObjectImpl;
import com.irme.server.dal.exceptions.DataAccessLayerException;
import lombok.extern.slf4j.Slf4j;

import javax.sql.DataSource;

import java.util.ArrayList;
import java.util.List;

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

}
