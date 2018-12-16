package com.hladush.deserializer;

public class TimeDeserializationUtils {
    public static int convertToMillisecond(String time) {
        int timeInSecond = (time.charAt(0) - '0') * 10 * 60;
        timeInSecond += (time.charAt(1) - '0') * 60;
        timeInSecond += (time.charAt(3) - '0') * 10;
        timeInSecond += (time.charAt(4) - '0');
        timeInSecond *= 1000;
        return timeInSecond;
    }
}
