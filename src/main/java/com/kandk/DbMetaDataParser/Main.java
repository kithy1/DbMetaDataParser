package com.kandk.DbMetaDataParser;

import com.kandk.DbMetaDataParser.model.Column;

import java.util.List;

public class Main {
    public static void main(String[] args) {
//        String columnName = "dluznik_home_address";
//        EntityBuilder entityBuilder = new EntityBuilder();
//        List<String> columnData = List.of("data zamkniecia_sprawy","datetime","1");
//        System.out.println(entityBuilder.fromSnakeCaseToCamelCase(columnName));
//        System.out.println(entityBuilder.prepareEntityUnit("sss dluznik"));
//        System.out.println(entityBuilder.prepareColumnUnit(columnData));
//        System.out.println(entityBuilder.prepareJavaType("bit"));
//        Column column = new Column.ColumnBuilder().setColumnName("cos").setColumnSize(50).build();
//        System.out.println(column);
        String text = "jakas-nazwa_2-z cyframi2";
        String[] split = text.split("[^a-zA-Z0-9]");
        for (String s : split) {
            System.out.println("-->"+s);
        }
    }
}
