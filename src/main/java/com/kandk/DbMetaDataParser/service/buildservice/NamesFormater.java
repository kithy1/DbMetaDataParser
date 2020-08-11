package com.kandk.DbMetaDataParser.service.buildservice;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class NamesFormater {

    public String fromSnakeCaseToCamelCase(String columnName) {
        String[] columnNames = columnName.split("[^a-zA-Z0-9]");
        List<String> formattedTokens = new ArrayList<>();
        List<String> tokens = new ArrayList<>(Arrays.asList(columnNames));
        for (int i = 0; i < tokens.size(); i++) {
            String token = tokens.get(i);
            if (i == 0) {
                token = toCamelCase(token);
                formattedTokens.add(token);
            } else {
                token = toPascalCase(token);
                formattedTokens.add(token);
            }
        }
        return String.join("", formattedTokens);
    }

    public String toCamelCase(String s) {
        return String.valueOf(s.charAt(0)).toLowerCase() + s.substring(1);
    }

    public String toPascalCase(String s) {
        return String.valueOf(s.charAt(0)).toUpperCase() + s.substring(1);
    }
}
