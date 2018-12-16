package com.hladush.reader;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

public interface Reader<T> {
    List<T> readAllEntities() throws IOException;
}
