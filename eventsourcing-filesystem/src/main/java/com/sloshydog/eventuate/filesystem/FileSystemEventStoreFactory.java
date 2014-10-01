package com.sloshydog.eventuate.filesystem;

import com.sloshydog.eventuate.api.EventStore;
import com.sloshydog.eventuate.internal.IEventStoreFactory;

import java.io.File;
import java.util.Map;

public class FileSystemEventStoreFactory implements IEventStoreFactory {

    @Override
    public EventStore getEventStore(Map<Object, Object> configuration) {
        if (!configuration.containsKey("eventstore.dir")) {
            throw new IllegalArgumentException("Configuration property 'eventstore.dir' is not specified");
        }

        final File baseDirectory = new File((String) configuration.get("eventstore.dir"));
        PayloadSerializer payloadSerializer = new PayloadSerializer();
        return new FileSystemEventStore(new EventStoreFileResolver(baseDirectory), new FileSystemEventMessageWriter(payloadSerializer), new FileSystemEventMessageReader(payloadSerializer));
    }
}
