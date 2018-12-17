package com.hladush.writer;

/**
 * Class for write object to console
 */
public class ConsoleWriter implements Writer {

    @Override
    public void write(Object object) {
        System.out.println(object);
    }
}
