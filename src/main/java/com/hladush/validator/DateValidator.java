package com.hladush.validator;

public class DateValidator {

    private static final String CORRECT_TIME_PATTERN = "([0-9]{2}:[0-9]{2})";

    public static boolean isValidLogTime(final String time){
        return time != null && time.length() == 5 && time.matches(CORRECT_TIME_PATTERN);
    }

}
