package com.sloshydog.eventuate.sample;

import com.sloshydog.eventuate.api.EventStore;
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
//        eventStore.store(new );

    }
}
