package com.sloshydog.eventuate.api;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class EventSpecificationTest {

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenNullAggregateIdProvided() {
        new EventSpecification(null, "foo");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenNullAggregateTypeProvided() {
        new EventSpecification("123", null);
    }

    @Test
    public void shouldBeAbleToGetAggregateId() {
        assertThat(new EventSpecification("123", "type").getAggregateIdentifier()).isEqualTo("123");
    }

    @Test
    public void shouldBeAbleToGetAggregateType() {
        assertThat(new EventSpecification("123", "type").getAggregateType()).isEqualTo("type");
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowExceptionWhenNullEventGivenToMatch() {
        new EventSpecification("123", "type").matches(null);
    }

    @Test
    public void shouldBeAbleToMatchAnEventWithTheCorrectSpecification() {
        Event expectedEvent = mock(Event.class);
        when(expectedEvent.getAggregateIdentifier()).thenReturn("123");
        when(expectedEvent.getAggregateType()).thenReturn("type");

        assertThat(new EventSpecification("123", "type").matches(expectedEvent)).isTrue();
    }

    @Test
    public void shouldNotMatchAnEventIfTheAggregateIdIsIncorrect() {
        Event expectedEvent = mock(Event.class);
        when(expectedEvent.getAggregateIdentifier()).thenReturn("923");
        when(expectedEvent.getAggregateType()).thenReturn("type");

        assertThat(new EventSpecification("123", "type").matches(expectedEvent)).isFalse();
    }

    @Test
    public void shouldNotMatchAnEventIfTheAggregateTypeIsIncorrect() {
        Event expectedEvent = mock(Event.class);
        when(expectedEvent.getAggregateIdentifier()).thenReturn("123");
        when(expectedEvent.getAggregateType()).thenReturn("wrong type");

        assertThat(new EventSpecification("123", "type").matches(expectedEvent)).isFalse();
    }
}