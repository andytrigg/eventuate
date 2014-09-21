package com.sloshydog.eventuate.sample;

import com.sloshydog.eventuate.api.EventStore;
import com.sloshydog.eventuate.api.Events;
import com.sloshydog.eventuate.filesystem.EventStoreFileResolver;
import com.sloshydog.eventuate.filesystem.FileSystemEventStore;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public final class BookLibrary {
    private BookLibrary() {
    }

    public static void main(String[] args) throws IOException {
        final File baseDirectory = new File(System.getProperty("java.io.tmpdir"), "BookLibraryEvents");
        FileUtils.deleteDirectory(baseDirectory);

        EventStore eventStore = new FileSystemEventStore(new EventStoreFileResolver(baseDirectory));
        eventStore.store(Events.newDomainEvent("book1", "book", new BookRegistered("The Day the Crayons Quit", "Oliver Jeffers", "0399255370")));
        eventStore.store(Events.newDomainEvent("book2", "book", new BookRegistered("Beautiful Oops!", "Barney Saltzberg", "076115728X")));
        eventStore.store(Events.newDomainEvent("book3", "book", new BookRegistered("The Most Magnificent Thing", "Ashley Spires", "1554537045")));

        eventStore.store(Events.newDomainEvent("book2", "book", new BookLent()));
        eventStore.store(Events.newDomainEvent("book2", "book", new BookReturned()));

        eventStore.store(Events.newDomainEvent("book3", "book", new BookLent()));

    }
}
