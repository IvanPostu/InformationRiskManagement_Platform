package com.irme.server.dal.dao;

import javax.sql.DataSource;

public abstract class OrganisationsDataAcessObject extends BaseDataAccessObject {

    protected OrganisationsDataAcessObject(DataSource dataSource) throws Exception {
        super(dataSource);
    }

}
