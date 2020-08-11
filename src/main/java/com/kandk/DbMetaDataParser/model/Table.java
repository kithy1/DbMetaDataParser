package com.kandk.DbMetaDataParser.model;

import java.util.ArrayList;
import java.util.List;

public class Table {

    String name;
    List<Column> columns;

    public Table() {
        this.columns = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Column> getColumns() {
        return columns;
    }

    public void setColumns(List<Column> columns) {
        this.columns = columns;
    }
}
