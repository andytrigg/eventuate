package com.sloshydog.eventuate.inmemory;

import com.sloshydog.eventuate.api.EventStore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class InMemoryEventStoreTest {

    @Test
    public void shouldBeAnEventStore() {
        assertThat(EventStore.class).isAssignableFrom(InMemoryEventStore.class);
    }
}
