package com.hladush;

import com.hladush.processor.LogEventProcessor;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        new LogEventProcessor().processLogFromFile("events.json");
    }


}
