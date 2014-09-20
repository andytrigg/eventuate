package com.sloshydog.eventuate.filesystem;

import com.sloshydog.eventuate.api.Event;
import com.sloshydog.eventuate.api.EventSpecification;
import com.sloshydog.eventuate.api.EventStore;
import com.sloshydog.eventuate.api.EventStoreException;
import com.sloshydog.eventuate.api.EventStream;

import java.io.Closeable;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class FileSystemEventStore implements EventStore {

    private final String baseDirectory;

    public FileSystemEventStore(String baseDirectory) {
        this.baseDirectory = baseDirectory;
    }

    @Override
    public void store(Event applicationEvent) {
        OutputStream out = null;
        try {
            out = new FileOutputStream(new File(baseDirectory, "somefile.evt"));
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
        return null;
    }
}
