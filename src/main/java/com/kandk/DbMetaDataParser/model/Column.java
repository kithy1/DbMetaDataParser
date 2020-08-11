package com.kandk.DbMetaDataParser.model;

import lombok.Data;

@Data
public class Column {

    private String tableCat;
    private String tableSchem;
    private String tableName;
    private String columnName;
    private Integer dataType;
    private String typeName;
    private Integer columnSize;
    private Integer decimalDigits;
    private Integer numPrecRadix;
    private Integer nullable;
    
    public static class ColumnBuilder{
        private String tableCat;
        private String tableSchem;
        private String tableName;
        private String columnName;
        private Integer dataType;
        private String typeName;
        private Integer columnSize;
        private Integer decimalDigits;
        private Integer numPrecRadix;
        private Integer nullable;
        
        public ColumnBuilder setTableCat(String tableCat){
            this.tableCat = tableCat;
            return this;
        }

        public ColumnBuilder setTableSchem(String tableSchem) {
            this.tableSchem = tableSchem;
            return this;
        }

        public ColumnBuilder setTableName(String tableName) {
            this.tableName = tableName;
            return this;
        }

        public ColumnBuilder setColumnName(String columnName) {
            this.columnName = columnName;
            return this;
        }

        public ColumnBuilder setDataType(Integer dataType) {
            this.dataType = dataType;
            return this;
        }

        public ColumnBuilder setTypeName(String typeName) {
            this.typeName = typeName;
            return this;
        }

        public ColumnBuilder setColumnSize(Integer columnSize) {
            this.columnSize = columnSize;
            return this;
        }

        public ColumnBuilder setDecimalDigits(Integer decimalDigits) {
            this.decimalDigits = decimalDigits;
            return this;
        }

        public ColumnBuilder setNumPrecRadix(Integer numPrecRadix) {
            this.numPrecRadix = numPrecRadix;
            return this;
        }

        public ColumnBuilder setNullable(Integer nullable) {
            this.nullable = nullable;
            return this;
        }
        
        public Column build(){
            Column column = new Column();
            column.setTableCat(tableCat);
            column.setTableSchem(tableSchem);
            column.setTableName(tableName);
            column.setColumnName(columnName);
            column.setDataType(dataType);
            column.setTypeName(typeName);
            column.setColumnSize(columnSize);
            column.setDecimalDigits(decimalDigits);
            column.setNumPrecRadix(numPrecRadix);
            column.setNullable(nullable);
            return column;
        }
    }

    @Override
    public String toString() {
        return "Column{" +
                "tableCat='" + tableCat + '\'' +
                ", tableSchem='" + tableSchem + '\'' +
                ", tableName='" + tableName + '\'' +
                ", columnName='" + columnName + '\'' +
                ", dataType=" + dataType +
                ", typeName='" + typeName + '\'' +
                ", columnSize=" + columnSize +
                ", decimalDigits=" + decimalDigits +
                ", numPrecRadix=" + numPrecRadix +
                ", nullable=" + nullable +
                '}';
    }
}
