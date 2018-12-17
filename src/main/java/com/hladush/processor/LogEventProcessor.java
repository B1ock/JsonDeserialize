package com.hladush.processor;

import com.hladush.model.LogEvent;
import com.hladush.reader.FileLogEventReader;
import com.hladush.reader.Reader;
import com.hladush.writer.ConsoleWriter;
import com.hladush.writer.Writer;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Class for reading from a file, writing to a collection and sorting it
 * as well as for printing all elements.
 */
public class LogEventProcessor {

    private Writer writer = new ConsoleWriter();

    public void processLogFromFile(String pathToFile) throws IOException {
        long startTime = System.currentTimeMillis();
        Reader<LogEvent> reader = new FileLogEventReader(pathToFile);
        List<LogEvent> events = reader.readAllEntities();
        Collections.sort(events);

        printAllElementsOnConsole(startTime, events);


    }

    private void printAllElementsOnConsole(long startTime, List<LogEvent> events) {
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        for (final LogEvent currentElement : events) {
            long timeSpent = currentElement.getTimeInMillisecond() - (System.currentTimeMillis() - startTime);
            if (timeSpent < 0) {
                timeSpent = 0;
            }
            Runnable task = () -> writer.write(currentElement);
            executor.schedule(task, timeSpent, TimeUnit.MILLISECONDS);
        }
        executor.shutdown();
    }
}
