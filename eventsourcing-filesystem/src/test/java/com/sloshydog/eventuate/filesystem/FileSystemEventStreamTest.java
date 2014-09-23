package com.sloshydog.eventuate.filesystem;

import com.sloshydog.eventuate.api.EventStream;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class FileSystemEventStreamTest {

    @Test
    public void shouldBeAnEventStream() {
        Assertions.assertThat(EventStream.class).isAssignableFrom(FileSystemEventStream.class);
    }
}