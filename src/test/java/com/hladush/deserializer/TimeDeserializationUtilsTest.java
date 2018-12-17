package com.hladush.deserializer;

import org.junit.Test;

import static org.junit.Assert.*;

public class TimeDeserializationUtilsTest {


    @Test
    public void testConvertToMillisecond() throws Exception {
        String jsonCTM = "00:00";
        assertEquals( 0,         TimeDeserializationUtils.convertToMillisecond(jsonCTM));

        jsonCTM = "59:59";
        assertEquals( 3_599_000, TimeDeserializationUtils.convertToMillisecond(jsonCTM));

        jsonCTM = "01:22";
        assertEquals( 82_000,    TimeDeserializationUtils.convertToMillisecond(jsonCTM));
    }
}