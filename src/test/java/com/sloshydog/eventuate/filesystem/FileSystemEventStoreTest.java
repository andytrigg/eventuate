package com.sloshydog.eventuate.filesystem;

import com.sloshydog.eventuate.api.EventStore;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FileSystemEventStoreTest {
    @Test
    public void shouldBeAnEventStore() {
        assertThat(EventStore.class).isAssignableFrom(FileSystemEventStore.class);
    }

}