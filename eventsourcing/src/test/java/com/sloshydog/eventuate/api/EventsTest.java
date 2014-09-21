package com.sloshydog.eventuate.api;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class EventsTest {

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowWhenAggregateIdIsNull() {
        Events.newDomainEvent(null, "type");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowWhenAggregateIdIsEmpty() {
        Events.newDomainEvent("", "type");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowWhenAggregateIdIsBlank() {
        Events.newDomainEvent("  ", "type");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowWhenAggregateTypeIsNull() {
        Events.newDomainEvent("123", null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowWhenAggregateTypeIsEmpty() {
        Events.newDomainEvent("123", "");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowWhenAggregateTypeIsBlank() {
        Events.newDomainEvent("123", "   ");
    }

    @Test
    public void shouldBeAbleToCreateAnEventWithAnAggregateId() {
        Event<Object> newDomainEvent = Events.newDomainEvent("123", "type");
        assertThat(newDomainEvent.getAggregateIdentifier()).isEqualTo("123");
    }

    @Test
    public void shouldBeAbleToCreateAnEventWithATimeStamp() {
        Event<Object> newDomainEvent = Events.newDomainEvent("123", "type");
        assertThat(newDomainEvent.getTimeStamp()).isNotNull();
    }

    @Test
    public void shouldBeAbleToCreateAnEventWithAnAggregateType() {
        Event<Object> newDomainEvent = Events.newDomainEvent("123", "type");
        assertThat(newDomainEvent.getAggregateType()).isEqualTo("type");
    }
}