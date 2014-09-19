package com.sloshydog.eventuate.impl;

import com.sloshydog.eventuate.api.Event;
import com.sloshydog.eventuate.api.EventSpecification;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AggregateTypeSpecificationTest {
    @Test
    public void shouldBeASpecification() {
        assertThat(EventSpecification.class).isAssignableFrom(AggregateTypeSpecification.class);
    }

    @Test
    public void shouldMatchWhenAggregateTypeIsTheSame() {
        Event event = mock(Event.class);
        when(event.getAggregateType()).thenReturn("aggregateType");

        assertThat(new AggregateTypeSpecification("aggregateType").matches(event)).isTrue();
    }

    @Test
    public void shouldNotMatchWhenAggregateTypeIsNotTheSame() {
        Event event = mock(Event.class);
        when(event.getAggregateType()).thenReturn("unkownAggregateType");

        assertThat(new AggregateTypeSpecification("aggregateType").matches(event)).isFalse();
    }

}