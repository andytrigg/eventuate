package com.sloshydog.eventuate.filesystem;

import com.sloshydog.eventuate.api.Event;
import com.sloshydog.eventuate.api.EventStream;

import java.io.Closeable;
import java.io.IOException;
import java.util.Iterator;

public class FileSystemEventStream implements EventStream, Closeable {

    @Override
    public void close() throws IOException {

    }

    @Override
    public Iterator<Event> iterator() {
        return null;
    }
}
