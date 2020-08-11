package com.kandk.DbMetaDataParser.service.buildservice;

import com.kandk.DbMetaDataParser.model.Column;
import com.kandk.DbMetaDataParser.model.Table;
import com.kandk.DbMetaDataParser.service.dbservice.DataBaseDetails;
import com.kandk.DbMetaDataParser.service.sqltypesservice.SqlColumnTypeParser;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class EntityBuilder {

    DataBaseDetails dataBaseDetails;
    SqlColumnTypeParser mssqlServerColumnTypeParser;
    NamesFormater namesFormater;

    public EntityBuilder(DataBaseDetails dataBaseDetails, SqlColumnTypeParser mssqlServerColumnTypeParser, NamesFormater namesFormater) {
        this.dataBaseDetails = dataBaseDetails;
        this.mssqlServerColumnTypeParser = mssqlServerColumnTypeParser;
        this.namesFormater = namesFormater;
    }

    public String prepareEntityUnit(String tableName) {
        String columns = prepareContent(tableName);
        String importNotNull = columns.contains("@NotNull") ? "import javax.validation.constraints.NotNull;\n" : "";
        String importSize = columns.contains("@Size") ? "import javax.validation.constraints.Size;\n" : "";
        String importDate = columns.contains("Date") ? "import java.util.Date;\n" : "";
        ;
        String importTimestamp = columns.contains("Timestamp") ? "import java.sql.Timestamp;\n" : "";
        String importCreationTimestamp = columns.contains("@CreationTimestamp") ? "import org.hibernate.annotations.CreationTimestamp;\n" : "";
        String importBigDecimal = columns.contains("BigDecimal") ? "import java.math.BigDecimal;" : "";
        return "import lombok.Data;\n" + importCreationTimestamp + "\nimport javax.persistence.*;\n" + importNotNull + importSize + importBigDecimal + importDate + importTimestamp +
                "\n@Entity\n@Table(name = \"" + tableName + "\")\n@Data\n" + "public class " + namesFormater.toPascalCase(namesFormater.fromSnakeCaseToCamelCase(tableName)) +
                " {\n\n" + columns + "}";
    }

    private String prepareContent(String tableName) {
        Table table = dataBaseDetails.getColumnData(null, null, tableName, null);
        return table.getColumns().stream().map(this::prepareColumnUnit).collect(Collectors.joining());
    }

    private String prepareColumnUnit(Column column) {
        String typeName = column.getTypeName();
        String sqlColumnName = column.getColumnName();
        String sqlColumnType = mssqlServerColumnTypeParser.getJavaType(column.getTypeName());
        String isColumnNullable = column.getNullable().equals(0) ? "@NotNull\n" : "";
        String sqlColumnSize = String.valueOf(column.getColumnSize());
        String sizeAnnotation = typeName.equalsIgnoreCase("varchar") ? "@Size(max = " + sqlColumnSize + ")\n" : "";
        String idColumn = typeName.contains("identity") ? "@Id\n@GeneratedValue(strategy = GenerationType.IDENTITY)\n" : "";

        return typeName.equalsIgnoreCase("timestamp") ? "@Column(columnDefinition = \"timestamp\", insertable = false, updatable = false)\n" +
                "@CreationTimestamp\n" + "private Date " + namesFormater.fromSnakeCaseToCamelCase(sqlColumnName) + ";\n" : idColumn + "@Column(name=\"" + sqlColumnName + "\")\n" + isColumnNullable + sizeAnnotation +
                "private " + sqlColumnType + " " + namesFormater.fromSnakeCaseToCamelCase(sqlColumnName) + ";\n";

    }

}
