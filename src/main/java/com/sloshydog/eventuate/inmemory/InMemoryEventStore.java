package com.sloshydog.eventuate.inmemory;

import com.sloshydog.eventuate.api.Event;
import com.sloshydog.eventuate.api.EventSpecification;
import com.sloshydog.eventuate.api.EventStore;
import com.sloshydog.eventuate.api.EventStream;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class InMemoryEventStore implements EventStore {
    private final List<Event> events = new ArrayList<Event>();

    @Override
    public void store(Event applicationEvent) {
        events.add(applicationEvent);
    }

    @Override
    public EventStream getMatching(EventSpecification eventSpecification) {
        return new EventStream() {
            @Override
            public Iterator<Event> iterator() {
                return events.iterator();
            }
        };
    }
}
