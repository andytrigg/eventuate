package com.sloshydog.eventuate.filesystem;

import com.sloshydog.eventuate.api.EventStream;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.io.Closeable;

public class FileSystemEventStreamTest {

    @Test
    public void shouldBeAnEventStream() {
        Assertions.assertThat(EventStream.class).isAssignableFrom(FileSystemEventStream.class);
    }

    @Test
    public void shouldBeCloseable() {
        Assertions.assertThat(Closeable.class).isAssignableFrom(FileSystemEventStream.class);
    }

}