package com.kandk.DbMetaDataParser.configuration;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;

import javax.sql.DataSource;


public class DbConnectionConfig {

    SQLServerDataSource dataSource = new SQLServerDataSource();

    public DataSource dataSource(String userName, String password, String serverName, String dbName) {
        dataSource.setUser(userName);
        dataSource.setPassword(password);
        dataSource.setServerName(serverName);
        dataSource.setDatabaseName(dbName);
        return dataSource;
    }

    public SQLServerDataSource getDataSource() {
        return dataSource;
    }
}
