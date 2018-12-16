package com.hladush.reader;

import com.fasterxml.jackson.core.type.TypeReference;
import com.hladush.deserializer.LogEventDeserializer;
import com.hladush.model.LogEvent;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;

public class FileLogEventReader implements Reader<LogEvent> {
    private static final TypeReference<List<LogEvent>> LIST_TYPE_REFERENCE = new TypeReference<List<LogEvent>>() {
    };
    private static final LogEventDeserializer DESERIALIZER = LogEventDeserializer.getInstance();

    private final String pathToFile;

    public FileLogEventReader(String pathToFile) {
        this.pathToFile = pathToFile;
    }


    @Override
    public List<LogEvent> readAllEntities() throws IOException {
        File file = new File(pathToFile);
        URL url = file.toURI().toURL();

        return DESERIALIZER.readValue(url, LIST_TYPE_REFERENCE);
    }

}
