package com.hladush.model;

import java.io.Serializable;

/**
 * Model that reperesent object in log file
 */
public class LogEvent implements Comparable<LogEvent> {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LogEvent logEvent = (LogEvent) o;

        if (timeInMillisecond != logEvent.timeInMillisecond) return false;
        if (eventName != null ? !eventName.equals(logEvent.eventName) : logEvent.eventName != null) return false;
        return time != null ? time.equals(logEvent.time) : logEvent.time == null;

    }

    @Override
    public int hashCode() {
        int result = eventName != null ? eventName.hashCode() : 0;
        result = 31 * result + (time != null ? time.hashCode() : 0);
        result = 31 * result + timeInMillisecond;
        return result;
    }
}
