package com.kandk.DbMetaDataParser.service.sqltypesservice;

import org.springframework.stereotype.Component;

@Component
public class MSSQLServerColumnTypeParser implements SqlColumnTypeParser {

    @Override
    public String getJavaType(String sqlType) {
        switch (sqlType) {
            case "bit":
                return "Boolean";
            case "tinyint":
            case "smallint":
                return "Short";
            case "int":
            case "int identity":
                return "Integer";
            case "bigint":
                return "Long";
            case "float":
            case "double":
                return "Double";
            case "real":
                return "Float";
            case "numeric":
            case "decimal":
            case "money":
                return "BigDecimal";
            case "char":
            case "varchar":
            case "longVarchar":
            case "nchar":
            case "nvarchar":
            case "text":
            case "ntext":
            case "sysname":
                return "String";
            case "date":
            case "datetime":
                return "Date";
            case "time":
            case "smalldatetime":
                return "Timestamp";
            case "binary":
            case "varbinary":
            case "longVarbinary":
            case "image":
                return "byte[]";
            default:
                return "<<<---undefined datatype--->>>";
        }
    }

    @Override
    public String getJavaType(Integer sqlType) {
        switch (sqlType) {
            case -7:
            case 16:
                return "Boolean";
            case 4:
                return "Integer";
            case -6:
            case 5:
                return "Short";
            case -15:
            case -9:
            case -1:
            case 1:
            case 12:
            case -16:
                return "String";
            case -5:
                return "Long";
            case 6:
            case 8:
                return "Double";
            case 7:
                return "Float";
            case 2:
            case 3:
                return "BigDecimal";
            case 91:
                return "Date";
            case 92:
                return "TimeStamp";
            case 93:
                return "Timestamp";
            case -2:
            case -3:
            case -4:
                return "byte[]";
            default:
                return "<<<---undefined datatype--->>>";
        }
    }
}
