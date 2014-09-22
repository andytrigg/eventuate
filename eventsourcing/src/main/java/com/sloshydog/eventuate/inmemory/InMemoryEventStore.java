package com.sloshydog.eventuate.inmemory;

import com.google.common.base.Predicate;
import com.sloshydog.eventuate.api.Event;
import com.sloshydog.eventuate.api.EventSpecification;
import com.sloshydog.eventuate.api.EventStore;
import com.sloshydog.eventuate.api.EventStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import static com.google.common.collect.FluentIterable.from;
import static com.google.common.collect.Lists.newArrayList;

public class InMemoryEventStore implements EventStore {

    private static final Logger LOGGER = LoggerFactory.getLogger(InMemoryEventStore.class);

    private final List<Event> events = newArrayList();

    @Override
    public void store(Event applicationEvent) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("store the event:%s", applicationEvent);
        }
        events.add(applicationEvent);
    }

    @Override
    public EventStream getMatching(final EventSpecification eventSpecification) {
        return new EventStream() {
            @Override
            public Iterator<Event> iterator() {
                return from(events).filter(new EventSpecificationPredicate(eventSpecification)).iterator();
            }

            @Override
            public void close() throws IOException {

            }
        };
    }

    private static class EventSpecificationPredicate implements Predicate<Event> {

        private final EventSpecification eventSpecification;

        public EventSpecificationPredicate(EventSpecification eventSpecification) {
            this.eventSpecification = eventSpecification;
        }

        @Override
        public boolean apply(final Event event) {
            return eventSpecification.matches(event);
        }
    }
}
