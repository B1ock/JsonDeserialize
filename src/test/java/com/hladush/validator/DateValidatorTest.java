package com.hladush.validator;

import org.junit.Test;

import static org.junit.Assert.*;


public class DateValidatorTest {

    @Test
    public void testIsValidLogTime() throws Exception {
        String jsonVLT = null;
        assertEquals(false, DateValidator.isValidLogTime(jsonVLT));

        jsonVLT = "efdgwer";
        assertEquals(false, DateValidator.isValidLogTime(jsonVLT));

        jsonVLT = "65:23";
        assertEquals(false, DateValidator.isValidLogTime(jsonVLT));

        jsonVLT = "12:85";
        assertEquals(false, DateValidator.isValidLogTime(jsonVLT));

        jsonVLT = "13:12";
        assertEquals(true, DateValidator.isValidLogTime(jsonVLT));
    }
}