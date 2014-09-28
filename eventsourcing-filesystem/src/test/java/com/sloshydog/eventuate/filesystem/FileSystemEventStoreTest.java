package com.sloshydog.eventuate.filesystem;

import com.sloshydog.eventuate.api.Event;
import com.sloshydog.eventuate.api.EventSpecification;
import com.sloshydog.eventuate.api.EventStore;
import com.sloshydog.eventuate.api.EventStoreException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.failBecauseExceptionWasNotThrown;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FileSystemEventStoreTest {

    @Mock
    private EventStoreFileResolver eventStoreFileResolver;
    @Mock
    private FileSystemEventMessageWriter eventMessageWriter;
    @Mock
    private FileSystemEventMessageReader eventMessageReader;

    @Test
    public void shouldBeAnEventStore() {
        assertThat(EventStore.class).isAssignableFrom(FileSystemEventStore.class);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowAnExceptionWhenTheEventIsNull() {
        EventStore eventStore = new FileSystemEventStore(eventStoreFileResolver, eventMessageWriter, eventMessageReader);
        eventStore.store(null);
    }

    @Test
    public void shouldBeAbleToStoreAnEventToTheFileSystem() throws IOException {
        Event newEvent = mock(Event.class);
        when(newEvent.getAggregateIdentifier()).thenReturn("124");
        when(newEvent.getAggregateType()).thenReturn("type");

        File eventFileDirectory = new File(System.getProperty("java.io.tmpdir"), "/type");
        eventFileDirectory.mkdirs();
        File eventFile = new File(eventFileDirectory, "124.evt");

        when(eventStoreFileResolver.getFileFor(new EventSpecification("type", "124"))).thenReturn(eventFile);

        EventStore eventStore = new FileSystemEventStore(eventStoreFileResolver, eventMessageWriter, eventMessageReader);
        eventStore.store(newEvent);

        verify(eventMessageWriter).writeEventMessage(any(DataOutput.class), eq(newEvent));
    }

    @Test
    public void shouldTrowAnExceptionOnIOException() throws IOException {
        Event newEvent = mock(Event.class);
        when(newEvent.getAggregateIdentifier()).thenReturn("124");
        when(newEvent.getAggregateType()).thenReturn("type");

        when(newEvent.getPayload()).thenReturn("Events payload");

        File eventFileDirectory = new File(System.getProperty("java.io.tmpdir"), "/type");
        eventFileDirectory.mkdirs();
        File eventFile = new File(eventFileDirectory, "124.evt");

        when(eventStoreFileResolver.getFileFor(new EventSpecification("type", "124"))).thenReturn(eventFile);
        doThrow(new IOException("bang")).when(eventMessageWriter).writeEventMessage(any(DataOutput.class), eq(newEvent));

        EventStore eventStore = new FileSystemEventStore(eventStoreFileResolver, eventMessageWriter, eventMessageReader);
        try {
            eventStore.store(newEvent);
            failBecauseExceptionWasNotThrown(EventStoreException.class);
        } catch (EventStoreException e) {
            assertThat(e).hasMessage("Unable to load event due to a FileNotFoundException");
        }
    }

    @Test
    public void shouldBeAbleToGetEventStreamForAMatch() throws IOException {
        EventSpecification eventSpecification = mock(EventSpecification.class);

        File eventFileDirectory = new File(System.getProperty("java.io.tmpdir"), "/type");
        eventFileDirectory.mkdirs();
        File eventFile = new File(eventFileDirectory, "124.evt");

        when(eventStoreFileResolver.getFileFor(eventSpecification)).thenReturn(eventFile);

        when(eventMessageReader.readEventMessage(any(DataInput.class))).thenThrow(new EOFException());
        EventStore eventStore = new FileSystemEventStore(eventStoreFileResolver, eventMessageWriter, eventMessageReader);

        assertThat(eventStore.getMatching(eventSpecification)).isNotNull();
    }

    @Test
    public void shouldThrowAnExceptionIfFileNotFoundExceptionDuringEventStreamCreation() {
        EventSpecification eventSpecification = mock(EventSpecification.class);


        File eventFile = new File("/ThisShouldNeverExists/", "124.evt");

        when(eventStoreFileResolver.getFileFor(eventSpecification)).thenReturn(eventFile);
        try {
            FileSystemEventStore eventStore = new FileSystemEventStore(eventStoreFileResolver, eventMessageWriter, eventMessageReader);
            eventStore.getMatching(eventSpecification);
            failBecauseExceptionWasNotThrown(FileNotFoundException.class);
        } catch (Exception e) {
            assertThat(e).hasMessage("Unable to load event due to a FileNotFoundException");
        }
    }
}