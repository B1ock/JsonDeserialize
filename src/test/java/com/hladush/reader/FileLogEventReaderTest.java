package com.hladush.reader;

import com.fasterxml.jackson.core.type.TypeReference;
import com.hladush.deserializer.LogEventDeserializer;
import com.hladush.model.LogEvent;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;


public class FileLogEventReaderTest {

    @org.junit.Test
    public void testReadAllEntities() throws Exception {
        final TypeReference<List<LogEvent>> LIST_TYPE_REFERENCE_EVENTS = new TypeReference<List<LogEvent>>() {
        };
        String jsonpath = "src/test/resources/eventstest.json";
        File file = new File(jsonpath);
        URL url = file.toURI().toURL();
        FileLogEventReader fler = new FileLogEventReader(jsonpath);
        fler.readAllEntities();
        LogEventDeserializer led = LogEventDeserializer.getInstance();


        ArrayList<LogEvent> expectedEvents = new ArrayList<>();
        expectedEvents.add(new LogEvent("Visitor Jack picked 1 bread to his basket", "00:17", 17000));
        expectedEvents.add(new LogEvent("Visitor John Wick picked 2 battaries AA to his basket", "00:35", 35000));
        expectedEvents.add(new LogEvent("Visitor John Oher picked 3 chicken to his basket", "00:59", 59000));

        List<LogEvent> actualValues = led.readValue(url, LIST_TYPE_REFERENCE_EVENTS);
                assertEquals(expectedEvents.size(),actualValues.size());
                assertEquals(true, expectedEvents.containsAll(actualValues));
    }
}