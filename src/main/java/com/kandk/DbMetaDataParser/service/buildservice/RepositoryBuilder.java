package com.kandk.DbMetaDataParser.service.buildservice;

import com.kandk.DbMetaDataParser.model.Table;
import com.kandk.DbMetaDataParser.service.sqltypesservice.SqlColumnTypeParser;
import org.springframework.stereotype.Component;

@Component
public class RepositoryBuilder {
    SqlColumnTypeParser mssqlServerColumnTypeParser;
    NamesFormater namesFormater;

    public RepositoryBuilder(SqlColumnTypeParser mssqlServerColumnTypeParser, NamesFormater namesFormater) {
        this.mssqlServerColumnTypeParser = mssqlServerColumnTypeParser;
        this.namesFormater = namesFormater;
    }

    public String prepareRepository(Table table) {
        String tableName = namesFormater.toPascalCase(namesFormater.fromSnakeCaseToCamelCase(table.getName()));
        String idType = table.getColumns().stream()
                .filter(column -> column.getTypeName().contains("identity"))
                .map(column -> column.getDataType())
                .map(type -> mssqlServerColumnTypeParser.getJavaType(type))
                .reduce("", (s, s2) -> s2);
        return "@Repository\npublic interface " + tableName +
                "Repository extends CrudRepository<" + tableName + "," + idType + "> {\n\n} ";

    }
}
