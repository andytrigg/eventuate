package com.sloshydog.eventuate.filesystem;

import com.sloshydog.eventuate.api.Event;
import com.sloshydog.eventuate.api.EventSpecification;
import com.sloshydog.eventuate.api.EventSpecifications;
import com.sloshydog.eventuate.api.EventStore;
import com.sloshydog.eventuate.api.EventStoreException;
import com.sloshydog.eventuate.api.EventStream;
import com.sloshydog.eventuate.common.LogUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class FileSystemEventStore implements EventStore {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileSystemEventStore.class);

    private final EventStoreFileResolver eventStoreFileResolver;
    private final FileSystemEventMessageWriter eventMessageWriter;
    private final FileSystemEventMessageReader eventMessageReader;

    public FileSystemEventStore(EventStoreFileResolver eventStoreFileResolver, FileSystemEventMessageWriter eventMessageWriter, FileSystemEventMessageReader fileSystemEventMessageReader) {
        this.eventStoreFileResolver = eventStoreFileResolver;
        this.eventMessageWriter = eventMessageWriter;
        this.eventMessageReader = fileSystemEventMessageReader;
    }

    @Override
    public void store(Event applicationEvent) {
        LogUtils.debug(LOGGER, "store the event:%s", applicationEvent);

        OutputStream out = null;
        try {
            EventSpecification eventSpecification = EventSpecifications.specificationFrom(applicationEvent);
            out = new FileOutputStream(eventStoreFileResolver.getFileFor(eventSpecification), true);
            DataOutputStream dataOutputStream = new DataOutputStream(out);
            eventMessageWriter.writeEventMessage(dataOutputStream, applicationEvent);
        } catch (IOException e) {
            throw new EventStoreException("Unable to load event due to a FileNotFoundException", e);
        } finally {
            IOUtils.closeQuietly(out);
        }

    }

    @Override
    public EventStream getMatching(EventSpecification eventSpecification) {
        try {
            File inputFile = eventStoreFileResolver.getFileFor(eventSpecification);
            return new FileSystemEventStream(eventMessageReader, new FileInputStream(inputFile));
        } catch (FileNotFoundException e) {
            throw new EventStoreException("Unable to load event due to a FileNotFoundException", e);
        }
    }
}
