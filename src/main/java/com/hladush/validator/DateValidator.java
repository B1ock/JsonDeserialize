package com.hladush.validator;

/**
 *  Validator for time in LogEvent
 */
public class DateValidator {

    private static final String CORRECT_TIME_PATTERN = "([0-5][0-9]:[0-5][0-9])";

    public static boolean isValidLogTime(final String time){
        return time != null && time.matches(CORRECT_TIME_PATTERN);
    }

}
