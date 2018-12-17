package com.hladush.reader;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

/**
 * Base class for all readers
 * @param <T>
 */

public interface Reader<T> {
    List<T> readAllEntities() throws IOException;
}
