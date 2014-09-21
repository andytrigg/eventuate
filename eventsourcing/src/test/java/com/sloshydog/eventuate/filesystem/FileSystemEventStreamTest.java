package com.sloshydog.eventuate.filesystem;

import com.sloshydog.eventuate.api.EventStream;
import com.sloshydog.eventuate.filesystem.FileSystemEventStream;
import org.junit.Test;

import java.io.Closeable;

import static org.assertj.core.api.Assertions.assertThat;

public class FileSystemEventStreamTest {

    @Test
    public void shouldBeAnEventStream() {
        assertThat(EventStream.class).isAssignableFrom(FileSystemEventStream.class);
    }

    @Test
    public void shouldBeCloseable() {
        assertThat(Closeable.class).isAssignableFrom(FileSystemEventStream.class);
    }

}