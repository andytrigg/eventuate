package com.sloshydog.eventuate.api;

import org.joda.time.DateTime;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class EventsTest {

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowWhenAggregateIdIsNull() {
        Events.newDomainEvent("type", null, "payload");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowWhenAggregateIdIsEmpty() {
        Events.newDomainEvent("type", "", "payload");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowWhenAggregateIdIsBlank() {
        Events.newDomainEvent("type", "  ", "payload");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowWhenAggregateTypeIsNull() {
        Events.newDomainEvent(null, "123", "payload");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowWhenAggregateTypeIsEmpty() {
        Events.newDomainEvent("", "123", "payload");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowWhenAggregateTypeIsBlank() {
        Events.newDomainEvent("   ", "123", "payload");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowWhenPayloadIsNull() {
        Events.<String>newDomainEvent("type", "123", null);
    }

    @Test
    public void shouldBeAbleToCreateAnEventWithAnAggregateId() {
        Event<String> newDomainEvent = Events.newDomainEvent("type", "123", "payload");
        assertThat(newDomainEvent.getAggregateIdentifier()).isEqualTo("123");
    }

    @Test
    public void shouldBeAbleToCreateAnEventWithATimeStamp() {
        Event<String> newDomainEvent = Events.newDomainEvent("type", "123", "payload");
        assertThat(newDomainEvent.getTimeStamp()).isNotNull();
    }

    @Test
    public void shouldBeAbleToCreateAnEventWithAnAggregateType() {
        Event<String> newDomainEvent = Events.newDomainEvent("type", "123", "payload");
        assertThat(newDomainEvent.getAggregateType()).isEqualTo("type");
    }


    @Test
    public void shouldBeAbleToCreateAnEventWithAPayload() {
        Event<String> newDomainEvent = Events.newDomainEvent("type", "123", "payload");
        assertThat(newDomainEvent.getPayload()).isEqualTo("payload");
    }

    @Test
    public void shouldBeAbleToCreateAnEventWithATimeStampExplicitlyProvided() {
        DateTime timeStamp = new DateTime();
        Event<String> newDomainEvent = Events.newDomainEvent("type", "123", timeStamp, "payload");
        assertThat(newDomainEvent.getTimeStamp()).isEqualTo(timeStamp);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowWhenTimeStampIsNull() {
        Events.newDomainEvent("type", "123", null, "payload");
    }

    @Test
    public void shouldBeAbleToGetAToStringOfAnEvent() {
        Event<String> newDomainEvent = Events.newDomainEvent("type", "123", new DateTime(1234L), "payload");
        assertThat(newDomainEvent.toString()).contains("[aggregateId=123,aggregateType=type,payload=payload,timeStamp=1970-01-01");
    }
}
