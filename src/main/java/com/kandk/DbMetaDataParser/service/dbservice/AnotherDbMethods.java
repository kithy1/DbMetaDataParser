package com.kandk.DbMetaDataParser.service.dbservice;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnotherDbMethods {

    DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void getTableNames() {
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            DatabaseMetaData metaData = conn.getMetaData();
            //ResultSet rs = metaData.getTables(null,null,"%", null);
//            ResultSet rs = metaData.getTables(null,null,null, new String[]{"TABLE"});
//
//            while (rs.next()){
//                System.out.println(rs.getString(1));
//                System.out.println(rs.getString(2));
//                System.out.println(rs.getString(3));
//                System.out.println(rs.getString(4));
//            }
            String tableName = "sprawy";
            ResultSet columns = metaData.getColumns(null, "dbo", tableName, null);

            ResultSet keys = metaData.getPrimaryKeys(null, null, "sprawy");
            ResultSet privileges = metaData.getColumnPrivileges(null, null, "sprawy", null);
            ResultSet cross = metaData.getCrossReference(null, null, "sprawy", null, null, null);
//            while (columns.next()){
//                String columnName = columns.getString("COLUMN_NAME");
//                String dataType = columns.getString("COLUMN_SIZE");
//                System.out.println(columnName + " " + dataType);
//            }
            while (cross.next()) {
                String pktable_cat = cross.getString("PKTABLE_CAT");
                String pktable_name = cross.getString("PKTABLE_NAME");
                String pkcolumn_name = cross.getString("PKCOLUMN_NAME");
                String key_seq = cross.getString("KEY_SEQ");
                String pk_name = cross.getString("PK_NAME");

                System.out.println(pktable_cat + " " + pktable_name + " " + pkcolumn_name + " " + key_seq + " " + pk_name);
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
    }
    public Map<String, List<String>> getColumnNames() {
        Connection conn = null;
        Map<String, List<String>> columnDetails = new HashMap<>();
        try {
            conn = dataSource.getConnection();
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * from dbo.sprawy");
            ResultSetMetaData metaData = rs.getMetaData();
            for (int i = 1; i < metaData.getColumnCount(); i++) {
                List<String> details = new ArrayList<>();
                details.add(metaData.getColumnTypeName(i));
                details.add(String.valueOf(metaData.isNullable(i)));
                columnDetails.put(metaData.getColumnName(i), details);
            }
            return columnDetails;
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
}
