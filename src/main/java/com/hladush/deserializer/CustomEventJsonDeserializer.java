package com.hladush.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.hladush.model.LogEvent;
import com.hladush.validator.DateValidator;

import java.io.IOException;

public class CustomEventJsonDeserializer extends JsonDeserializer<LogEvent> {

    @Override
    public LogEvent deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        TempEvent event = jp.readValueAs(TempEvent.class);

        if (!DateValidator.isValidLogTime(event.time)) {
            throw new IllegalArgumentException("Invalide time");
        }
        int timeInMilliseconds = TimeDeserializationUtils.convertToMillisecond(event.time);

        return new LogEvent(event.eventName, event.time, timeInMilliseconds);
    }

    private static class TempEvent {

        public String eventName;
        public String time;

    }

}
