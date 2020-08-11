package com.kandk.DbMetaDataParser.service.savingservice;


import com.kandk.DbMetaDataParser.service.buildservice.NamesFormater;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class TxtFileWriter implements CodeWriter{
    NamesFormater namesFormater;

    public TxtFileWriter(NamesFormater namesFormater) {
        this.namesFormater = namesFormater;
    }

    @Override
    public void write(String fileName, String content) throws IOException {
        Files.writeString(Paths.get("K:/DbMetaDataParser/src/main/resources/generatedjavaclasses/"+namesFormater.toPascalCase(namesFormater.fromSnakeCaseToCamelCase(fileName))+".java"),content, StandardCharsets.UTF_8);
    }
}
