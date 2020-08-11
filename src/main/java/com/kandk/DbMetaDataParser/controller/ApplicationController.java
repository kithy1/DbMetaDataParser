package com.kandk.DbMetaDataParser.controller;

import com.kandk.DbMetaDataParser.configuration.DbConnectionConfig;
import com.kandk.DbMetaDataParser.model.Column;
import com.kandk.DbMetaDataParser.model.Table;
import com.kandk.DbMetaDataParser.service.dbservice.DataBaseDetails;
import com.kandk.DbMetaDataParser.service.buildservice.EntityBuilder;
import com.kandk.DbMetaDataParser.service.savingservice.CodeWriter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ApplicationController {
    DataBaseDetails dataBaseDetails;
    EntityBuilder entityBuilder;
    CodeWriter codeWriter;
    DbConnectionConfig dbConnectionConfig;

    public ApplicationController(DataBaseDetails dataBaseDetails, EntityBuilder entityBuilder, CodeWriter codeWriter) {
        this.dataBaseDetails = dataBaseDetails;
        this.entityBuilder = entityBuilder;
        this.codeWriter = codeWriter;
        this.dbConnectionConfig = new DbConnectionConfig();
    }

    @PostMapping(value = "/dbconnection", params = {"userName", "password", "serverName", "dbName"})
    public void prepareConnection(@RequestParam("userName") String userName, @RequestParam("password") String password, @RequestParam("serverName") String serverName, @RequestParam("dbName") String dbName) {
        dbConnectionConfig.dataSource(userName, password, serverName, dbName);
        this.dataBaseDetails.setDataSource(dbConnectionConfig.getDataSource());

    }

    @GetMapping("/table")
    public List<String> showTables() {
        return dataBaseDetails.getTableData();
    }

    @GetMapping(value = "/entity", params = "tableName")
    public String showEntity(@RequestParam("tableName") String tableName) {
        return entityBuilder.prepareEntityUnit(tableName);
    }

    @GetMapping(value = "/write", params = "tableName")
    public void writeContentToFile(@RequestParam("tableName") String tableName) throws IOException {
        codeWriter.write(tableName, entityBuilder.prepareEntityUnit(tableName));
    }

    @GetMapping("/writeAll")
    public void writeAllEntities() {
        dataBaseDetails.getTableData().stream().forEach(s -> {
            try {
                codeWriter.write(s, entityBuilder.prepareEntityUnit(s));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @GetMapping(value = "/types", params = "tableName")
    public String getColumn(@RequestParam(name = "tableName") String tableName) {
        Table alimentowani = dataBaseDetails.getColumnData(null, null, tableName, null);
        return alimentowani.getColumns().stream().map(column -> column.toString() + "\n").collect(Collectors.joining());
    }
}
