package com.kandk.DbMetaDataParser.service.dbservice;

import com.kandk.DbMetaDataParser.model.Column;
import com.kandk.DbMetaDataParser.model.Table;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.*;
import java.util.*;

import static com.kandk.DbMetaDataParser.model.Column.*;

@Service
public class DataBaseDetails {

    DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Table getColumnData(String catalog, String schemaPattern, String tableName, String columnNamePattern) {
        Connection conn = null;
//        Map<String, List<Column>> columnDetails = new HashMap<>();
        Table table = new Table();
        List<Column> columns = new ArrayList<>();
        try {
            conn = dataSource.getConnection();
            DatabaseMetaData metaData = conn.getMetaData();
            ResultSet rs = metaData.getColumns(catalog, schemaPattern, tableName, columnNamePattern);
            while (rs.next()) {
                Column column = new ColumnBuilder()
                        .setColumnName(rs.getString("TABLE_CAT"))
                        .setTableSchem(rs.getString("TABLE_SCHEM"))
                        .setTableName(rs.getString("TABLE_NAME"))
                        .setColumnName(rs.getString("COLUMN_NAME"))
                        .setDataType(rs.getInt("DATA_TYPE"))
                        .setTypeName(rs.getString("TYPE_NAME"))
                        .setColumnSize(rs.getInt("COLUMN_SIZE"))
                        .setDecimalDigits(rs.getInt("DECIMAL_DIGITS"))
                        .setNumPrecRadix(rs.getInt("NUM_PREC_RADIX"))
                        .setNullable(rs.getInt("NULLABLE")).build();
                columns.add(column);

            }
            table.setName(tableName);
            table.setColumns(columns);
//            columnDetails.put(tableName, columns);
            return table;
        } catch (SQLException e) {
            throw new RuntimeException(e);

        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                }
            }
        }

    }

    public List<String> getTableData() {
        Connection conn = null;
        List<String> tables = new ArrayList<>();
        try {
            conn = dataSource.getConnection();
            DatabaseMetaData metaData = conn.getMetaData();
            ResultSet rs = metaData.getTables(null, "dbo", "%", new String[]{"TABLE"});
            while (rs.next()) {
                tables.add(rs.getString("TABLE_NAME"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);

        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                }
            }
        }
        return tables;
    }
}
