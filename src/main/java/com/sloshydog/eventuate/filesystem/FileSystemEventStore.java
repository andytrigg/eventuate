package com.sloshydog.eventuate.filesystem;

import com.sloshydog.eventuate.api.Event;
import com.sloshydog.eventuate.api.EventSpecification;
import com.sloshydog.eventuate.api.EventSpecifications;
import com.sloshydog.eventuate.api.EventStore;
import com.sloshydog.eventuate.api.EventStoreException;
import com.sloshydog.eventuate.api.EventStream;

import java.io.Closeable;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class FileSystemEventStore implements EventStore {

    private final EventStoreFileResolver eventStoreFileResolver;

    public FileSystemEventStore(EventStoreFileResolver eventStoreFileResolver) {
        this.eventStoreFileResolver = eventStoreFileResolver;
    }

    @Override
    public void store(Event applicationEvent) {
        OutputStream out = null;
        try {
            EventSpecification eventSpecification = EventSpecifications.specificationFrom(applicationEvent);
            out = new FileOutputStream(eventStoreFileResolver.getFileFor(eventSpecification), true);
            DataOutputStream dataOutputStream = new DataOutputStream(out);
            dataOutputStream.writeUTF(applicationEvent.getPayload());
        } catch (IOException e) {
            throw new EventStoreException("Unable to store given entity due to an IOException", e);
        } finally {
            closeQuietly(out);
        }

    }

    public void closeQuietly(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException ignored) {
            }
        }
    }

    @Override
    public EventStream getMatching(EventSpecification eventSpecification) {
//        DataInputStream inputStream = null;
//        try {
//            inputStream = new DataInputStream(new FileInputStream(new File(getBaseDirFor(eventSpecification), getFileNameFor(eventSpecification))));
//        } catch (FileNotFoundException e) {
//            throw new EventStoreException("bang", e);
//        }
//        return new EventStream() {
//
//            @Override
//            public Iterator<Event> iterator() {
//                return new ;
//            }
//        };
        return null;
    }
}
