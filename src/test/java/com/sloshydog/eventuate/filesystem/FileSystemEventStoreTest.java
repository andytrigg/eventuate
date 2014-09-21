package com.sloshydog.eventuate.filesystem;

import com.sloshydog.eventuate.api.Event;
import com.sloshydog.eventuate.api.EventSpecification;
import com.sloshydog.eventuate.api.EventStore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FileSystemEventStoreTest {

    @Mock
    private EventStoreFileResolver eventStoreFileResolver;

    @Test
    public void shouldBeAnEventStore() {
        assertThat(EventStore.class).isAssignableFrom(FileSystemEventStore.class);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowAnExceptionWhenTheEventIsNull() {
        EventStore eventStore = new FileSystemEventStore(eventStoreFileResolver);
        eventStore.store(null);
    }

    @Test
    public void shouldBeAbleToStoreAnEventToTheFileSystem() throws IOException {
        Event newEvent = mock(Event.class);
        when(newEvent.getIdentifier()).thenReturn("123");
        when(newEvent.getAggregateType()).thenReturn("type");

        when(newEvent.getPayload()).thenReturn("Events payload");

        when(eventStoreFileResolver.getFileFor(new EventSpecification("123", "type"))).thenReturn(new File(System.getProperty("java.io.tmpdir"), "/type/123.evt"));

        EventStore eventStore = new FileSystemEventStore(eventStoreFileResolver);
        eventStore.store(newEvent);

        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(new File(System.getProperty("java.io.tmpdir"), "/type/123.evt"));

            assertThat(new DataInputStream(inputStream).readUTF()).isEqualTo("Events payload");
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
    }

//    @Test()
//    public void shouldBeAbleToReadAnEventForTheStore() {
//        Event newEvent = mock(Event.class);
//        when(newEvent.getIdentifier()).thenReturn("123");
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