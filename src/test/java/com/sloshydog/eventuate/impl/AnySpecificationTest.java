package com.sloshydog.eventuate.impl;

import com.sloshydog.eventuate.api.Event;
import com.sloshydog.eventuate.api.EventSpecification;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

public class AnySpecificationTest {
    @Test
    public void shouldBeASpecification() {
        assertThat(EventSpecification.class).isAssignableFrom(AnySpecification.class);
    }

    @Test
    public void shouldMatchAnyEvent() {
        Event event = mock(Event.class);

        assertThat(new AnySpecification().matches(event)).isTrue();
    }
}