package com.sloshydog.eventuate.api;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class EventSpecificationsTest {

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowAnExceptionIfNullEventProvided() {
        EventSpecifications.specificationFrom(null);
    }

    @Test
    public void shouldBeAbleToCreateAnEventSpecificationFromAnEvent() {
        Event event = mock(Event.class);
        when(event.getIdentifier()).thenReturn("123");
        when(event.getAggregateType()).thenReturn("type");

        assertThat(EventSpecifications.specificationFrom(event)).isEqualTo(new EventSpecification("123", "type"));
    }
}
