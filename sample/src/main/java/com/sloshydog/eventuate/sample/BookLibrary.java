package com.sloshydog.eventuate.sample;

import com.sloshydog.eventuate.api.Event;
import com.sloshydog.eventuate.api.EventSpecification;
import com.sloshydog.eventuate.api.EventStore;
import com.sloshydog.eventuate.api.EventStoreFactory;
import com.sloshydog.eventuate.api.EventStream;
import com.sloshydog.eventuate.api.Events;
import org.apache.commons.io.FileUtils;
import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

import static org.apache.commons.io.IOUtils.closeQuietly;

public final class BookLibrary {
    private static final Logger LOGGER = LoggerFactory.getLogger(BookLibrary.class);

    private BookLibrary() {
    }

    public static void main(String[] args) throws IOException {
        final File baseDirectory = new File(System.getProperty("java.io.tmpdir"), "BookLibraryEvents");
        FileUtils.deleteDirectory(baseDirectory);

        EventStore eventStore = EventStoreFactory.getInstance().getEventStore();
        eventStore.store(Events.newDomainEvent("book", "book1", new BookRegistered("book1", "The Day the Crayons Quit", "Oliver Jeffers", "0399255370")));
        eventStore.store(Events.newDomainEvent("book", "book2", new BookRegistered("book2", "Beautiful Oops!", "Barney Saltzberg", "076115728X")));
        eventStore.store(Events.newDomainEvent("book", "book3", new BookRegistered("book3", "The Most Magnificent Thing", "Ashley Spires", "1554537045")));

        eventStore.store(Events.newDomainEvent("book", "book2", new BookLent("book2", "Andy", new DateTime(), Duration.standardDays(14))));
        eventStore.store(Events.newDomainEvent("book", "book2", new BookReturned("book2", "Andy", Duration.standardDays(7), false)));

        eventStore.store(Events.newDomainEvent("book", "book3", new BookLent("book3", "Mike", new DateTime(), Duration.standardDays(14))));

        EventStream eventStream = eventStore.getMatching(new EventSpecification("book", "book2"));
        for (Event event : eventStream) {
            LOGGER.info(event.toString());
        }
        closeQuietly(eventStream);
    }
}
