package com.hladush.writer;

public class ConsoleWriter implements Writer {

    @Override
    public void write(Object object) {
        System.out.println(object);
    }
}
