package com.sloshydog.eventuate.filesystem;

import com.sloshydog.eventuate.api.Event;
import com.sloshydog.eventuate.api.EventStoreException;
import com.sloshydog.eventuate.api.EventStream;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.DataInput;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.failBecauseExceptionWasNotThrown;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FileSystemEventStreamTest {

    @Mock
    private FileSystemEventMessageReader eventMessageReader;
    @Mock
    private InputStream inputStream;

    @Test
    public void shouldBeAnEventStream() {
        assertThat(EventStream.class).isAssignableFrom(FileSystemEventStream.class);
    }

    @Test
    public void shouldProvideAnIteratorOverTheEvents() throws IOException {
        Event event1 = mock(Event.class);
        Event event2 = mock(Event.class);

        when(eventMessageReader.readEventMessage(any(DataInput.class))).thenReturn(event1).thenReturn(event2).thenThrow(new EOFException());

        FileSystemEventStream events = new FileSystemEventStream(eventMessageReader, inputStream);

        assertThat(events.iterator()).contains(event1, event2);
    }

    @Test
    public void shouldDelegateCloseToTheInputStream() throws IOException {
        Event event1 = mock(Event.class);

        when(eventMessageReader.readEventMessage(any(DataInput.class))).thenReturn(event1).thenThrow(new EOFException());

        FileSystemEventStream events = new FileSystemEventStream(eventMessageReader, inputStream);

        events.close();

        Mockito.verify(inputStream).close();
    }

    @Test
    public void shouldThrowAnExceptionWhenIOExceptionIsThrown() throws IOException {
        when(eventMessageReader.readEventMessage(any(DataInput.class))).thenThrow(new IOException("bang"));

        try {
            new FileSystemEventStream(eventMessageReader, inputStream);
            failBecauseExceptionWasNotThrown(EventStoreException.class);
        } catch (Exception e) {
            assertThat(e).hasMessage("An error occurred while reading from the underlying source");
        }
    }
}