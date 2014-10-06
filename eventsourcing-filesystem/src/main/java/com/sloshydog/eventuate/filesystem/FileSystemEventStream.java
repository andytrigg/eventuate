package com.sloshydog.eventuate.filesystem;

import com.sloshydog.eventuate.api.Event;
import com.sloshydog.eventuate.api.EventStoreException;
import com.sloshydog.eventuate.api.EventStream;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Queue;

import static com.google.common.collect.Lists.newLinkedList;

/**
 * The EventStream represents a stream of historical domain events. The order of events in this stream are in actual
 * chronological order in which the events happened.
 *
 * This implementation is back by the file system through the use of a {@link FileSystemEventMessageReader}
 *
 * @since 1.0
 */
class FileSystemEventStream implements EventStream {
    private final FileSystemEventMessageReader eventMessageReader;
    private final Queue<Event> events = newLinkedList();
    private final DataInputStream input;

    public FileSystemEventStream(FileSystemEventMessageReader eventMessageReader, InputStream inputStream) {
        this.eventMessageReader = eventMessageReader;
        this.input = new DataInputStream(new BufferedInputStream(inputStream));
        readEventFeed();
    }

    @Override
    public void close() throws IOException {
        input.close();
    }

    @Override
    public Iterator<Event> iterator() {
        return events.iterator();
    }

    private void readEventFeed() {
        try {
            do {
                events.add(eventMessageReader.readEventMessage(input));
            } while (true);
        } catch (EOFException e) {
            // No more events available
        } catch (IOException e) {
            throw new EventStoreException("An error occurred while reading from the underlying source", e);
        }
    }
}
