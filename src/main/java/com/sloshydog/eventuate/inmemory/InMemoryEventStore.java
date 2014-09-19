package com.sloshydog.eventuate.inmemory;

import com.sloshydog.eventuate.api.Event;
import com.sloshydog.eventuate.api.EventSpecification;
import com.sloshydog.eventuate.api.EventStore;
import com.sloshydog.eventuate.api.EventStream;

public class InMemoryEventStore implements EventStore {

    @Override
    public void store(Event applicationEvent) {

    }

    @Override
    public EventStream getMatching(EventSpecification eventSpecification) {
        return null;
    }
}
