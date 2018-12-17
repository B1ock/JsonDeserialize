package com.hladush.deserializer;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.hladush.model.LogEvent;

import java.io.IOException;
import java.net.URL;
import java.util.List;

/**
 *  Class for deserializetion LogEvent
 */
public class LogEventDeserializer {

    private static final LogEventDeserializer INSTANCE = new LogEventDeserializer();
    private ObjectMapper mapper;

    private LogEventDeserializer() {
        mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addDeserializer(LogEvent.class, new CustomEventJsonDeserializer());
        mapper.registerModule(module);
    }

    public static LogEventDeserializer getInstance() {
        return INSTANCE;
    }

    public List<LogEvent> readValue(URL url, TypeReference<List<LogEvent>> listTypeReference) throws IOException {
        return mapper.readValue(url, listTypeReference);
    }
}
