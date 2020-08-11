package com.kandk.DbMetaDataParser.controller;

import com.kandk.DbMetaDataParser.model.Table;
import com.kandk.DbMetaDataParser.service.buildservice.RepositoryBuilder;
import com.kandk.DbMetaDataParser.service.dbservice.DataBaseDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RepositoryController {

    DataBaseDetails dataBaseDetails;
    RepositoryBuilder repositoryBuilder;

    public RepositoryController(DataBaseDetails dataBaseDetails,RepositoryBuilder repositoryBuilder) {
        this.dataBaseDetails = dataBaseDetails;
        this.repositoryBuilder = repositoryBuilder;
    }

    @GetMapping(value = "repo", params = "tableName")
    public String showRepository(@RequestParam String tableName){
        Table table = dataBaseDetails.getColumnData(null, null, tableName, null);
        return repositoryBuilder.prepareRepository(table);
    }
}
