package com.kandk.DbMetaDataParser.service.sqltypesservice;

public interface SqlColumnTypeParser {

    String getJavaType(String sqlType);
    String getJavaType(Integer sqlType);

}
