package com.sloshydog.eventuate.api;

public interface EventStore {
    void store(Event applicationEvent);

    EventStream getMatching(EventSpecification eventSpecification);
}