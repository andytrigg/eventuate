package com.sloshydog.eventuate.inmemory;

import com.sloshydog.eventuate.api.Event;
import com.sloshydog.eventuate.api.EventSpecification;
import com.sloshydog.eventuate.api.EventStore;
import com.sloshydog.eventuate.api.EventStream;
import com.sloshydog.eventuate.inmemory.InMemoryEventStore;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class InMemoryEventStoreTest {

    @Test
    public void shouldBeAnEventStore() {
        assertThat(EventStore.class).isAssignableFrom(InMemoryEventStore.class);
    }

    @Test
    public void shouldReturnAnEmptyEventStreamWhenNoEventHasBeenStored() {
        Event newEvent = mock(Event.class);
        EventSpecification eventSpecification = mock(EventSpecification.class);

        when(eventSpecification.matches(newEvent)).thenReturn(true);

        EventStore eventStore = new InMemoryEventStore();
        EventStream eventStream = eventStore.getMatching(eventSpecification);
        assertThat(eventStream).isEmpty();
    }

    @Test
    public void shouldBeAbleToStoreASingleEventInMemory() {
        Event newEvent = mock(Event.class);
        EventSpecification eventSpecification = mock(EventSpecification.class);

        when(eventSpecification.matches(newEvent)).thenReturn(true);

        EventStore eventStore = new InMemoryEventStore();
        eventStore.store(newEvent);

        EventStream eventStream = eventStore.getMatching(eventSpecification);
        assertThat(eventStream).hasSize(1);
    }

    @Test
    public void shouldNotReturnAnEventStreamContainingEventsThatDoNotMatchTheSpecification() {
        Event newEvent = mock(Event.class);
        EventSpecification eventSpecification = mock(EventSpecification.class);

        when(eventSpecification.matches(newEvent)).thenReturn(false);

        InMemoryEventStore eventStore = new InMemoryEventStore();
        eventStore.store(newEvent);

        EventStream eventStream = eventStore.getMatching(eventSpecification);
        assertThat(eventStream).hasSize(0);
    }

    @Test
    public void shouldOnlyReturnEventsInTheEventStreamThatMatchTheSpecification() {
        Event eventOne = mock(Event.class);
        Event eventTwo = mock(Event.class);
        Event eventThree = mock(Event.class);
        EventSpecification eventSpecification = mock(EventSpecification.class);

        when(eventSpecification.matches(eventOne)).thenReturn(true);
        when(eventSpecification.matches(eventTwo)).thenReturn(false);
        when(eventSpecification.matches(eventThree)).thenReturn(true);

        InMemoryEventStore eventStore = new InMemoryEventStore();
        eventStore.store(eventOne);
        eventStore.store(eventTwo);
        eventStore.store(eventThree);

        EventStream eventStream = eventStore.getMatching(eventSpecification);
        assertThat(eventStream).containsExactly(eventOne, eventThree);
    }
}
