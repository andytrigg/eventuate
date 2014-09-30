package com.sloshydog.eventuate.filesystem;

import com.sloshydog.eventuate.api.EventStore;
import com.sloshydog.eventuate.api.IEventStoreFactory;

import java.io.File;

public class FileSystemEventStoreFactory implements IEventStoreFactory {


    @Override
    public EventStore getEventStore() {
        final File baseDirectory = new File(System.getProperty("java.io.tmpdir"), "BookLibraryEvents");

        PayloadSerializer payloadSerializer = new PayloadSerializer();
        return new FileSystemEventStore(new EventStoreFileResolver(baseDirectory), new FileSystemEventMessageWriter(payloadSerializer), new FileSystemEventMessageReader(payloadSerializer));
    }
}
