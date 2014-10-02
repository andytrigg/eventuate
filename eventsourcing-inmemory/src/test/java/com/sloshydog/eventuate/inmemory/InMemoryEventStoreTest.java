package com.sloshydog.eventuate.inmemory;

import com.sloshydog.eventuate.api.Event;
import com.sloshydog.eventuate.api.EventSpecification;
import com.sloshydog.eventuate.api.EventStore;
import com.sloshydog.eventuate.api.EventStream;
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
        EventSpecification eventSpecification = new EventSpecification("type", "1234");

        EventStore eventStore = new InMemoryEventStore();
        EventStream eventStream = eventStore.getMatching(eventSpecification);
        assertThat(eventStream).isEmpty();
    }

    @Test
    public void shouldBeAbleToStoreASingleEventInMemory() {
        Event newEvent = createMockEvent("type", "1234");
        EventSpecification eventSpecification = new EventSpecification("type", "1234");

        EventStore eventStore = new InMemoryEventStore();
        eventStore.store(newEvent);

        EventStream eventStream = eventStore.getMatching(eventSpecification);
        assertThat(eventStream).hasSize(1);
    }

    @Test
    public void shouldNotReturnAnEventStreamContainingEventsThatDoNotMatchTheSpecification() {
        Event newEvent = createMockEvent("type", "1234");

        EventSpecification eventSpecification = new EventSpecification("type", "1235");

        InMemoryEventStore eventStore = new InMemoryEventStore();
        eventStore.store(newEvent);

        EventStream eventStream = eventStore.getMatching(eventSpecification);
        assertThat(eventStream).hasSize(0);
    }

    @Test
    public void shouldOnlyReturnEventsInTheEventStreamThatMatchTheSpecification() {
        Event eventOne = createMockEvent("type", "1234");
        Event eventTwo = createMockEvent("type", "1235");
        Event eventThree = createMockEvent("type", "1234");
        EventSpecification eventSpecification = new EventSpecification("type", "1234");

        InMemoryEventStore eventStore = new InMemoryEventStore();
        eventStore.store(eventOne);
        eventStore.store(eventTwo);
        eventStore.store(eventThree);

        EventStream eventStream = eventStore.getMatching(eventSpecification);
        assertThat(eventStream).containsExactly(eventOne, eventThree);
    }

    private Event createMockEvent(String type, String value) {
        Event newEvent = mock(Event.class);
        when(newEvent.getAggregateType()).thenReturn(type);
        when(newEvent.getAggregateIdentifier()).thenReturn(value);
        return newEvent;
    }
}
