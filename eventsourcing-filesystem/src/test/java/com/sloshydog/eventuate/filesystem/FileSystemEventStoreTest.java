package com.sloshydog.eventuate.filesystem;

import com.sloshydog.eventuate.api.Event;
import com.sloshydog.eventuate.api.EventSpecification;
import com.sloshydog.eventuate.api.EventStore;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@RunWith(MockitoJUnitRunner.class)
public class FileSystemEventStoreTest {

    @Mock
    private EventStoreFileResolver eventStoreFileResolver;

    @Test
    public void shouldBeAnEventStore() {
        Assertions.assertThat(EventStore.class).isAssignableFrom(FileSystemEventStore.class);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowAnExceptionWhenTheEventIsNull() {
        EventStore eventStore = new FileSystemEventStore(eventStoreFileResolver);
        eventStore.store(null);
    }

    @Test
    public void shouldBeAbleToStoreAnEventToTheFileSystem() throws IOException {
        Event newEvent = Mockito.mock(Event.class);
        Mockito.when(newEvent.getAggregateIdentifier()).thenReturn("124");
        Mockito.when(newEvent.getAggregateType()).thenReturn("type");

        Mockito.when(newEvent.getPayload()).thenReturn("Events payload");

        File eventFileDirectory = new File(System.getProperty("java.io.tmpdir"), "/type");
        eventFileDirectory.mkdirs();
        File eventFile = new File(eventFileDirectory, "124.evt");

        Mockito.when(eventStoreFileResolver.getFileFor(new EventSpecification("type", "124"))).thenReturn(eventFile);

        EventStore eventStore = new FileSystemEventStore(eventStoreFileResolver);
        eventStore.store(newEvent);

        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(eventFile);

            Assertions.assertThat(new DataInputStream(inputStream).readUTF()).isEqualTo("Events payload");
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
    }

    // TODO require a test when exception is thrown during storage.
//    @Test()
//    public void shouldBeAbleToReadAnEventForTheStore() {
//        Event newEvent = mock(Event.class);
//        when(newEvent.getAggregateIdentifier()).thenReturn("123");
//        when(newEvent.getAggregateType()).thenReturn("type");
//
//        when(newEvent.getPayload()).thenReturn("Events payload");
//
//        EventStore eventStore = new FileSystemEventStore(System.getProperty("java.io.tmpdir"));
//        eventStore.store(newEvent);
//
//        EventStream events = eventStore.getMatching(new EventSpecification("123", "type"));
//        assertThat(events.iterator()).extracting("identifier").containsExactly("123");
//    }
}