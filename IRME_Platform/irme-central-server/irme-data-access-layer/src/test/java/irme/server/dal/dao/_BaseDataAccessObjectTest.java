package irme.server.dal.dao;

import com.irme.server.dal.DataAccessObjectFactory;
import com.irme.server.dal.connection.ConnectionConfig;
import com.irme.server.dal.connection.ConnectionConfigType;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public abstract class _BaseDataAccessObjectTest {

    protected static DataAccessObjectFactory daoFactory;

    static {
        HikariConfig hikariConfig = ConnectionConfig
                .getDataSourceConfig(ConnectionConfigType.TESTING);
        HikariDataSource dataSource = new HikariDataSource(hikariConfig);
        DataAccessObjectFactory factory = new DataAccessObjectFactory(dataSource);
        daoFactory = factory;

    }

    protected _BaseDataAccessObjectTest() {

    }

}
