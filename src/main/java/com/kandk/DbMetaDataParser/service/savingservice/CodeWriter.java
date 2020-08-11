package com.kandk.DbMetaDataParser.service.savingservice;

import java.io.IOException;

public interface CodeWriter {

    void write(String name, String content) throws IOException;
}
