package com.hladush.model;

import java.io.Serializable;

public class LogEvent implements Comparable<LogEvent>, Serializable {
    private String eventName;
    private String time;
    private int timeInMillisecond;


    public LogEvent(String eventName, String time, int timeInMillisecond) {
        this.eventName = eventName;
        this.time = time;
        this.timeInMillisecond = timeInMillisecond;
    }

    public String getEventName() {
        return eventName;
    }

    public String getTime() {
        return time;
    }

    public int getTimeInMillisecond() {
        return timeInMillisecond;
    }

    @Override
    public String toString() {
        return "{\n" +
                "\"eventName\":\"" + eventName + "\",\n" +
                "\"time\":" + time + "\"\n" +
                '}';
    }

    @Override
    public int compareTo(LogEvent event) {
        return Integer.compare(this.timeInMillisecond, event.timeInMillisecond);
    }
}
