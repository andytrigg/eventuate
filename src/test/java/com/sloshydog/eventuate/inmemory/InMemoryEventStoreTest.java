package com.sloshydog.eventuate.inmemory;

import com.sloshydog.eventuate.api.Event;
import com.sloshydog.eventuate.api.EventStore;
import com.sloshydog.eventuate.api.EventStream;
import com.sloshydog.eventuate.impl.Specifications;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class InMemoryEventStoreTest {

    @Test
    public void shouldBeAnEventStore() {
        assertThat(EventStore.class).isAssignableFrom(InMemoryEventStore.class);
    }

    @Test
    public void shouldBeAbleToStoreASingleEventInMemory() {
        Event newEvent = mock(Event.class);

        InMemoryEventStore eventStore = new InMemoryEventStore();
        eventStore.store(newEvent);

        EventStream eventStream = eventStore.getMatching(Specifications.any());
        assertThat(eventStream).hasSize(1);
    }
}
